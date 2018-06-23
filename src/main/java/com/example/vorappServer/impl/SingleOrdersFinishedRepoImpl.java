package com.example.vorappServer.impl;

import com.example.vorappServer.customRepo.SingleOrdersFinishedRepoCustom;
import com.example.vorappServer.model.Dimiensions;
import com.example.vorappServer.model.OrdersFinished;
import com.example.vorappServer.model.SingleOrders;
import com.example.vorappServer.model.SingleOrdersFinished;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class SingleOrdersFinishedRepoImpl implements SingleOrdersFinishedRepoCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List findByOrderstory(OrdersFinished ordersFinished){
        Query query = entityManager.createQuery("select s from SingleOrdersFinished where s.orderstory_id = :id", SingleOrdersFinished.class);
        query.setParameter("id", ordersFinished);
        return query.getResultList();
    }

    @Override
    public List findByDimension(Dimiensions dimension){
        Query query = entityManager.createQuery("select s from SingleOrdersFinished where s.dimension_id = :id", SingleOrdersFinished.class);
        query.setParameter("id", dimension);
        return query.getResultList();
    }

    @Override
    public void deleteByOrderstory(OrdersFinished ordersFinished){
        Query query = entityManager.createQuery("delete from SingleOrdersFinished o where o.orderstory_id = :id", SingleOrdersFinished.class);
        query.setParameter("id", ordersFinished);
    }

    @Override
    public void deleteByDimension(Dimiensions dimension){
        Query query = entityManager.createQuery("delete from SingleOrdersFinished o where o.dimension_id = :id", SingleOrdersFinished.class);
        query.setParameter("id", dimension);
    }
}
