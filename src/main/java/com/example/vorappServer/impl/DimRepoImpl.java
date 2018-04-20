package com.example.vorappServer.impl;

import com.example.vorappServer.customRepo.DimRepoCustom;
import com.example.vorappServer.model.Dimiensions;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Paweł on 2018-04-03.
 */

@Repository
public class DimRepoImpl implements DimRepoCustom{
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Dimiensions> findByDim(BigDecimal firstDimension, BigDecimal secondDimension, BigDecimal thickness, BigDecimal weight){
        Query query = entityManager.createQuery("select d from Dimiensions d where d.firstDimension = :first and d.secondDimension = :second" +
                " and d.thickness = :third and d.weight = :fourth", Dimiensions.class);

        query.setParameter("first", firstDimension);
        query.setParameter("second", secondDimension);
        query.setParameter("third", thickness);
        query.setParameter("fourth", weight);

        return query.getResultList();
    }
}
