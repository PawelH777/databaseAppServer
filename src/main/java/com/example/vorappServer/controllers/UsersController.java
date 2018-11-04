package com.example.vorappServer.controllers;

import com.example.vorappServer.model.User;
import com.example.vorappServer.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Paweł on 2018-03-26.
 */

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UserRepo userRepo;

    @Autowired
    public UsersController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @RequestMapping
    public ResponseEntity findAll(){
        return new ResponseEntity<>(userRepo.findAll(), HttpStatus.OK);
    }

    @RequestMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable("id") Long user_id){
        User user = userRepo.findById(user_id).orElse(null);
        assert user != null;
        return  new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/login/{login}")
    public List<User> findByLogin(@PathVariable(value = "login") String login)
    {
        return userRepo.findByLogin(login);
    }

    @PostMapping(value = "/create")
    public User createUser(@RequestBody User user){
        return userRepo.save(user);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId, @Valid @RequestBody User user){
        User finduser = userRepo.findById(userId)
                .orElse(null);
        if(finduser == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        finduser.setLogin(user.getLogin());
        finduser.setPassword(user.getPassword());
        finduser.setAdmin(user.isAdmin());
        return new ResponseEntity<>(userRepo.save(finduser), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable(value = "id") Long userId){
        User usrDelete = userRepo.findById(userId).orElse(null);
        if(usrDelete == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        userRepo.delete(usrDelete);
        return ResponseEntity.ok().build();
    }
}
