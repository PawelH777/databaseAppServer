package com.example.vorappServer.model;


import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Paweł on 2018-02-03.
 */

@Entity
@Table(name = "clients")
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long client_id;

    @Column(name = "firm_name")
    private String firmName;

    public long getClient_id() {
        return client_id;
    }

    public void setClient_id(long client_id) {
        this.client_id = client_id;
    }

    public String getFirmName() {
        return firmName;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }

    public Client() {
    }

    public Client(String firmName) {
        this.firmName = firmName;
    }

    public Client(long client_id, String firmName) {
        this.client_id = client_id;
        this.firmName = firmName;
    }
}
