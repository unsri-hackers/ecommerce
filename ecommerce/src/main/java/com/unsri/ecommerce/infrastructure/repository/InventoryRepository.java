package com.unsri.ecommerce.infrastructure.repository;

import com.unsri.ecommerce.domain.models.Inventory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
    
}
