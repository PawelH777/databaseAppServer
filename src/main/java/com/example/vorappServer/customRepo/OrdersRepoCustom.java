package com.example.vorappServer.customRepo;

import com.example.vorappServer.model.Client;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Paweł on 2018-04-21.
 */
public interface OrdersRepoCustom {
    List findByClient(Client client);
    @Transactional
    void deleteByClient(Client client);
}
