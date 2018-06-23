package com.example.vorappServer.impl;

import com.example.vorappServer.customRepo.OrdersFinishedRepoCustom;
import com.example.vorappServer.model.Client;
import com.example.vorappServer.model.OrdersFinished;
import com.example.vorappServer.repo.OrdersFinishedRepo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Paweł on 2018-04-22.
 */
public class OrdersFinishedRepoImpl implements OrdersFinishedRepoCustom {

    @PersistenceContext
    private
    EntityManager entityManager;
    @Autowired
    private OrdersFinishedRepo ordersFinishedRepo;

    @Override
    public List findByClient(Client client){
        Query query = entityManager.createQuery("select o from Orders o where o.client_id = :id", OrdersFinished.class);

        query.setParameter("id", client);

        return query.getResultList();
    }

    @Override
    public void deleteByClient(Client client){
        Query query = entityManager.createQuery("delete from Orders o where o.client_id = :id", OrdersFinished.class);

        query.setParameter("id", client);
    }
}
