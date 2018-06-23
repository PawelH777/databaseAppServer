package com.example.vorappServer.controllers;

import com.example.vorappServer.model.*;
import com.example.vorappServer.repo.OrdersRepo;
import com.example.vorappServer.repo.SingleOrdersFinishedRepo;
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
@RequestMapping("/orderstory")
public class OrdersFinishedController {

    @Autowired
    private OrdersRepo ordersRepo;

    @Autowired
    private com.example.vorappServer.repo.OrdersFinishedRepo ordersFinishedRepo;

    @Autowired
    private SingleOrdersRepo singleOrdersRepo;

    @Autowired
    private SingleOrdersFinishedRepo singleOrdersFinishedRepo;

    @RequestMapping
    public ResponseEntity<List<OrdersFinished>> findAll(){
        return new ResponseEntity<>(ordersFinishedRepo.findAll(), HttpStatus.OK);
    }

    @RequestMapping("/order/id/{id}")
    public ResponseEntity<OrdersFinished> findById(@PathVariable("id") Long order_id){
        OrdersFinished ord = ordersFinishedRepo.findById(order_id).orElse(null);
        return  new ResponseEntity<>(ord, HttpStatus.OK);
    }

    @PostMapping("/clients")
    public ResponseEntity<List> findByClientId(@RequestBody Client client){
        return new ResponseEntity<List>(ordersFinishedRepo.findByClient(client), HttpStatus.OK);
    }

    @PostMapping(value = "/createorder")
    public OrdersFinished createOrder(@RequestBody OrdersFinished order){
        return ordersFinishedRepo.save(order);
    }

    @PutMapping(value = "/order/update/{id}")
    public OrdersFinished updateOrder(@PathVariable(value = "id") Long orderId, @Valid @RequestBody OrdersFinished order){
        OrdersFinished findorder = ordersFinishedRepo.findById(orderId)
                .orElse(null);

        if(findorder != null){
            findorder.setClient(order.getClient());
            findorder.setMaterials(order.getMaterials());
            findorder.setOrder_date(order.getOrder_date());
            findorder.setOrder_note(order.getOrder_note());
            findorder.setOrder_receive_date(order.getOrder_receive_date());
            findorder.setSingle_orders_completed(order.getSingle_orders_completed());
            findorder.setSingle_orders_unfinished(order.getSingle_orders_unfinished());
        }
        return ordersFinishedRepo.save(findorder);
    }

    @RequestMapping(value = "/order/delete/{id}")
    public ResponseEntity<Object> deleteOrderWithSingleFinishedOrders(@PathVariable(value = "id") Long orderId){
        OrdersFinished ordDelete = ordersFinishedRepo.findById(orderId).orElse(null);
        if(ordDelete != null)
            singleOrdersFinishedRepo.deleteByOrderstory(ordDelete);

        ordersFinishedRepo.delete(ordDelete);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/delete/client")
    public ResponseEntity<Object> deleteOrdersByClient(@RequestBody Client client){
        ordersFinishedRepo.deleteByClient(client);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/move-finished-order")
    public ResponseEntity<Object> moveFinishedOrderToOrders(@RequestBody OrdersFinished orderFinished){
        Orders orderObject = ordersRepo.save(new Orders(orderFinished.getClient(), orderFinished.getMaterials(),
                orderFinished.getOrder_receive_date(), orderFinished.getOrder_date(),
                orderFinished.getOrder_note(), orderFinished.getSingle_orders_completed(),
                orderFinished.getSingle_orders_unfinished()));
        List<SingleOrdersFinished> allSingleFinishedOrders = singleOrdersFinishedRepo.findByOrderstory(orderFinished);
        for(SingleOrdersFinished singleOrdersFinished : allSingleFinishedOrders)
            singleOrdersRepo.save(new SingleOrders(orderObject, singleOrdersFinished.getDimension(),
                    singleOrdersFinished.getQuantity(), singleOrdersFinished.getLength(), singleOrdersFinished.getMetrs(),
                    singleOrdersFinished.getMaterials(), singleOrdersFinished.getFinished()));
        singleOrdersFinishedRepo.deleteByOrderstory(orderFinished);
        ordersFinishedRepo.delete(orderFinished);
        return ResponseEntity.ok().build();
    }
}
