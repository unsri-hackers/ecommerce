package com.unsri.ecommerce.application.behaviours.inventory.queries;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.unsri.ecommerce.domain.models.Inventory;
import com.unsri.ecommerce.infrastructure.repository.InventoryRepository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
public class GetInventoryTests {

    @Mock
    private InventoryRepository inventoryRepository;

    @InjectMocks
    private GetInventory getInventory;

    @BeforeAll
    public static void setup() {
    }

    @Test
    public void GetInventoryTests_ReturnSuccess() {
        // Arrange
        ArrayList<Inventory> list = new ArrayList<>();
        list.add(new Inventory());

        when(inventoryRepository.findAll()).thenReturn(list);

        // Act
        List<Inventory> expectedResult = getInventory.execute();

        // Assert
        Assert.isTrue(expectedResult.size() > 0, "should more than 0");

        // Verify
        verify(inventoryRepository, times(1)).findAll();
    }
}
