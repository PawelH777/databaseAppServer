package com.example.vorappServer.extraClass;

/**
 * Created by Paweł on 2018-04-03.
 */
public class ClientsHelpClass {

    private Long client_id;

    private String firmName;

    public String getFirmName() {
        return firmName;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }

    public Long getClient_id() {
        return client_id;
    }

    public void setClient_id(Long client_id) {
        this.client_id = client_id;
    }

    public ClientsHelpClass(Long client_id, String firmName) {
        this.client_id = client_id;
        this.firmName = firmName;
    }

    public ClientsHelpClass() {
    }
}
