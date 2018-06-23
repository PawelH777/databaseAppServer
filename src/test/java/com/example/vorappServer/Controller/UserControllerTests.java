package com.example.vorappServer.Controller;

import com.example.vorappServer.controllers.UsersController;
import com.example.vorappServer.model.User;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.hamcrest.core.Is.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Paweł on 2018-05-02.
 */

@RunWith(SpringRunner.class)
@WebMvcTest(UsersController.class)
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private
    UsersController usersController;

    private User user;
    private List<User> allUsers;

    @Test
    @WithMockUser
    public void findAllTest() throws Exception{
        user = new User("user", "password", true);

        allUsers = Collections.singletonList(user);

        given(usersController.findAll()).willReturn(new ResponseEntity<>(allUsers,HttpStatus.OK));

        mockMvc.perform(get("/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].login", is(user.getLogin())))
                .andExpect(jsonPath("$[0].password", is(user.getPassword())))
                .andExpect(jsonPath("$[0].admin", is(user.isAdmin())));
    }

    @Test
    @WithMockUser
    public void findById() throws Exception{
        user = new User(1,"user", "password", true);

        given(usersController.findById(user.getUser_id()))
                .willReturn(new ResponseEntity<>(user,HttpStatus.OK));

        mockMvc.perform(get("/users/user/id/" + user.getUser_id())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", Matchers.hasSize(4)))
                .andExpect(jsonPath("$.user_id", is(1)))
                .andExpect(jsonPath("$.login", is(user.getLogin())))
                .andExpect(jsonPath("$.password", is(user.getPassword())))
                .andExpect(jsonPath("$.admin", is(user.isAdmin())));
    }

    @Test
    @WithMockUser
    public void findByLoginTest() throws Exception{
        user = new User("user", "password", true);

        allUsers = Collections.singletonList(user);

        given(usersController.findByLogin(user.getLogin())).willReturn(allUsers);

        try {
            mockMvc.perform(get("/users/user/login/" + user.getLogin())
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(1)))
                    .andExpect(jsonPath("$[0].login", is(user.getLogin())))
                    .andExpect(jsonPath("$[0].password", is(user.getPassword())))
                    .andExpect(jsonPath("$[0].admin", is(user.isAdmin())));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @WithMockUser
    public void createUserTest() throws Exception{
        user = new User(1, "user", "password", true);
        String json = new Gson().toJson(user);

        mockMvc.perform(
                post("/users/createuser")
                        .with(csrf())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        )
                .andExpect(status().isOk());

    }

    @Test
    @WithMockUser
    public void updateUserTest() throws Exception{
        user = new User(1, "user", "password", true);
        String json = new Gson().toJson(user);

        mockMvc.perform(
                put("/users/createuser")
                        .with(csrf())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        )
                .andExpect(status().isOk());
    }
}
