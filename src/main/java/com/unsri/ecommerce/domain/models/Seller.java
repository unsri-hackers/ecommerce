package com.unsri.ecommerce.domain.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "SELLER")
public class Seller {

    @Id
    @GeneratedValue
    @Column
    private Integer id;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String username;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String avatar;

    @Column
    private Date birthDate;

    @Column
    private String gender;

    @Column(name = "vendor_type")
    private Integer type;

    @Column
    private boolean isActivated;

    @Column
    private String verificationCode;

    @OneToMany(targetEntity = Inventory.class)
    @JoinColumn(name = "fk_seller_id", referencedColumnName = "id")
    private List<Inventory> inventories;

    public Seller() {}

    public Seller(
        Integer id,
        String username,
        String email,
        String password,
        String firstName,
        String lastName,
        String avatar,
        Date birthDate,
        String gender,
        Integer type,
        List<Inventory> inventories,
        boolean isActivated,
        String verificationCode
    ) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatar = avatar;
        this.birthDate = birthDate;
        this.gender = gender;
        this.type = type;
        this.inventories = inventories;
        this.isActivated = isActivated;
        this.verificationCode = verificationCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<Inventory> getInventories() {
        return inventories;
    }

    public void setInventories(List<Inventory> inventories) {
        this.inventories = inventories;
    }

    public boolean getIsActivated() {
        return isActivated;
    }

    public void setIsActivated(boolean isActivated) {
        this.isActivated = isActivated;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }
}
