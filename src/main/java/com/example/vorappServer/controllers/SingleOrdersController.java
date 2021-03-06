package com.example.vorappServer.controllers;


import com.example.vorappServer.Services.SingleOrdersServices;
import com.example.vorappServer.model.Dimiensions;
import com.example.vorappServer.model.Orders;
import com.example.vorappServer.model.SingleOrders;
import com.example.vorappServer.model.Trays;
import com.example.vorappServer.repo.SingleOrdersRepo;
import com.example.vorappServer.repo.TraysRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/single-orders")
public class SingleOrdersController {

    private final SingleOrdersRepo singleOrdersRepo;

    private final TraysRepo traysRepo;

    private final SingleOrdersServices singleOrdersServices;

    @Autowired
    public SingleOrdersController(SingleOrdersRepo singleOrdersRepo, TraysRepo traysRepo, SingleOrdersServices singleOrdersServices) {
        this.singleOrdersRepo = singleOrdersRepo;
        this.traysRepo = traysRepo;
        this.singleOrdersServices = singleOrdersServices;
    }

    @RequestMapping
    public ResponseEntity<List<SingleOrders>> findAll(){
        return new ResponseEntity<>(singleOrdersRepo.findAll(), HttpStatus.OK);
    }

    @PostMapping("/order")
    public ResponseEntity<List> findByOrders(@RequestBody Orders orderObject){
        return new ResponseEntity<List>(singleOrdersRepo.findByOrders(orderObject), HttpStatus.OK);
    }

    @PostMapping("/create")
    public SingleOrders createSingleOrder(@RequestBody SingleOrders singleOrders){
        return singleOrdersRepo.save(singleOrders);
    }

    @PutMapping("/update")
    public ResponseEntity<SingleOrders> updateSingleOrder(@RequestBody SingleOrders singleOrders){
        Optional<SingleOrders> singleOrderFromDatabase = singleOrdersRepo.findById(singleOrders.getSingle_active_order_id());

        if(!singleOrderFromDatabase.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        int traysCounter = singleOrders.getAmount_of_trays().intValue() - singleOrderFromDatabase.get().getAmount_of_trays().intValue();

        if(traysCounter < 0){
            List<Trays> trays = traysRepo.findAllTraysBySingleOrders(singleOrders);
            for(Trays tray : trays)
                traysRepo.delete(tray);

            for(int a = 0; a < singleOrders.getAmount_of_trays(); a++)
                traysRepo.save(new Trays(singleOrders, "Paleta " + (a + 1)));
        }
        else if(traysCounter > 0){
            int traysIdentifer = singleOrderFromDatabase.get().getAmount_of_trays().intValue();
            for(; traysIdentifer < singleOrders.getAmount_of_trays(); traysIdentifer++)
                traysRepo.save(new Trays(singleOrders, "Paleta " + (traysIdentifer + 1)));
        }

        return new ResponseEntity<>(singleOrdersRepo.save(singleOrders), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteSingleOrder(@PathVariable(value = "id") Long singleOrderId){
        singleOrdersServices.deleteSingleOrder(singleOrderId);
        return ResponseEntity.ok().build();
    }

    @RequestMapping("/delete/order/{id}")
    public ResponseEntity<Object> deleteSingleOrderByOrder(@PathVariable(value = "id") Long singleOrderId, @RequestBody Orders order){
        SingleOrders singleOrdersObject= singleOrdersRepo.findById(singleOrderId).orElse(null);
        if(singleOrdersObject == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        traysRepo.deleteBySingleOrders(singleOrdersObject);
        singleOrdersRepo.deleteByOrders(order);
        return ResponseEntity.ok().build();
    }
}
