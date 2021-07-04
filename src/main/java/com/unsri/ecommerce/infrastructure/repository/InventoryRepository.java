package com.unsri.ecommerce.infrastructure.repository;

import com.unsri.ecommerce.application.domain.Inventory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
    List<Inventory> findAllPaginatedByItemName(String itemName, Pageable pageable);

    List<Inventory> findAllPaginatedByfkSellerId(Integer sellerId, Pageable pageable);
}
