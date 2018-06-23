package com.example.vorappServer.controllers;

import com.example.vorappServer.extraClass.ClientsHelpClass;
import com.example.vorappServer.model.Client;
import com.example.vorappServer.model.Orders;
import com.example.vorappServer.model.OrdersFinished;
import com.example.vorappServer.repo.*;
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
@RequestMapping("/clients")
public class ClientsController {

    @Autowired
    private ClientRepo clientRepo;

    @Autowired
    private OrdersRepo ordersRepo;

    @Autowired
    private OrdersFinishedRepo ordersFinishedRepo;

    @Autowired
    private SingleOrdersRepo singleOrdersRepo;

    @Autowired
    private SingleOrdersFinishedRepo singleOrdersFinishedRepo;

    @RequestMapping
    public ResponseEntity<Collection<Client>> findAll(){
        return new ResponseEntity<>(clientRepo.findAll(), HttpStatus.OK);
    }

    @GetMapping("/client/id/{id}")
    public ResponseEntity<Client> findById(@PathVariable("id") Long client_id){
        Client cli = clientRepo.findById(client_id).orElse(null);
        return  new ResponseEntity<>(cli, HttpStatus.OK);
    }

    @PostMapping("/client/firmname")
    public List<Client> findByFirmName(@RequestBody ClientsHelpClass clientsHelpClass){
        return clientRepo.findByfirmName(clientsHelpClass.getFirmName());
    }

    @PostMapping(value = "/createclient")
    public Client createClient(@RequestBody Client client){
        return clientRepo.save(client);
    }

    @PutMapping(value = "/client/update/{id}")
    public Client updateClient(@PathVariable(value = "id") Long clientId, @Valid @RequestBody Client client){
        Client findclient = clientRepo.findById(clientId)
                .orElse(null);

        findclient.setFirmName(client.getFirmName());

        return clientRepo.save(findclient);
    }

    @DeleteMapping(value = "/client/delete/{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable(value = "id") Long clientId){
        Client cliDelete = clientRepo.findById(clientId).orElse(null);
        if(cliDelete != null){
            List orders = ordersRepo.findByClient(cliDelete);
            List finishedOrders = ordersFinishedRepo.findByClient(cliDelete);
            if(orders.size() > 0)
                for(Object object : orders)
                    singleOrdersRepo.deleteByOrders((Orders) object);
            if(finishedOrders.size() > 0)
                for(Object object : finishedOrders)
                    singleOrdersFinishedRepo.deleteByOrderstory((OrdersFinished) object);
            ordersRepo.deleteByClient(cliDelete);
            ordersFinishedRepo.deleteByClient(cliDelete);
            clientRepo.delete(cliDelete);
        }
        return ResponseEntity.ok().build();
    }
}
