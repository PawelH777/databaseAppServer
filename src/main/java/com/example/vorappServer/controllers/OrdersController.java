package com.example.vorappServer.controllers;

import com.example.vorappServer.model.*;
import com.example.vorappServer.repo.OrdersFinishedRepo;
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
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersRepo ordersRepo;

    @Autowired
    private OrdersFinishedRepo ordersFinishedRepo;

    @Autowired
    private SingleOrdersRepo singleOrdersRepo;

    @Autowired
    private SingleOrdersFinishedRepo singleOrdersFinishedRepo;

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
    public ResponseEntity<List> findByClientId(@RequestBody Client client){
        return new ResponseEntity<List>(ordersRepo.findByClient(client), HttpStatus.OK);
    }

    @PostMapping(value = "/createorder")
    public Orders createOrder(@RequestBody Orders order){
        return ordersRepo.save(order);
    }

    @PutMapping(value = "/order/update/{id}")
    public Orders updateOrder(@PathVariable(value = "id") Long orderId, @Valid @RequestBody Orders order){
        Orders findorder = ordersRepo.findById(orderId)
                .orElse(null);

        findorder.setClient(order.getClient());
        findorder.setMaterials(order.getMaterials());
        findorder.setOrder_receive_date(order.getOrder_receive_date());
        findorder.setOrder_date(order.getOrder_date());
        findorder.setOrder_note(order.getOrder_note());
        findorder.setSingle_orders_completed(order.getSingle_orders_completed());
        findorder.setSingle_orders_unfinished(order.getSingle_orders_unfinished());

        return ordersRepo.save(findorder);
    }

    @RequestMapping(value = "/order/delete/{id}")
    public ResponseEntity<Object> deleteOrder(@PathVariable(value = "id") Long orderId){
        Orders ordDelete = ordersRepo.findById(orderId).orElse(null);
        if(ordDelete != null)
            singleOrdersRepo.deleteByOrders(ordDelete);
        ordersRepo.delete(ordDelete);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/delete/client")
    public ResponseEntity<Object> deleteOrdersByClient(@RequestBody Client client){
        ordersRepo.deleteByClient(client);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/move-order")
    public ResponseEntity<Object> moveOrderToOrdersFinished(@RequestBody Orders order){
        OrdersFinished ordersFinished = ordersFinishedRepo.save( new OrdersFinished(order.getClient(),
                order.getMaterials(), order.getOrder_receive_date(), order.getOrder_date(), order.getOrder_note(),
                order.getSingle_orders_completed(), order.getSingle_orders_unfinished()));
        List<SingleOrders> allSingleOrdersByOrder = singleOrdersRepo.findByOrders(order);
        for(SingleOrders singleOrder : allSingleOrdersByOrder)
            singleOrdersFinishedRepo.save(new SingleOrdersFinished(ordersFinished, singleOrder.getDimension(),
                    singleOrder.getQuantity(), singleOrder.getLength(), singleOrder.getMetrs(), singleOrder.getMaterials(),
                    singleOrder.getFinished()));
        singleOrdersRepo.deleteByOrders(order);
        ordersRepo.delete(order);
        return ResponseEntity.ok().build();
    }
}
