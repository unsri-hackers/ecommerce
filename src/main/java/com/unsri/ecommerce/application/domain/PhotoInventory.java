package com.unsri.ecommerce.application.domain;

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

    public PhotoInventory() { }

    public PhotoInventory(String path, String name) {
        this.path = path;
        this.name = name;
    }

    public PhotoInventory(Integer id, String path, String name) {
        this.id = id;
        this.path = path;
        this.name = name;
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
}
