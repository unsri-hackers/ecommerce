package com.unsri.ecommerce.presentation.controllers;

import java.util.List;

import com.unsri.ecommerce.application.behaviours.inventory.commands.CreateInventory;
import com.unsri.ecommerce.application.behaviours.inventory.commands.UpdateInventory;
import com.unsri.ecommerce.application.behaviours.inventory.queries.GetInventory;
import com.unsri.ecommerce.domain.models.Inventory;
import com.unsri.ecommerce.infrastructure.repository.InventoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class InventoryController {

    private InventoryRepository _InventoryRepository;

    public InventoryController(InventoryRepository inventoryRepository) {
        _InventoryRepository = inventoryRepository;
    }

    @GetMapping("/inventories")
    public List<Inventory> GetHelloWorld() {
        GetInventory command = new GetInventory(_InventoryRepository);
        return command.execute();
    }

    @PostMapping("/inventories")
    public Inventory AddItems(@RequestBody Inventory item){
        CreateInventory command = new CreateInventory(item, _InventoryRepository);

        return command.execute();
    }

    @PutMapping("/inventories/{id}")
    Inventory updateInventory(@PathVariable int id, @RequestBody Inventory newInventory) {
        UpdateInventory command = new UpdateInventory(id, newInventory, _InventoryRepository);
        return command.execute();
    }
}
