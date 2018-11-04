package com.example.vorappServer.controllers;

import com.example.vorappServer.Services.SingleOrdersServices;
import com.example.vorappServer.model.*;
import com.example.vorappServer.repo.OrdersRepo;
import com.example.vorappServer.repo.SingleOrdersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Paweł on 2018-03-26.
 */

@RestController
@RequestMapping("/orders")
public class OrdersController {

    private final OrdersRepo ordersRepo;

    private final SingleOrdersServices singleOrdersServices;

    @Autowired
    public OrdersController(OrdersRepo ordersRepo, SingleOrdersServices singleOrdersServices) {
        this.ordersRepo = ordersRepo;
        this.singleOrdersServices = singleOrdersServices;
    }

    @RequestMapping
    public ResponseEntity<List<Orders>> findAll(){
        return new ResponseEntity<>(ordersRepo.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orders> findById(@PathVariable("id") Long order_id){
        Orders ord = ordersRepo.findById(order_id).orElse(null);
        return  new ResponseEntity<>(ord, HttpStatus.OK);
    }

    @GetMapping("/finished/{finished}")
    public ResponseEntity<List> findByOrderFinished(@PathVariable("finished") Boolean finished){
        return new ResponseEntity<List>(ordersRepo.findByOrderFinished(finished), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public Orders createOrder(@RequestBody Orders order){
        return ordersRepo.save(order);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Orders>  updateOrder(@PathVariable(value = "id") Long orderId, @Valid @RequestBody Orders order){
        Orders findorder = ordersRepo.findById(orderId)
                .orElse(null);

        if(findorder == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        findorder.setClients(order.getClients());
        findorder.setMaterials(order.getMaterials());
        findorder.setOrder_receive_date(order.getOrder_receive_date());
        findorder.setOrder_date(order.getOrder_date());
        findorder.setOrder_note(order.getOrder_note());
        findorder.setSingle_orders_finished(order.getSingle_orders_finished());
        findorder.setSingle_orders_unfinished(order.getSingle_orders_unfinished());
        findorder.setOrderFinished(order.getOrderFinished());

        return new ResponseEntity<Orders>(ordersRepo.save(findorder), HttpStatus.OK);
    }

    @PutMapping(value = "/change-status")
    public ResponseEntity<Orders> changeOrdersStatus(@RequestBody Orders order){

        if(order == null)
            return new ResponseEntity<Orders>(HttpStatus.NOT_FOUND);

        if(order.getOrderFinished())
            order.setOrderFinished(false);
        else
            order.setOrderFinished(true);

        return new ResponseEntity<>(ordersRepo.save(order), HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}")
    public ResponseEntity<Object> deleteOrder(@PathVariable(value = "id") Long orderId){
        Orders ordDelete = ordersRepo.findById(orderId).orElse(null);
        if(ordDelete == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        singleOrdersServices.deleteSingleOrdersByOrder(ordDelete);
        ordersRepo.delete(ordDelete);
        return ResponseEntity.ok().build();
    }
}
