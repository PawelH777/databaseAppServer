package com.example.vorappServer.impl;

import com.example.vorappServer.customRepo.SingleOrdersRepoCustom;
import com.example.vorappServer.model.Dimiensions;
import com.example.vorappServer.model.Orders;
import com.example.vorappServer.model.SingleOrders;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class SimpleOrdersRepoImpl implements SingleOrdersRepoCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List findByOrders(Orders order){
        Query query = entityManager.createQuery("select s from SingleOrders where s.order_id = :id", SingleOrders.class);
        query.setParameter("id", order);
        return query.getResultList();
    }

    @Override
    public List findByDimension(Dimiensions dimension){
        Query query = entityManager.createQuery("select s from SingleOrders where s.dimension_id = :id", SingleOrders.class);
        query.setParameter("id", dimension);
        return query.getResultList();
    }

    @Override
    public void deleteByOrders(Orders order){
        Query query = entityManager.createQuery("delete from SingleOrders o where o.order_id = :id", SingleOrders.class);
        query.setParameter("id", order);
    }

    @Override
    public void deleteByDimension(Dimiensions dimension){
        Query query = entityManager.createQuery("delete from SingleOrders o where o.dimension_id = :id", SingleOrders.class);
        query.setParameter("id", dimension);
    }
}
