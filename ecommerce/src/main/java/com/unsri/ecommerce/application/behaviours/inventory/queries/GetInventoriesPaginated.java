package com.unsri.ecommerce.application.behaviours.inventory.queries;

import com.unsri.ecommerce.application.behaviours.BaseCommand;
import com.unsri.ecommerce.domain.models.Inventory;
import com.unsri.ecommerce.infrastructure.repository.InventoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GetInventoriesPaginated implements BaseCommand<Page<Inventory>> {

    private InventoryRepository inventoryRepository;
    private Pageable pageable;

    public GetInventoriesPaginated(InventoryRepository inventoryRepository, Pageable pageable) {
        this.inventoryRepository = inventoryRepository;
        this.pageable = pageable;
    }

    @Override
    public Page<Inventory> execute(Optional<Page<Inventory>> param) {
        return inventoryRepository.findAll(pageable);
    }
}
