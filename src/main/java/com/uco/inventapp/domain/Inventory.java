package com.uco.inventapp.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "inventory")
@Getter
@Setter
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name= "fk_client", nullable = false)
    private int fk_client;

    @Column(name = "fk_product", nullable = false)
    private int fk_product;

    @Column(name = "state",nullable = false)
    private boolean state;

    public Inventory(int id,int fk_client,int fk_product,boolean state){
        this.id = id;
        this.fk_client = fk_client;
        this.fk_product = fk_product;
        this.state = state;
    }
    public int getId() { return id;}

    public void setId(int id){this.id =id;}

    public  int getFk_client(){return  fk_client;}

    public void setFk_client(int fk_client){this.fk_client = fk_client;}

    public  int getFk_product(){return  fk_product;}

    public void setFk_product(int fk_product){this.fk_product = fk_product;}

    public  boolean get_State(){return state;}

    public void set_State(boolean state){this.state = state;}
}
