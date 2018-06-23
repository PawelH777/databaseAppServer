package com.example.vorappServer.repo;

import com.example.vorappServer.customRepo.SingleOrdersRepoCustom;
import com.example.vorappServer.model.SingleOrders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SingleOrdersRepo extends JpaRepository<SingleOrders, Long>, SingleOrdersRepoCustom  {

}
