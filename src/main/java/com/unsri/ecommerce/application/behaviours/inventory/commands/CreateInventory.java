package com.unsri.ecommerce.application.behaviours.inventory.commands;

import com.unsri.ecommerce.application.behaviours.BaseCommand;
import com.unsri.ecommerce.application.domain.Inventory;
import com.unsri.ecommerce.infrastructure.repository.InventoryRepository;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreateInventory implements BaseCommand<Inventory> {

    private InventoryRepository _repository;

    public CreateInventory(InventoryRepository inventoryRepository) {
        _repository = inventoryRepository;
    }

    @Override
    public Inventory execute(Optional<Inventory> param) {
        return _repository.save(param.get());
    }
}
