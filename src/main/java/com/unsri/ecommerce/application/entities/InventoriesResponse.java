package com.unsri.ecommerce.application.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class InventoriesResponse {
    private List<InventoryResponse> inventories;

    public InventoriesResponse(List<InventoryResponse> inventories) {
        this.inventories = inventories;
    }

    @JsonProperty(value = "inventories")
    public List<InventoryResponse> getInventories() {
        return inventories;
    }

    public void setInventories(List<InventoryResponse> inventories) {
        this.inventories = inventories;
    }
}


