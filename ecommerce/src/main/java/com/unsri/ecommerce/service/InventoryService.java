package com.unsri.ecommerce.service;

import java.util.List;

import com.unsri.ecommerce.models.Inventory;

public interface InventoryService {
    List<Inventory> GetAllItems();
    Inventory AddItems(Inventory item);
    Inventory updateInventory(int id, Inventory newIntentory);
}
