package com.example.vorappServer.repo;

import java.util.List;

import com.example.vorappServer.model.Clients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Paweł on 2018-03-22.
 */

@Repository
public interface ClientsRepo extends JpaRepository<Clients, Long> {

List<Clients> findByfirmName(String firmName);
}
