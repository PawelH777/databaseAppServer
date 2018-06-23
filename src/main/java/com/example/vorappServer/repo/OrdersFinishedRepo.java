package com.example.vorappServer.repo;

import com.example.vorappServer.customRepo.OrdersFinishedRepoCustom;
import com.example.vorappServer.model.OrdersFinished;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Paweł on 2018-03-22.
 */

@Repository
public interface OrdersFinishedRepo extends JpaRepository<OrdersFinished, Long>, OrdersFinishedRepoCustom {

}
