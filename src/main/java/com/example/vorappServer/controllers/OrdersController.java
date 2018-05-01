package com.example.vorappServer.controllers;

import com.example.vorappServer.model.Client;
import com.example.vorappServer.model.Dimiensions;
import com.example.vorappServer.model.Orders;
import com.example.vorappServer.repo.OrdersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.ws.Response;
import java.util.List;

/**
 * Created by Paweł on 2018-03-26.
 */

@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersRepo ordersRepo;

    @RequestMapping
    public ResponseEntity<List<Orders>> findAll(){

        return new ResponseEntity<>(ordersRepo.findAll(), HttpStatus.OK);
    }

    @GetMapping("/order/id/{id}")
    public ResponseEntity<Object[]> findById(@PathVariable("id") Long order_id){
        Orders ord = ordersRepo.findById(order_id).orElse(null);
        return  new ResponseEntity<>(ord.toObject(), HttpStatus.OK);
    }

    @PostMapping("/clients")
    public ResponseEntity<List> findByClientId(@RequestBody Client client){
        return new ResponseEntity<List>(ordersRepo.findByClient(client), HttpStatus.OK);
    }

    @PostMapping("/dims")
    public ResponseEntity<List> findByDim(@RequestBody Dimiensions dimension){
        return new ResponseEntity<List>(ordersRepo.findByDimension(dimension), HttpStatus.OK);
    }

    @PostMapping(value = "/createorder")
    public Orders createOrder(@RequestBody Orders order){
        return ordersRepo.save(order);
    }

    @PutMapping(value = "/order/update/{id}")
    public Orders updateOrder(@PathVariable(value = "id") Long orderId, @Valid @RequestBody Orders order){
        Orders findorder = ordersRepo.findById(orderId)
                .orElse(null);

        findorder.setDimension(order.getDimension());
        findorder.setClient(order.getClient());
        findorder.setMaterials(order.getMaterials());
        findorder.setMetrs(order.getMetrs());
        findorder.setNote(order.getNote());
        findorder.setOrder_date(order.getOrder_date());
        findorder.setReceive_date(order.getReceive_date());

        return ordersRepo.save(findorder);
    }

    @DeleteMapping(value = "/order/delete/{id}")
    public ResponseEntity<Object> deleteOrder(@PathVariable(value = "id") Long orderId){
        Orders ordDelete = ordersRepo.findById(orderId).orElse(null);

        ordersRepo.delete(ordDelete);

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/delete/client")
    public ResponseEntity<Object> deleteOrdersByClient(@RequestBody Client client){
        ordersRepo.deleteByClient(client);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/delete/dim")
    public ResponseEntity<Object> deleteOrdersByDim(@RequestBody Dimiensions dim){
        ordersRepo.deleteByDimension(dim);
        return ResponseEntity.ok().build();
    }
}
