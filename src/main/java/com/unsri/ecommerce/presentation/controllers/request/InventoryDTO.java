package com.unsri.ecommerce.presentation.controllers.request;

import java.util.List;

public class InventoryDTO {
    private int sellerId;
    private String productName;
    private String productPrice;
    private List<PhotoDTO> photos;

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public List<PhotoDTO> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PhotoDTO> photoDTOS) {
        this.photos = photoDTOS;
    }
}

