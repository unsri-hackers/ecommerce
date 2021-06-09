package com.unsri.ecommerce.domain.models;

import java.util.List;

public class InventoryResponse {

    private Integer sellerId;
    private String sellerName;
    private Integer productId;
    private String productName;
    private Double price;
    private List<Photo> photos;

    public InventoryResponse() {
    }

    public InventoryResponse(Integer sellerId, String sellerName, Integer productId, String productName, Double price, List<Photo> photos) {
        this.sellerId = sellerId;
        this.sellerName = sellerName;
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.photos = photos;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "vendor_id=" + sellerId + "," +
                "vendor_name=" + sellerName + "," +
                "product_id=" + productId + "," +
                "product_name=" + productName + "," +
                "photos=" + photos +
                '}';
    }
}