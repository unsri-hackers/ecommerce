package com.unsri.ecommerce.presentation.payload.request;

import com.unsri.ecommerce.domain.models.PhotoInventory;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.List;

public class UploadInventoryRequest {
    @NotEmpty(message = "name must be filled")
    private String productName;

    @Positive(message = "price must be more than zero")
    private Double price;

    private List<PhotoInventory> photos;

    public UploadInventoryRequest() {
    }

    public UploadInventoryRequest(String productName, Double price, List<PhotoInventory> photos) {
        this.productName = productName;
        this.price = price;
        this.photos = photos;
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

    public List<PhotoInventory> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PhotoInventory> photos) {
        this.photos = photos;
    }
}
