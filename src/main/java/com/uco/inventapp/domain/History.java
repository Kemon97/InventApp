package com.uco.inventapp.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "users")
@Getter
@Setter
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product")
    private String product;
    @Column(name = "event_type")
    private String event_type;
    @Column(name = "date")
    private String date;
    public History() {
    }
    public History(String product, String event_type, String date) {
        this.product = product;
        this.event_type = event_type;
        this.date = date;
    }
    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getEventType() {
        return event_type;
    }

    public void setEventType (String event_type) { this.event_type = event_type; }

    public String getDate() {
        return date;
    }

    public void setDate(Date setDate) {
        this.date =date;
    }

}