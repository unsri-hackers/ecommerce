package com.unsri.ecommerce.application.behaviours.inventory.commands;

import com.unsri.ecommerce.domain.models.Inventory;
import com.unsri.ecommerce.infrastructure.repository.InventoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.util.Assert;

import static org.mockito.Mockito.*;

public class CreateInventoryTests {

    @Mock
    private InventoryRepository inventoryRepository;

    @InjectMocks
    private CreateInventory createInventory;

    private Inventory inventory;

    @BeforeEach
    public void setup() {
    }

    @Test
    public void CreateInventoryTests_ReturnSuccess() {
        // Arrange
        inventory = new Inventory();
        inventory.setItemName("Apple");
        inventory.setPrice(1000.0);

        when(inventoryRepository.save(inventory)).thenReturn(inventory);

        // Act
        Inventory expectedResult = createInventory.execute();

        // Assert
        Assert.isTrue(expectedResult != null, "should not null");
        Assert.isTrue(expectedResult.getItemName().equals(inventory.getItemName()), "item name should be same");
        Assert.isTrue(expectedResult.getPrice().equals(inventory.getPrice()), "price should be same");
 
        // Verify
        verify(inventoryRepository, times(1)).save(inventory);
    }

}
