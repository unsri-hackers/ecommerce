package com.unsri.ecommerce.domain.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "INVENTORY")
public class Inventory {

    @Id
    @GeneratedValue
    @Column
    private int id;

    @Column
    private String itemName;

    @Column
    private Double price;

    @Column(name = "fk_seller_id")
    private Integer fkSellerId;

    @OneToMany(targetEntity = Photo.class)
    @JoinColumn(name = "fk_inventory_id", referencedColumnName = "id")
    private List<Photo> photos;

    public Inventory() {

    }

    public Inventory(String itemName, Double price) {
        this.itemName = itemName;
        this.price = price;
    }

    public Inventory(String itemName, Double price, Integer fkSellerId) {
        this.itemName = itemName;
        this.price = price;
        this.fkSellerId = fkSellerId;
    }

    public Inventory(String itemName, Double price, Integer fkSellerId, List<Photo> photos) {
        this.itemName = itemName;
        this.price = price;
        this.fkSellerId = fkSellerId;
        this.photos = photos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getFkSellerId() {
        return fkSellerId;
    }

    public void setFkSellerId(Integer sellerId) {
        this.fkSellerId = sellerId;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }
}
