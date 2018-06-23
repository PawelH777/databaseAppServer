package com.example.vorappServer.repo;

import java.util.List;

import com.example.vorappServer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Paweł on 2018-03-22.
 */

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
   List<User> findByLogin(@Param("login") String login);
}
