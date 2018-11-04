package com.example.vorappServer.controllers;

import com.example.vorappServer.model.SingleOrders;
import com.example.vorappServer.model.Trays;
import com.example.vorappServer.repo.TraysRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trays")
public class TraysController {

    private final TraysRepo traysRepo;

    @Autowired
    public TraysController(TraysRepo traysRepo) {
        this.traysRepo = traysRepo;
    }

    @GetMapping
    public ResponseEntity<List<Trays>> findAll(){
        return new ResponseEntity<>(traysRepo.findAll(), HttpStatus.OK);
    }

    @PostMapping("/single-order")
    public ResponseEntity<List> findAllTraysBySingleOrder(@RequestBody SingleOrders singleOrder){
        return new ResponseEntity<>(traysRepo.findAllTraysBySingleOrders(singleOrder), HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/create")
    public ResponseEntity createTraysBySingleOrder(@RequestBody  SingleOrders singleOrder){
        int numberOfTrays = singleOrder.getAmount_of_trays().intValue();

        for(int a = 0; a < numberOfTrays; a++){
            traysRepo.save(new Trays(singleOrder, "Paleta " + (a + 1)));
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Trays> changeTraysStatus(@RequestBody  Trays tray){
        if(tray == null)
           return new ResponseEntity<Trays>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<Trays>(traysRepo.save(tray), HttpStatus.OK);
    }
}
