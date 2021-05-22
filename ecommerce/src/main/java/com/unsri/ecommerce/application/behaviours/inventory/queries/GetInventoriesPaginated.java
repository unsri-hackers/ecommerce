package com.unsri.ecommerce.application.behaviours.inventory.queries;

import com.unsri.ecommerce.application.behaviours.BaseCommand;
import com.unsri.ecommerce.domain.models.Inventory;
import com.unsri.ecommerce.infrastructure.repository.InventoryRepository;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public class GetInventoriesPaginated implements BaseCommand<List<Inventory>> {

    private InventoryRepository inventoryRepository;
    private int page, size;

    public GetInventoriesPaginated(InventoryRepository inventoryRepository, int page, int size) {
        this.inventoryRepository = inventoryRepository;
        this.page = page;
        this.size = size;
    }

    @Override
    public List<Inventory> execute() {
        return inventoryRepository.findAll(PageRequest.of(this.page, this.size)).getContent();
    }
}
