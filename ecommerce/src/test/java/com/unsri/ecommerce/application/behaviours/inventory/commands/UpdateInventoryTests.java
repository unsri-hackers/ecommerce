package com.unsri.ecommerce.application.behaviours.inventory.commands;

import com.unsri.ecommerce.domain.models.Inventory;
import com.unsri.ecommerce.infrastructure.repository.InventoryRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import static org.mockito.Mockito.*;

@SpringBootTest
public class UpdateInventoryTests {

    @Mock
    private Inventory newInventory;

    @Mock
    private InventoryRepository inventoryRepository;

    @BeforeAll
    public static void setup() {

    }

    @Test
    public void UpdateInventoryTests_ReturnSuccess() {
        // Arrange
        UpdateInventory updateInventory = new UpdateInventory(1, newInventory, inventoryRepository);
        Inventory inventory = new Inventory();

        when(inventoryRepository.findById(1))
            .thenReturn(java.util.Optional.of(inventory));

        inventory.setItemName("Semsang");
        inventory.setPrice(10000000.0);

        when(inventoryRepository.save(inventory))
            .thenReturn(inventory);

        // Act
        Inventory expectedResult = updateInventory.execute();

        // Assert
        Assert.isTrue(expectedResult != null, "should not null");

        // Verify
        verify(inventoryRepository, times(1)).findById(1);
        verify(inventoryRepository, times(1)).save(inventory);
    }

}
