package com.example.vorappServer.Services;

import com.example.vorappServer.model.Orders;
import com.example.vorappServer.model.SingleOrders;
import com.example.vorappServer.model.Trays;
import com.example.vorappServer.repo.OrdersRepo;
import com.example.vorappServer.repo.SingleOrdersRepo;
import com.example.vorappServer.repo.TraysRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SingleOrdersServices {

    @Autowired
    private SingleOrdersRepo singleOrdersRepo;

    @Autowired
    private TraysRepo traysRepo;

    public void deleteSingleOrder(Long singleOrderId){
        SingleOrders singleOrdersObject= singleOrdersRepo.findById(singleOrderId).orElse(null);

        assert singleOrdersObject != null;

        List<Trays> trays = traysRepo.findAllTraysBySingleOrders(singleOrdersObject);
        for(Trays tray : trays)
            traysRepo.delete(tray);

        singleOrdersRepo.delete(singleOrdersObject);
    }

    public void deleteSingleOrdersByOrder(Orders order){
        List<SingleOrders> singleOrders = singleOrdersRepo.findByOrders(order);
        for(SingleOrders singleOrder : singleOrders)
            deleteSingleOrder(singleOrder.getSingle_active_order_id());
    }
}
