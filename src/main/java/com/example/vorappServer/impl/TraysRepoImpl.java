package com.example.vorappServer.impl;

import com.example.vorappServer.customRepo.TraysRepoCustom;
import com.example.vorappServer.model.SingleOrders;
import com.example.vorappServer.model.Trays;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class TraysRepoImpl implements TraysRepoCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List findAllTraysBySingleOrders(SingleOrders singleOrdersObject){
        Query query = entityManager.createQuery("select t from Trays t where t.single_order = :id", Trays.class);
        query.setParameter("id", singleOrdersObject);
        return query.getResultList();
    }

    @Override
    public void deleteBySingleOrders(SingleOrders singleOrdersObject){
        Query query = entityManager.createQuery("delete from Trays t where t.single_order = :id", Trays.class);
        query.setParameter("id", singleOrdersObject);
    }
}
