package com.example.vorappServer.repo;

import com.example.vorappServer.customRepo.storyOrdersRepoCustom;
import com.example.vorappServer.model.ordersStory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Paweł on 2018-03-22.
 */

@Repository
public interface ordersStoryRepo extends JpaRepository<ordersStory, Long>, storyOrdersRepoCustom {

}
