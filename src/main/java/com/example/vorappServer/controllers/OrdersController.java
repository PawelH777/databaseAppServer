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

    @Autowired
    private OrdersRepo ordersRepo;

    @Autowired
    private SingleOrdersRepo singleOrdersRepo;

    @Autowired
    private SingleOrdersServices singleOrdersServices;

    @RequestMapping
    public ResponseEntity<List<Orders>> findAll(){
        return new ResponseEntity<>(ordersRepo.findAll(), HttpStatus.OK);
    }

    @GetMapping("/order/id/{id}")
    public ResponseEntity<Orders> findById(@PathVariable("id") Long order_id){
        Orders ord = ordersRepo.findById(order_id).orElse(null);
        return  new ResponseEntity<>(ord, HttpStatus.OK);
    }

    @PostMapping("/clients")
    public ResponseEntity<List> findByClientId(@RequestBody Clients clients){
        return new ResponseEntity<List>(ordersRepo.findByClients(clients), HttpStatus.OK);
    }

    @GetMapping("/finished/{finished}")
    public ResponseEntity<List> findByOrderFinished(@PathVariable("finished") Boolean finished){
        return new ResponseEntity<List>(ordersRepo.findByOrderFinished(finished), HttpStatus.OK);
    }

    @PostMapping(value = "/createorder")
    public Orders createOrder(@RequestBody Orders order){
        return ordersRepo.save(order);
    }

    @PutMapping(value = "/order/update/{id}")
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

    @PutMapping(value = "/order/change-status")
    public ResponseEntity<Orders> changeOrdersStatus(@RequestBody Orders order){

        if(order == null)
            return new ResponseEntity<Orders>(HttpStatus.NOT_FOUND);

        if(order.getOrderFinished())
            order.setOrderFinished(false);
        else
            order.setOrderFinished(true);

        return new ResponseEntity<>(ordersRepo.save(order), HttpStatus.OK);
    }

    @RequestMapping(value = "/order/delete/{id}")
    public ResponseEntity<Object> deleteOrder(@PathVariable(value = "id") Long orderId){
        Orders ordDelete = ordersRepo.findById(orderId).orElse(null);
        assert ordDelete != null;
        singleOrdersServices.deleteSingleOrdersByOrder(ordDelete);
        ordersRepo.delete(ordDelete);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/delete/clients")
    public ResponseEntity<Object> deleteOrdersByClient(@RequestBody Clients clients){
        ordersRepo.deleteByClients(clients);
        return ResponseEntity.ok().build();
    }
}
