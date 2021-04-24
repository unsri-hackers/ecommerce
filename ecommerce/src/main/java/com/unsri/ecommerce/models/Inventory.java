package com.unsri.ecommerce.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Inventory {

    @Id
    @Column
    private String ID;
    
    @Column
    private String ItemName;
    
    @Column
    private Double Price;

    public Inventory(String Id, String itemName, Double price) {
        this.ID = Id;
        this.ItemName = itemName;
        this.Price = price;
    }

    public String GetId() {
        return this.ID;
    }

    public String GetItemName() {
        return this.ItemName;
    }

    public Double GetPrice() {
        return this.Price;
    }
}
