package com.example.vorappServer.controllers;


import com.example.vorappServer.model.Dimiensions;
import com.example.vorappServer.model.Orders;
import com.example.vorappServer.model.SingleOrders;
import com.example.vorappServer.model.SingleOrdersFinished;
import com.example.vorappServer.repo.SingleOrdersFinishedRepo;
import com.example.vorappServer.repo.SingleOrdersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/single-orders")
public class SingleOrdersController {

    @Autowired
    private SingleOrdersRepo singleOrdersRepo;

    @RequestMapping
    public ResponseEntity<List<SingleOrders>> findAll(){
        return new ResponseEntity<>(singleOrdersRepo.findAll(), HttpStatus.OK);
    }

    @PostMapping("/order")
    public ResponseEntity<List> findByOrders(@RequestBody Orders orderObject){
        return new ResponseEntity<List>(singleOrdersRepo.findByOrders(orderObject), HttpStatus.OK);
    }

    @PostMapping("/dimension")
    public ResponseEntity<List> findByDimiensions(@RequestBody Dimiensions dimensionObject){
        return new ResponseEntity<List>(singleOrdersRepo.findByDimension(dimensionObject), HttpStatus.OK);
    }

    @PostMapping("/create-single-order")
    public SingleOrders createSingleOrder(@RequestBody SingleOrders singleOrders){
        return singleOrdersRepo.save(singleOrders);
    }

    @PutMapping("/update-single-order/{id}")
    public SingleOrders updateSingleOrder(@PathVariable(value = "id") Long singleOrderId, @RequestBody SingleOrders singleOrders){
        SingleOrders singleOrderFromDatabase = singleOrdersRepo
                .findById(singleOrderId)
                .orElse(null);
        if(singleOrderFromDatabase != null){
            singleOrderFromDatabase.setDimension(singleOrders.getDimension());
            singleOrderFromDatabase.setLength(singleOrders.getLength());
            singleOrderFromDatabase.setQuantity(singleOrders.getQuantity());
            singleOrderFromDatabase.setMetrs(singleOrders.getMetrs());
            singleOrderFromDatabase.setMaterials(singleOrders.getMaterials());
            singleOrderFromDatabase.setFinished(singleOrders.getFinished());
        }
        return singleOrdersRepo.save(singleOrderFromDatabase);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteSingleOrder(@PathVariable(value = "id") Long singleOrderId){
        SingleOrders singleOrdersObject= singleOrdersRepo.findById(singleOrderId).orElse(null);

        singleOrdersRepo.delete(singleOrdersObject);

        return ResponseEntity.ok().build();
    }

    @RequestMapping("/delete/order")
    public ResponseEntity<Object> deleteSingleOrderByOrder(@RequestBody Orders order){
        singleOrdersRepo.deleteByOrders(order);
        return ResponseEntity.ok().build();
    }

    @RequestMapping("/delete/dimension")
    public ResponseEntity<Object> deleteSingleOrderByDimension(@RequestBody Dimiensions dimension){
        singleOrdersRepo.deleteByDimension(dimension);
        return ResponseEntity.ok().build();
    }
}
