package com.unsri.ecommerce.application.behaviours.inventory.queries;

import com.unsri.ecommerce.application.behaviours.BaseCommand;
import com.unsri.ecommerce.domain.models.Inventory;
import com.unsri.ecommerce.infrastructure.repository.InventoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GetInventoriesPaginated implements BaseCommand<List<Inventory>> {

    private InventoryRepository inventoryRepository;
    private Pageable pageable;

    public GetInventoriesPaginated(InventoryRepository inventoryRepository, Pageable pageable) {
        this.inventoryRepository = inventoryRepository;
        this.pageable = pageable;
    }

    @Override
    public List<Inventory> execute(Optional<List<Inventory>> param) {
        return this.inventoryRepository.findAll(pageable).getContent();
    }
}
