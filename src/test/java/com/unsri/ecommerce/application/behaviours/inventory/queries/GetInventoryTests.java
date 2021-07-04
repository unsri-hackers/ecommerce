package com.unsri.ecommerce.application.behaviours.inventory.queries;

import com.unsri.ecommerce.application.domain.Inventory;
import com.unsri.ecommerce.infrastructure.repository.InventoryRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class GetInventoryTests {

    @Mock
    private InventoryRepository inventoryRepository;

    private GetInventory getInventory;

    private ArrayList<Inventory> list = new ArrayList<>();

    public GetInventoryTests() {
        MockitoAnnotations.openMocks(this);
        getInventory = new GetInventory(inventoryRepository);

        list.add(new Inventory());
    }

    @Test
    public void GetInventoryTests_ReturnSuccess() {
        // Arrange
        when(inventoryRepository.findAll()).thenReturn(list);

        // Act
        List<Inventory> expectedResult = getInventory.execute(Optional.empty());

        // Assert
        Assert.isTrue(expectedResult.size() > 0, "should more than 0");

        // Verify
        verify(inventoryRepository, times(1)).findAll();
    }
}
