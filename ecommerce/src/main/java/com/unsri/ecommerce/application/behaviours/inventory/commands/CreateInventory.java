package com.unsri.ecommerce.application.behaviours.inventory.commands;

import com.unsri.ecommerce.application.behaviours.BaseCommand;
import com.unsri.ecommerce.domain.models.Inventory;
import com.unsri.ecommerce.infrastructure.repository.InventoryRepository;

public class CreateInventory implements BaseCommand<Inventory> {

    private Inventory _inventory;
    private InventoryRepository _repository;

    public CreateInventory(Inventory inventory, InventoryRepository inventoryRepository) {
        _inventory = inventory;
        _repository = inventoryRepository;
    }

    @Override
    public Inventory execute() {
        return _repository.save(_inventory);
    }
    
}
