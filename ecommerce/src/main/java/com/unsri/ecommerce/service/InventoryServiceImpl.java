package com.unsri.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import com.unsri.ecommerce.models.Inventory;
import com.unsri.ecommerce.repository.InventoryRepository;

import org.springframework.stereotype.Service;

@Service
public class InventoryServiceImpl implements InventoryService {

    private InventoryRepository _repository;

    public InventoryServiceImpl(InventoryRepository repository) {
        _repository = repository;
    }

    @Override
    public List<Inventory> GetAllItems() {
        
        List<Inventory> items = new ArrayList<Inventory>();
        _repository.findAll().forEach(item -> items.add(item));

        return items;
    }

    @Override
    public Inventory AddItems(Inventory item) {
        return _repository.save(item);
    }

    
    
}
