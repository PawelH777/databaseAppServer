package com.example.vorappServer.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "single_finished_orders")
public class SingleOrdersFinished implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long single_finished_order_id;
    @OneToOne
    @JoinColumn(name = "orderstoryId")
    private OrdersFinished orderstory;
    @OneToOne
    @JoinColumn(name = "dimensionId")
    private Dimiensions dimension;

    @Column(columnDefinition = "Numeric(8,2) NOT NULL")
    private BigDecimal quantity;

    @Column(columnDefinition = "Numeric(8,2) NOT NULL")
    private BigDecimal length;

    @Column(columnDefinition = "Numeric(8,2) NOT NULL")
    private BigDecimal metrs;

    @Column(columnDefinition = "Numeric(8,2) NOT NULL")
    private BigDecimal materials;

    private Boolean finished;



    public Long getSingle_finished_order_id() {
        return single_finished_order_id;
    }

    public void setSingle_finished_order_id(Long single_finished_order_id) {
        this.single_finished_order_id = single_finished_order_id;
    }

    public OrdersFinished getOrderstory() {
        return orderstory;
    }

    public void setOrderstory(OrdersFinished orderstory) {
        this.orderstory = orderstory;
    }

    public Dimiensions getDimension() {
        return dimension;
    }

    public void setDimension(Dimiensions dimension) {
        this.dimension = dimension;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getLength() {
        return length;
    }

    public void setLength(BigDecimal length) {
        this.length = length;
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

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }


    public SingleOrdersFinished(OrdersFinished orderstory, Dimiensions dimension, BigDecimal quantity,
                                BigDecimal length, BigDecimal metrs, BigDecimal materials, Boolean finished) {
        this.orderstory = orderstory;
        this.dimension = dimension;
        this.quantity = quantity;
        this.length = length;
        this.metrs = metrs;
        this.materials = materials;
        this.finished = finished;
    }

    public SingleOrdersFinished() {
    }
}
