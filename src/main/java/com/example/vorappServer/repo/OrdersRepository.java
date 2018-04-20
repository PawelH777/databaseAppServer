package com.example.vorappServer.repo;

import com.example.vorappServer.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Created by Paweł on 2018-03-22.
 */

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {

}
