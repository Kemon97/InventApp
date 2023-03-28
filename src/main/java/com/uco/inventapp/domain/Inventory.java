package com.uco.inventapp.domain;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "inventories")
@Getter
@Setter
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "id", nullable = false)
    private Long id;

    @Column(name= "fk_client")
    private String fk_client;

    @Column(name = "fk_product")
    private String fk_product;

    @Column(name = "state")
    private boolean state;

    public Inventory() {
    }

    public Inventory(Long id,String fk_client,String fk_product,boolean state){
        this.id = id;
        this.fk_client = fk_client;
        this.fk_product = fk_product;
        this.state = state;
    }
    public Long getId() { return id;}

    public void setId(Long id){this.id =id;}

    public String getFk_client(){return  fk_client;}

    public void setFk_client(String fk_client){this.fk_client = fk_client;}

    public String getFk_product(){return  fk_product;}

    public void setFk_product(String fk_product){this.fk_product = fk_product;}

    public  boolean get_State(){return state;}

    public void set_State(boolean state){this.state = state;}
}
