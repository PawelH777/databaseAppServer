package com.example.vorappServer.controllers;


import com.example.vorappServer.model.Dimiensions;
import com.example.vorappServer.model.OrdersFinished;
import com.example.vorappServer.model.SingleOrdersFinished;
import com.example.vorappServer.repo.SingleOrdersFinishedRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/single-orders-finished")
public class SingleOrdersFinishedController {
    @Autowired
    private SingleOrdersFinishedRepo singleOrdersFinishedRepo;

    @RequestMapping
    public ResponseEntity<List<SingleOrdersFinished>> findAll(){
        return new ResponseEntity<>(singleOrdersFinishedRepo.findAll(), HttpStatus.OK);
    }

    @RequestMapping("/finished-order")
    public ResponseEntity<List> findByOrdersFinished(@RequestBody OrdersFinished orderFinished){
        return new ResponseEntity<>(singleOrdersFinishedRepo.findByOrderstory(orderFinished), HttpStatus.OK);
    }

    @PostMapping("/dimension")
    public ResponseEntity<List> findByDimiensions(@RequestBody Dimiensions dimensionObject){
        return new ResponseEntity<List>(singleOrdersFinishedRepo.findByDimension(dimensionObject), HttpStatus.OK);
    }

    @PostMapping("/create-single-order-finished")
    public SingleOrdersFinished createSingleOrderFinished(@RequestBody SingleOrdersFinished singleOrderFinishedObject){
        return singleOrdersFinishedRepo.save(singleOrderFinishedObject);
    }

    @PutMapping("update-single-order-finished/{id}")
    public SingleOrdersFinished updateSingleOrderFinished(@PathVariable(value = "id") Long singleOrderFinishedId,
                                                          @RequestBody SingleOrdersFinished singleOrdersFinished){
        SingleOrdersFinished singleOrderFinishedFromDatabase = singleOrdersFinishedRepo.
                findById(singleOrderFinishedId)
                .orElse(null);

        if(singleOrderFinishedFromDatabase != null){
            singleOrderFinishedFromDatabase.setDimension(singleOrdersFinished.getDimension());
            singleOrderFinishedFromDatabase.setLength(singleOrdersFinished.getLength());
            singleOrderFinishedFromDatabase.setQuantity(singleOrdersFinished.getQuantity());
            singleOrderFinishedFromDatabase.setMetrs(singleOrdersFinished.getMetrs());
            singleOrderFinishedFromDatabase.setMaterials(singleOrdersFinished.getMaterials());
            singleOrderFinishedFromDatabase.setFinished(singleOrdersFinished.getFinished());
        }
        return singleOrdersFinishedRepo.save(singleOrderFinishedFromDatabase);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteSingleOrderFinished(@PathVariable(value = "id") Long singleOrderId){
        SingleOrdersFinished singleOrdersObject= singleOrdersFinishedRepo.findById(singleOrderId).orElse(null);

        singleOrdersFinishedRepo.delete(singleOrdersObject);

        return ResponseEntity.ok().build();
    }

    @RequestMapping("/delete/finished-order")
    public ResponseEntity<Object> deleteSingleOrdersFinishedByOrdersFinished(@RequestBody OrdersFinished orderFinished){
        singleOrdersFinishedRepo.deleteByOrderstory(orderFinished);
        return ResponseEntity.ok().build();
    }

    @RequestMapping("/delete/dimension")
    public ResponseEntity<Object> deleteSingleOrderByDimension(@RequestBody Dimiensions dimension){
        singleOrdersFinishedRepo.deleteByDimension(dimension);
        return ResponseEntity.ok().build();
    }
}
