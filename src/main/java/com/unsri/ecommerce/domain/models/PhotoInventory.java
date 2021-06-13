package com.unsri.ecommerce.domain.models;

import javax.persistence.*;

@Entity
@Table(name = "PHOTO_INVENTORY")
public class PhotoInventory {

    @Id
    @GeneratedValue
    @Column
    private Integer id;

    @Column
    private String path;

    @Column
    private String name;

    @Column(name= "fk_inventory_id")
    private Integer fkInventoryId;

    public PhotoInventory() { }

    public PhotoInventory(String path, String name, Integer fkInventoryId) {
        this.path = path;
        this.name = name;
        this.fkInventoryId = fkInventoryId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public Integer getFkInventoryId() {
        return fkInventoryId;
    }

    public void setFkInventoryId(Integer fkSellerId) {
        this.fkInventoryId = fkSellerId;
    }
}
