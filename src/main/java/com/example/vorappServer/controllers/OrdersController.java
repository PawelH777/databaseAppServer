package com.example.vorappServer.controllers;

import com.example.vorappServer.model.Client;
import com.example.vorappServer.model.Dimiensions;
import com.example.vorappServer.model.Orders;
import com.example.vorappServer.repo.DimRepo;
import com.example.vorappServer.repo.OrdersRepository;
import com.example.vorappServer.repo.UserRepo;
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
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersRepository ordersRepository;

    @RequestMapping
    public ResponseEntity<List<Orders>> findAll(){

        return new ResponseEntity<>(ordersRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/order/id/{id}")
    public ResponseEntity<Object[]> findById(@PathVariable("id") Long order_id){
        Orders ord = ordersRepository.findById(order_id).orElse(null);
        return  new ResponseEntity<>(ord.toObject(), HttpStatus.OK);
    }

    @PostMapping(value = "/createorder")
    public Orders createOrder(@RequestBody Orders order){
        return ordersRepository.save(order);
    }

    @PutMapping(value = "/order/update/{id}")
    public Orders updateOrder(@PathVariable(value = "id") Long orderId, @Valid @RequestBody Orders order){
        Orders findorder = ordersRepository.findById(orderId)
                .orElse(null);

        findorder.setDimension(order.getDimension());
        findorder.setClient(order.getClient());
        findorder.setMaterials(order.getMaterials());
        findorder.setMetrs(order.getMetrs());
        findorder.setNote(order.getNote());
        findorder.setOrder_date(order.getOrder_date());
        findorder.setReceive_date(order.getReceive_date());

        return ordersRepository.save(findorder);
    }

    @DeleteMapping(value = "/order/delete/{id}")
    public ResponseEntity<Object> deleteOrder(@PathVariable(value = "id") Long orderId){
        Orders ordDelete = ordersRepository.findById(orderId).orElse(null);

        ordersRepository.delete(ordDelete);

        return ResponseEntity.ok().build();
    }
}
