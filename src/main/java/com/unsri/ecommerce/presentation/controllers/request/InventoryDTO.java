package com.unsri.ecommerce.presentation.controllers.request;

import java.util.List;

public class InventoryDTO {
    private int sellerId;
    private String productName;
    private String price;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<PhotoDTO> getPhotos() {
        return photos;
    }

    public void setPhotos(List<PhotoDTO> photoDTOS) {
        this.photos = photoDTOS;
    }
}

