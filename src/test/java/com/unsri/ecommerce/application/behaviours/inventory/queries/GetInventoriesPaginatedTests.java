package com.unsri.ecommerce.application.behaviours.inventory.queries;

import com.unsri.ecommerce.domain.models.Inventory;
import com.unsri.ecommerce.infrastructure.repository.InventoryRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class GetInventoriesPaginatedTests {

    @Mock
    private InventoryRepository inventoryRepository;
    private final GetInventoriesPaginated getInventoriesPaginated;
    private final List<Inventory> inventories = new ArrayList<>();
    private final Pageable pageable = PageRequest.of(0, 10);
    private final String keyword = "aza";
    private final int listLength = 12;

    public GetInventoriesPaginatedTests() {
        MockitoAnnotations.openMocks(this);

        getInventoriesPaginated = new GetInventoriesPaginated(inventoryRepository, pageable);
        for (int i = 0; i < listLength; i++) {
            inventories.add(new Inventory(keyword, 100.0));
        }
    }

    @Test
    void GetInventoriesPaginatedTests_ReturnSuccess(){
        // Arrange
        Page<Inventory> page = new PageImpl<>(inventories);
        when(inventoryRepository.findAll(pageable)).thenReturn(page);

        // Act
        List<Inventory> expectedResult = getInventoriesPaginated.execute(Optional.empty()).getContent();

        // Assert
        Assert.isTrue(expectedResult.size() > 0, "should be more than 0");
        Assert.isTrue(expectedResult.size() == listLength, "inventoriesSize should be the same as input");
        for (Inventory inventory : expectedResult) {
            Assert.isTrue(inventory.getItemName().equalsIgnoreCase(keyword), "itemName should be the same");
            Assert.isTrue(inventory.getItemName().equals(keyword), "itemName case should be the same");
            Assert.isTrue(inventory.getPrice() == 100.0, "price should be the same");
        }

        // Verify
        verify(inventoryRepository, times(1)).findAll(pageable);
    }
}