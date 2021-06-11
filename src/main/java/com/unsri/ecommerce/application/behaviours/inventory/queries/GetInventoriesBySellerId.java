package com.unsri.ecommerce.application.behaviours.inventory.queries;

import com.unsri.ecommerce.application.behaviours.BaseCommand;
import com.unsri.ecommerce.domain.models.Inventory;
import com.unsri.ecommerce.infrastructure.repository.InventoryRepository;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public class GetInventoriesBySellerId implements BaseCommand<List<Inventory>> {

    private InventoryRepository inventoryRepository;
    private Integer sellerId;
    private Pageable pageable;

    public GetInventoriesBySellerId(InventoryRepository inventoryRepository, Integer sellerId, Pageable pageable) {
        this.inventoryRepository = inventoryRepository;
        this.sellerId = sellerId;
        this.pageable = pageable;
    }

    @Override
    public List<Inventory> execute(Optional<List<Inventory>> param) {
        List<Inventory> inventories = inventoryRepository.findAllPaginatedByfkSellerId(this.sellerId, pageable);

        return inventories;
    }
}
