package com.unsri.ecommerce.domain.models;

import javax.persistence.*;

@Entity
@Table(name = "JWT_USER")
public class JwtUser {

    @Id
    @GeneratedValue
    @Column
    private int id;

    @Column
    private int userId;

    @Column
    private String deviceId;

    @Column
    private String jwt;

    @Column
    private boolean isInvalidated;

    public JwtUser() {

    }

    public JwtUser(int userId, String deviceId, String jwt, boolean isInvalidated) {
        this.userId = userId;
        this.deviceId = deviceId;
        this.jwt = jwt;
        this.isInvalidated = isInvalidated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public boolean isInvalidated() {
        return isInvalidated;
    }

    public void setInvalidated(boolean invalidated) {
        isInvalidated = invalidated;
    }

}
