package com.example.vorappServer.repo;

import java.util.List;

import com.example.vorappServer.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

/**
 * Created by Paweł on 2018-03-22.
 */

@Repository
public interface ClientRepo extends JpaRepository<Client, Long> {

List<Client> findByfirmName(String firmName);
}
