package com.example.vorappServer.customRepo;

import com.example.vorappServer.model.Dimiensions;
import com.example.vorappServer.model.OrdersFinished;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SingleOrdersFinishedRepoCustom {

    List findByOrderstory(OrdersFinished ordersFinished);

    List findByDimension(Dimiensions dimension);

    @Transactional
    void deleteByOrderstory(OrdersFinished ordersFinished);

    @Transactional
    void deleteByDimension(Dimiensions dimension);
}
