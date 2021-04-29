package com.unsri.ecommerce.controllers;

import java.util.List;

import com.unsri.ecommerce.models.Inventory;
import com.unsri.ecommerce.service.InventoryService;

import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/AddItems")
    public Inventory AddItems(@RequestBody Inventory item){
        return _inventoryService.AddItems(item);
    }

    @PutMapping("/inventories/{id}")
    Inventory updateInventory(@PathVariable int id, @RequestBody Inventory newInventory) {
        return _inventoryService.updateInventory(id, newInventory);
    }
}
