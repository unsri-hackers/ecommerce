package com.unsri.ecommerce.repository;

import com.unsri.ecommerce.models.Inventory;

import org.springframework.data.repository.CrudRepository;

public interface InventoryRepository extends CrudRepository<Inventory, Integer> {
    
}
