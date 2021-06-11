package com.unsri.ecommerce.presentation.payload.request;

import com.unsri.ecommerce.domain.models.PhotoInventory;

import java.util.List;

public class UploadInventoryRequest {
    private String productName;
    private Double price;
    private List<PhotoInventory> photo;

    public UploadInventoryRequest() {
    }

    public UploadInventoryRequest(String productName, Double price, List<PhotoInventory> photo) {
        this.productName = productName;
        this.price = price;
        this.photo = photo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<PhotoInventory> getPhoto() {
        return photo;
    }

    public void setPhoto(List<PhotoInventory> photo) {
        this.photo = photo;
    }
}
