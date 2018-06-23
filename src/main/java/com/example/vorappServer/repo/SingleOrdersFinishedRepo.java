package com.example.vorappServer.repo;

import com.example.vorappServer.customRepo.SingleOrdersFinishedRepoCustom;
import com.example.vorappServer.model.SingleOrdersFinished;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SingleOrdersFinishedRepo extends JpaRepository<SingleOrdersFinished, Long>, SingleOrdersFinishedRepoCustom {
}
