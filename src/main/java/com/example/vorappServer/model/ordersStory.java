package com.example.vorappServer.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by Paweł on 2018-02-03.
 */

@Data
@Entity
@Table(name = "ordershistory")
public class ordersStory implements Serializable {


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

    @Column(nullable = true)
    private String Note;

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

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public ordersStory(Dimiensions dimension, Client client, BigDecimal metrs, BigDecimal materials, LocalDate receive_date, LocalDate order_date, String note) {
        this.dimension = dimension;
        this.client = client;
        this.metrs = metrs;
        this.materials = materials;
        this.receive_date = receive_date;
        this.order_date = order_date;
        Note = note;
    }

    public ordersStory() {
    }

    public ordersStory(Long order_id, Dimiensions dimension, Client client, BigDecimal metrs, BigDecimal materials, LocalDate receive_date, LocalDate order_date, String note) {
        this.order_id = order_id;
        this.dimension = dimension;
        this.client = client;
        this.metrs = metrs;
        this.materials = materials;
        this.receive_date = receive_date;
        this.order_date = order_date;
        Note = note;
    }

    public Object[] toObject(){

        ordersStory ord = new ordersStory(dimension, client, metrs, materials, receive_date, order_date, Note);
        Dimiensions dim = new Dimiensions(dimension.getDimension_id(), dimension.getFirstDimension(), dimension.getSecondDimension(), dimension.getThickness(), dimension.getWeight());
        Client cli = new Client(client.getClient_id(), client.getFirmName());

        return new Object[]{
                ord, dim, cli
        };
    }
}
