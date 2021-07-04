package com.unsri.ecommerce.application.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "INVENTORY")
public class Inventory {

    @Id
    @GeneratedValue
    @Column
    private Integer id;

    @Column
    private String itemName;

    @Column
    private Double price;

    @Column(name = "fk_seller_id")
    private Integer fkSellerId;

    @OneToMany(targetEntity = PhotoInventory.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_inventory_id", referencedColumnName = "id")
    private List<PhotoInventory> photoInventories;

    public Inventory() {

    }

    public Inventory(String itemName, Double price, Integer fkSellerId, List<PhotoInventory> photoInventories) {
        this.itemName = itemName;
        this.price = price;
        this.fkSellerId = fkSellerId;
        this.photoInventories = photoInventories;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public List<PhotoInventory> getPhotos() {
        return photoInventories;
    }

    public void setPhotos(List<PhotoInventory> photoInventories) {
        this.photoInventories = photoInventories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inventory inventory = (Inventory) o;
        return Objects.equals(id, inventory.id) && Objects.equals(itemName, inventory.itemName) && Objects.equals(price, inventory.price) && Objects.equals(fkSellerId, inventory.fkSellerId) && Objects.equals(photoInventories, inventory.photoInventories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, itemName, price, fkSellerId, photoInventories);
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", price=" + price +
                ", fkSellerId=" + fkSellerId +
                ", photoInventories=" + photoInventories +
                '}';
    }
}
