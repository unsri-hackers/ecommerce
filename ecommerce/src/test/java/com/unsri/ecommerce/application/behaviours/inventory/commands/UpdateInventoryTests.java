package com.unsri.ecommerce.application.behaviours.inventory.commands;

import com.unsri.ecommerce.domain.models.Inventory;
import com.unsri.ecommerce.infrastructure.repository.InventoryRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.util.Assert;

import static org.mockito.Mockito.*;

public class UpdateInventoryTests {

    @Mock
    private InventoryRepository inventoryRepository;

    private Inventory existingInventory;

    private Inventory newInventory;

    private UpdateInventory updateInventory;

    public UpdateInventoryTests() {
        MockitoAnnotations.openMocks(this);
        existingInventory = new Inventory();
        existingInventory.setId(1);
        existingInventory.setItemName("Apple");
        existingInventory.setPrice(1000.0);

        newInventory = new Inventory();
        newInventory.setId(1);
        newInventory.setItemName("Apple 2.0");
        newInventory.setPrice(1200.0);

        updateInventory = new UpdateInventory(existingInventory.getId(), newInventory, inventoryRepository);
    }

    @Test
    public void UpdateInventoryTests_ReturnSuccess() {
        // Arrange
        when(inventoryRepository.findById(existingInventory.getId()))
            .thenReturn(java.util.Optional.of(existingInventory));

        when(inventoryRepository.save(newInventory))
            .thenReturn(newInventory);

        // Act
        Inventory expectedResult = updateInventory.execute();

        // Assert
        Assert.isTrue(expectedResult != null, "should not null");

        // Verify
        verify(inventoryRepository, times(1)).findById(existingInventory.getId());
        verify(inventoryRepository, times(1)).save(existingInventory);
    }
}
