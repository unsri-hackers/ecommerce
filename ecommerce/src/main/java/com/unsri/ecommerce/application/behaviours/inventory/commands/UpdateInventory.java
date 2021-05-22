package com.unsri.ecommerce.application.behaviours.inventory.commands;

import com.unsri.ecommerce.application.behaviours.BaseCommand;
import com.unsri.ecommerce.domain.models.Inventory;
import com.unsri.ecommerce.infrastructure.repository.InventoryRepository;

import java.util.Optional;

public class UpdateInventory implements BaseCommand<Inventory> {

    private Integer _existingId;
    private InventoryRepository _inventoryRepository;

    public UpdateInventory(Integer existingId, InventoryRepository inventoryRepository) {
        _existingId = existingId;
        _inventoryRepository = inventoryRepository;
    }

    @Override
    public Inventory execute(Optional<Inventory> param) {
        return _inventoryRepository.findById(_existingId)
                .map(inventory -> {
                    inventory.setItemName(param.get().getItemName());
                    inventory.setPrice(param.get().getPrice());
                    return _inventoryRepository.save(inventory);
                })
                .orElseGet(() -> {
                    return new Inventory();
                });
    }
}
