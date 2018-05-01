package com.example.vorappServer.impl;

import com.example.vorappServer.customRepo.OrdersRepoCustom;
import com.example.vorappServer.model.Client;
import com.example.vorappServer.model.Dimiensions;
import com.example.vorappServer.model.Orders;
import org.springframework.transaction.annotation.Transactional;

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
    public List findByClient(Client client){
        Query query = entityManager.createQuery("select o from Orders o where o.client_id = :id", Orders.class);

        query.setParameter("id", client);

        return query.getResultList();
    }

    @Override
    public List findByDimension(Dimiensions dimension){
        Query query = entityManager.createQuery("select o from Orders o where o.dimension_id = :id", Orders.class);

        query.setParameter("id", dimension);

        return query.getResultList();
    }

    @Override
    public void deleteByClient(Client client){
        Query query = entityManager.createQuery("delete from Orders o where o.client_id = :id", Orders.class);

        query.setParameter("id", client);
    }

    @Override
    public void deleteByDimension(Dimiensions dimension){
        Query query = entityManager.createQuery("delete from Orders o where o.dimension_id = :id", Orders.class);

        query.setParameter("id", dimension);
    }
}
