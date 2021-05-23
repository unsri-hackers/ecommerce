package com.unsri.ecommerce.application.behaviours.inventory.queries;

import com.unsri.ecommerce.application.behaviours.BaseCommand;
import com.unsri.ecommerce.domain.models.Inventory;
import com.unsri.ecommerce.infrastructure.repository.InventoryRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public class GetInventoriesPaginatedByItemName implements BaseCommand<List<Inventory>> {

    private InventoryRepository inventoryRepository;
    private String itemName;
    private Pageable pageable;

    public GetInventoriesPaginatedByItemName(InventoryRepository inventoryRepository, String itemName, Pageable pageable) {
        this.inventoryRepository = inventoryRepository;
        this.itemName = itemName;
        this.pageable = pageable;
    }

    @Override
    public List<Inventory> execute(Optional<List<Inventory>> param) {
        return inventoryRepository.findAllPaginatedByItemName(this.itemName, pageable);
    }
}
