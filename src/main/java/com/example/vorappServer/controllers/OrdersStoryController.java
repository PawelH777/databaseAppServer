package com.example.vorappServer.controllers;

import com.example.vorappServer.model.Client;
import com.example.vorappServer.model.Dimiensions;
import com.example.vorappServer.model.ordersStory;
import com.example.vorappServer.repo.ordersStoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Paweł on 2018-03-26.
 */

@RestController
@RequestMapping("/orderstory")
public class OrdersStoryController {

    @Autowired
    private ordersStoryRepo ordersStoryRepo;

    @RequestMapping
    public ResponseEntity<List<ordersStory>> findAll(){
        return new ResponseEntity<>(ordersStoryRepo.findAll(), HttpStatus.OK);
    }

    @RequestMapping("/order/id/{id}")
    public ResponseEntity<Object[]> findById(@PathVariable("id") Long order_id){
        ordersStory ord = ordersStoryRepo.findById(order_id).orElse(null);
        return  new ResponseEntity<>(ord.toObject(), HttpStatus.OK);
    }

    @PostMapping("/clients")
    public ResponseEntity<List> findByClientId(@RequestBody Client client){
        return new ResponseEntity<List>(ordersStoryRepo.findByClient(client), HttpStatus.OK);
    }

    @PostMapping("/dims")
    public ResponseEntity<List> findByDim(@RequestBody Dimiensions dimension){
        return new ResponseEntity<List>(ordersStoryRepo.findByDimension(dimension), HttpStatus.OK);
    }

    @PostMapping(value = "/createorder")
    public ordersStory createOrder(@RequestBody ordersStory order){
        return ordersStoryRepo.save(order);
    }

    @PutMapping(value = "/order/update/{id}")
    public ordersStory updateOrder(@PathVariable(value = "id") Long orderId, @Valid @RequestBody ordersStory order){
        ordersStory findorder = ordersStoryRepo.findById(orderId)
                .orElse(null);

        findorder.setDimension(order.getDimension());
        findorder.setClient(order.getClient());
        findorder.setMaterials(order.getMaterials());
        findorder.setMetrs(order.getMetrs());
        findorder.setNote(order.getNote());
        findorder.setOrder_date(order.getOrder_date());
        findorder.setReceive_date(order.getReceive_date());

        return ordersStoryRepo.save(findorder);
    }

    @DeleteMapping(value = "/order/delete/{id}")
    public ResponseEntity<Object> deleteOrder(@PathVariable(value = "id") Long orderId){
        ordersStory ordDelete = ordersStoryRepo.findById(orderId).orElse(null);

        ordersStoryRepo.delete(ordDelete);

        return ResponseEntity.ok().build();
    }


    @RequestMapping(value = "/delete/client")
    public ResponseEntity<Object> deleteOrdersByClient(@RequestBody Client client){
        ordersStoryRepo.deleteByClient(client);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/delete/dim")
    public ResponseEntity<Object> deleteOrdersByDim(@RequestBody Dimiensions dim){
        ordersStoryRepo.deleteByDimension(dim);
        return ResponseEntity.ok().build();
    }
}
