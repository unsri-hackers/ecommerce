package com.unsri.ecommerce.domain.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "VENDOR")
public class Vendor {

    @Id
    @GeneratedValue
    @Column
    private int id;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private byte avatar;

    @Column
    private Date birthDate;

    @Column
    private String gender;

    @Column
    private int vendorType;

    public Vendor() {

    }

    public Vendor(
        int id,
        String email,
        String password,
        String firstName,
        String lastName,
        byte avatar,
        Date birthDate,
        String gender,
        int vendorType
    ) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatar = avatar;
        this.birthDate = birthDate;
        this.gender = gender;
        this.vendorType = vendorType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public byte getAvatar() {
        return avatar;
    }

    public void setAvatar(byte avatar) {
        this.avatar = avatar;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getVendorType() {
        return vendorType;
    }

    public void setVendorType(int vendorType) {
        this.vendorType = vendorType;
    }
}
