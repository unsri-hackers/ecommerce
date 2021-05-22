package com.unsri.ecommerce.presentation.controllers;

import java.util.List;

import com.unsri.ecommerce.application.behaviours.inventory.commands.CreateInventory;
import com.unsri.ecommerce.application.behaviours.inventory.commands.UpdateInventory;
import com.unsri.ecommerce.application.behaviours.inventory.queries.GetInventory;
import com.unsri.ecommerce.application.behaviours.inventory.queries.GetInventoriesPaginated;
import com.unsri.ecommerce.application.behaviours.inventory.queries.GetInventoriesPaginatedByItemName;
import com.unsri.ecommerce.domain.models.Inventory;
import com.unsri.ecommerce.infrastructure.repository.InventoryRepository;

import org.springframework.web.bind.annotation.*;

@RestController
public class InventoryController {
    
    private InventoryRepository _InventoryRepository;

    public InventoryController(InventoryRepository inventoryRepository) {
        _InventoryRepository = inventoryRepository;
    }

    @GetMapping("/inventories")
    public List<Inventory> getInventory() {
        GetInventory command = new GetInventory(_InventoryRepository);
        return command.execute(java.util.Optional.empty());
    }

    @GetMapping(value = "/inventories/paging/keyword")
    public List<Inventory> getInventoriesPaginatedByKeyword(
        @RequestParam(value = "search") String keyword,
        @RequestParam(value = "page", defaultValue = "0") int page,
        @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        GetInventoriesPaginatedByItemName command = new GetInventoriesPaginatedByItemName(_InventoryRepository, keyword, page, size);
        return command.execute(java.util.Optional.empty());
    }

    @GetMapping(value = "/inventories/paging")
    public List<Inventory> getInventoriesPaginated(
        @RequestParam(value = "page") int page,
        @RequestParam(value = "size", defaultValue = "10") int size
    ){
        GetInventoriesPaginated command = new GetInventoriesPaginated(_InventoryRepository, page, size);
        return command.execute(java.util.Optional.empty());
    }

    @PostMapping(value = "/inventories")
    public Inventory addItem(@RequestBody Inventory item) {
        CreateInventory command = new CreateInventory(_InventoryRepository);
        return command.execute(java.util.Optional.ofNullable(item));
    }

    @PutMapping("/inventories/{id}")
    Inventory updateInventory(@PathVariable int id, @RequestBody Inventory newInventory) {
        UpdateInventory command = new UpdateInventory(id, _InventoryRepository);
        return command.execute(java.util.Optional.ofNullable(newInventory));
    }
}
