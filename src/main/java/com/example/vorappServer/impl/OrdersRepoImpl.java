package com.example.vorappServer.impl;

import com.example.vorappServer.customRepo.OrdersRepoCustom;
import com.example.vorappServer.model.Clients;
import com.example.vorappServer.model.Orders;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Paweł on 2018-04-21.
 */
public class OrdersRepoImpl implements OrdersRepoCustom {

    @PersistenceContext
    private
    EntityManager entityManager;

    @Override
    public List findByClients(Clients clients){
        Query query = entityManager.createQuery("select o from Orders o where o.client_id = :id", Orders.class);
        query.setParameter("id", clients);
        return query.getResultList();
    }

    @Override
    public List findByOrderFinished(Boolean finished){
        Query query = entityManager.createQuery("select o from Orders o where o.order_finished = :finished", Orders.class);
        query.setParameter("finished", finished);
        return query.getResultList();
    }

    @Override
    public void deleteByClients(Clients clients){
        Query query = entityManager.createQuery("delete from Orders o where o.client_id = :id", Orders.class);
        query.setParameter("id", clients);
    }
}
