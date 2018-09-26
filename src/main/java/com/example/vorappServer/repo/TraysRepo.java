package com.example.vorappServer.repo;

import com.example.vorappServer.customRepo.TraysRepoCustom;
import com.example.vorappServer.model.Trays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TraysRepo extends JpaRepository<Trays, Long>, TraysRepoCustom {

}
