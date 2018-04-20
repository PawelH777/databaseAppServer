package com.example.vorappServer.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by Paweł on 2018-02-03.
 */


@Entity
@Table(name = "orders")
public class Orders<T> implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long order_id;
    @OneToOne
    @JoinColumn(name = "dimensionId")
    private Dimiensions dimension;
    @OneToOne
    @JoinColumn(name = "clientId")
    private Client client;

    @Column(columnDefinition = "Numeric(8,3) NOT NULL")
    private BigDecimal metrs;

    @Column(columnDefinition = "Numeric(8,3) NOT NULL")
    private BigDecimal materials;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate receive_date;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate order_date;

    private String Note;

    @Override
    public String toString(){
        String result = String.format("Zamówienie[order_id=%d, metrs=%f, materials=%f, receive_date=%td, order_date=%td, Note=%s]%n",
                order_id, metrs, materials, receive_date, order_date, Note);
        if(dimension != null){
            result += String.format("Wymiary[dimension_id=%d, first_dimension=%f, second_dimension=%f, thickness=%f, weight=%f]%n",
                    dimension.getDimension_id(), dimension.getFirstDimension(), dimension.getSecondDimension(), dimension.getThickness(), dimension.getWeight());
        }
        if(client !=null){
            result += String.format("Klient[client_id=%d, firmName=%s]%n", client.getClient_id(), client.getFirmName());
        }
        return result;
    }

    public Object[] toObject(){

        Orders ord = new Orders(dimension, client, metrs, materials, receive_date, order_date, Note);
        Dimiensions dim = new Dimiensions(dimension.getDimension_id(), dimension.getFirstDimension(), dimension.getSecondDimension(), dimension.getThickness(), dimension.getWeight());
        Client cli = new Client(client.getClient_id(), client.getFirmName());

        return new Object[]{
               ord, dim, cli
        };
    }



    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public Dimiensions getDimension() {
        return dimension;
    }

    public void setDimension(Dimiensions dimension) {
        this.dimension = dimension;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public BigDecimal getMetrs() {
        return metrs;
    }

    public void setMetrs(BigDecimal metrs) {
        this.metrs = metrs;
    }

    public BigDecimal getMaterials() {
        return materials;
    }

    public void setMaterials(BigDecimal materials) {
        this.materials = materials;
    }

    public LocalDate getReceive_date() {
        return receive_date;
    }

    public void setReceive_date(LocalDate receive_date) {
        this.receive_date = receive_date;
    }

    public LocalDate getOrder_date() {
        return order_date;
    }

    public void setOrder_date(LocalDate order_date) {
        this.order_date = order_date;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public Orders(Dimiensions dimension, Client client, BigDecimal metrs, BigDecimal materials, LocalDate receive_date, LocalDate order_date, String note) {
        this.dimension = dimension;
        this.client = client;
        this.metrs = metrs;
        this.materials = materials;
        this.receive_date = receive_date;
        this.order_date = order_date;
        Note = note;
    }

    public Orders(Long order_id, Dimiensions dimension, Client client, BigDecimal metrs, BigDecimal materials, LocalDate receive_date, LocalDate order_date, String note) {
        this.order_id = order_id;
        this.dimension = dimension;
        this.client = client;
        this.metrs = metrs;
        this.materials = materials;
        this.receive_date = receive_date;
        this.order_date = order_date;
        Note = note;
    }

    public Orders() {
    }
}
