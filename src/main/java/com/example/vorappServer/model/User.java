package com.example.vorappServer.model;


import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Paweł on 2018-02-02.
 */

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long user_id;

    private String login;
    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String password;
    @Column(nullable = false)
    private boolean admin;

    public long getUser_id() {
        return user_id;
    }
    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public User(){

    }

    public User(String a, String b, boolean c){

        login = a;
        password = b;
        admin = c;
    }

    public User(long user_id, String login, String password, boolean admin) {
        this.user_id = user_id;
        this.login = login;
        this.password = password;
        this.admin = admin;
    }
}
