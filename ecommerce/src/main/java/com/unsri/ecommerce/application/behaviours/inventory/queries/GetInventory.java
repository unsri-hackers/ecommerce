package com.unsri.ecommerce.application.behaviours.inventory.queries;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.unsri.ecommerce.application.behaviours.BaseCommand;
import com.unsri.ecommerce.domain.models.Inventory;
import com.unsri.ecommerce.infrastructure.repository.InventoryRepository;

public class GetInventory implements BaseCommand<List<Inventory>> {

    private InventoryRepository _repository;

    public GetInventory(InventoryRepository inventoryRepository) {
        _repository = inventoryRepository;
    }

    @Override
    public List<Inventory> execute(Optional<List<Inventory>> param) {
        return _repository.findAll();
    }
}
