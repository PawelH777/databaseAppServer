package com.example.vorappServer.controllers;

import com.example.vorappServer.extraClass.ClientsHelpClass;
import com.example.vorappServer.model.Clients;
import com.example.vorappServer.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * Created by Paweł on 2018-03-26.
 */

@RestController
@RequestMapping("/clients")
public class ClientsController {

    @Autowired
    private ClientsRepo clientsRepo;

    @Autowired
    private OrdersRepo ordersRepo;

    @Autowired
    private SingleOrdersRepo singleOrdersRepo;

    @RequestMapping
    public ResponseEntity<Collection<Clients>> findAll(){
        return new ResponseEntity<>(clientsRepo.findAll(), HttpStatus.OK);
    }

    @GetMapping("/client/id/{id}")
    public ResponseEntity<Clients> findById(@PathVariable("id") Long client_id){
        Clients cli = clientsRepo.findById(client_id).orElse(null);
        return  new ResponseEntity<>(cli, HttpStatus.OK);
    }

    @PostMapping("/client/firmname")
    public List<Clients> findByFirmName(@RequestBody ClientsHelpClass clientsHelpClass){
        return clientsRepo.findByfirmName(clientsHelpClass.getFirmName());
    }

    @PostMapping(value = "/createclient")
    public Clients createClient(@RequestBody Clients clients){
        return clientsRepo.save(clients);
    }

    @PutMapping(value = "/client/update/{id}")
    public Clients updateClient(@PathVariable(value = "id") Long clientId, @Valid @RequestBody Clients clients){
        Clients findclient = clientsRepo.findById(clientId)
                .orElse(null);

        findclient.setFirmName(clients.getFirmName());

        return clientsRepo.save(findclient);
    }
}
