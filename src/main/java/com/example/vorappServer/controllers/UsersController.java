package com.example.vorappServer.controllers;

import com.example.vorappServer.model.User;
import com.example.vorappServer.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Paweł on 2018-03-26.
 */

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserRepo userRepo;

    @RequestMapping
    public ResponseEntity findAll(){
        return new ResponseEntity<>(userRepo.findAll(), HttpStatus.OK);
    }

    @RequestMapping("/user/id/{id}")
    public ResponseEntity<User> findById(@PathVariable("id") Long user_id){
        User user = userRepo.findById(user_id).orElse(null);
        return  new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/user/login/{login}")
    public List<User> findByLogin(@PathVariable(value = "login") String login)
    {
        return userRepo.findByLogin(login);
    }


    @PostMapping(value = "/createuser")
    public User createUser(@RequestBody User user){
        return userRepo.save(user);
    }

    @PutMapping(value = "/user/update/{id}")
    public User updateUser(@PathVariable(value = "id") Long userId, @Valid @RequestBody User user){
        User finduser = userRepo.findById(userId)
                .orElse(null);

        finduser.setLogin(user.getLogin());
        finduser.setPassword(user.getPassword());
        finduser.setAdmin(user.isAdmin());
        return userRepo.save(finduser);
    }

    @DeleteMapping(value = "/user/delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable(value = "id") Long userId){
        User usrDelete = userRepo.findById(userId).orElse(null);

        userRepo.delete(usrDelete);

        return ResponseEntity.ok().build();
    }
}
