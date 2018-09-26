package com.example.vorappServer.model;

import com.example.vorappServer.data.TrayStatus;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "trays")
public class Trays implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tray_id;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "single_order_id")
    private SingleOrders single_order;
    private String tray_name;
    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private TrayStatus tray_status;

    public Long getTray_id() {
        return tray_id;
    }

    public void setTray_id(Long tray_id) {
        this.tray_id = tray_id;
    }

    public SingleOrders getSingle_order() {
        return single_order;
    }

    public void setSingle_order(SingleOrders single_order) {
        this.single_order = single_order;
    }

    public String getTray_name() {
        return tray_name;
    }

    public void setTray_name(String tray_name) {
        this.tray_name = tray_name;
    }

    public TrayStatus getTray_status() {
        return tray_status;
    }

    public void setTray_status(TrayStatus tray_status) {
        this.tray_status = tray_status;
    }

    public Trays(Long tray_id, SingleOrders single_order, String tray_name) {
        this.tray_id = tray_id;
        this.single_order = single_order;
        this.tray_name = tray_name;
        this.tray_status = TrayStatus.WAITING;
    }

    public Trays(SingleOrders single_order, String tray_name) {
        this.single_order = single_order;
        this.tray_name = tray_name;
        this.tray_status = TrayStatus.WAITING;
    }

    public Trays() {
    }
}
