package com.example.vorappServer.customRepo;

import com.example.vorappServer.model.Clients;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Paweł on 2018-04-21.
 */
public interface OrdersRepoCustom {
    List findByClients(Clients clients);
    List findByOrderFinished(Boolean finished);
    @Transactional
    void deleteByClients(Clients clients);
}
