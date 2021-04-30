package com.unsri.ecommerce.application.behaviours.inventory.commands;

import com.unsri.ecommerce.application.behaviours.BaseCommand;
import com.unsri.ecommerce.domain.models.Inventory;
import com.unsri.ecommerce.infrastructure.repository.InventoryRepository;

public class UpdateInventory implements BaseCommand<Inventory> {

    private Integer _existingId;
    private Inventory _newinventory;
    private InventoryRepository _inventoryRepository;

    public UpdateInventory(Integer existingId, Inventory newInventory, InventoryRepository inventoryRepository) {
        _existingId = existingId;
        _newinventory = newInventory;
        _inventoryRepository = inventoryRepository;
    }

    @Override
    public Inventory execute() {
        return _inventoryRepository.findById(_existingId)
            .map(inventory -> {
                inventory.setItemName(_newinventory.getItemName());
                inventory.setPrice(_newinventory.getPrice());
                return _inventoryRepository.save(inventory);
            })
            .orElseGet(() -> {
                return new Inventory();
            });
    }
    
}
