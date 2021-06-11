package com.unsri.ecommerce.domain.models;

import javax.persistence.*;

@Entity
@Table(name = "PHOTO_SELLER")
public class PhotoSeller {

    @Id
    @GeneratedValue
    @Column
    private Integer id;

    @Column
    private String path;

    @Column
    private String name;

    public PhotoSeller() { }

    public PhotoSeller(Integer id, String path, String name) {
        this.id = id;
        this.path = path;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
