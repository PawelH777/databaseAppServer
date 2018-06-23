package com.example.vorappServer.customRepo;

import com.example.vorappServer.model.Client;
import com.example.vorappServer.model.Dimiensions;
import com.example.vorappServer.model.OrdersFinished;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Paweł on 2018-04-21.
 */
public interface OrdersFinishedRepoCustom {

    List findByClient(Client client);
    @Transactional
    void deleteByClient(Client client);
}
