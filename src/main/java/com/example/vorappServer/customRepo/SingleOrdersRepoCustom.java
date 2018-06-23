package com.example.vorappServer.customRepo;

import com.example.vorappServer.model.Dimiensions;
import com.example.vorappServer.model.Orders;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SingleOrdersRepoCustom {

    List findByOrders(Orders order);

    List findByDimension(Dimiensions dimension);

    @Transactional
    void deleteByDimension(Dimiensions dimension);

    @Transactional
    void deleteByOrders(Orders order);

}
