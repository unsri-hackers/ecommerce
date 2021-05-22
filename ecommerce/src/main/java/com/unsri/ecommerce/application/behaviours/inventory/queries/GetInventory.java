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
        List<Inventory> items = new ArrayList<Inventory>();
        _repository.findAll().forEach(item -> items.add(item));

        return items;
    }
}
