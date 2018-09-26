package com.example.vorappServer.customRepo;

import com.example.vorappServer.model.SingleOrders;
import com.example.vorappServer.model.Trays;

import java.util.List;

public interface TraysRepoCustom {

    List findAllTraysBySingleOrders(SingleOrders singleOrdersObject);

    void deleteBySingleOrders(SingleOrders singleOrdersObject);
}
