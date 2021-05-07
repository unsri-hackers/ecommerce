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
public class CreateInventoryTests {

    @Mock
    private Inventory inventory;

    @Mock
    private InventoryRepository inventoryRepository;

    @InjectMocks
    private CreateInventory createInventory;

    @BeforeAll
    public static void setup() {
    }

    @Test
    public void CreateInventoryTests_ReturnSuccess() {
        // Arrange
        when(inventoryRepository.save(inventory)).thenReturn(new Inventory());

        // Act
        Inventory expectedResult = createInventory.execute();

        // Assert
        Assert.isTrue(expectedResult != null, "should not null");

        // Verify
        verify(inventoryRepository, times(1)).save(inventory);
    }

}
