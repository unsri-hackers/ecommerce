package com.unsri.ecommerce.controllers;

import java.util.ArrayList;
import java.util.List;

import com.unsri.ecommerce.models.Inventory;
import com.unsri.ecommerce.service.InventoryService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryController {
    
    private InventoryService _inventoryService;

    public InventoryController(InventoryService inventoryService) {
        _inventoryService = inventoryService;
    }

    @GetMapping("/GetAll")
    public List<Inventory> GetHelloWorld() {
        return _inventoryService.GetAllItems();
    }
}
