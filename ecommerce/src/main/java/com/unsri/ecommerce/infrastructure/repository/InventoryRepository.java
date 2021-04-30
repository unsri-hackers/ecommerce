package com.unsri.ecommerce.infrastructure.repository;

import com.unsri.ecommerce.domain.models.Inventory;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends CrudRepository<Inventory, Integer> {
    
}
