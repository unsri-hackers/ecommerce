package com.unsri.ecommerce.application.behaviours.inventory.queries;

import com.unsri.ecommerce.application.domain.Inventory;
import com.unsri.ecommerce.application.domain.PhotoInventory;
import com.unsri.ecommerce.infrastructure.repository.InventoryRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class GetInventoriesPaginatedByItemNameTest {

    @Mock
    private InventoryRepository inventoryRepository;

    private final GetInventoriesPaginatedByItemName getInventoriesPaginatedByItemName;
    private final Pageable pageable = PageRequest.of(0, 10);
    private final String keyword = "aza";
    private final int fkSellerId = 1;
    private final List<Inventory> inventories = new ArrayList<>();

    public GetInventoriesPaginatedByItemNameTest() {
        MockitoAnnotations.openMocks(this);
        getInventoriesPaginatedByItemName = new GetInventoriesPaginatedByItemName(inventoryRepository, keyword, pageable);

        for (int i = 0; i < 3; i++) {
            inventories.add(new Inventory(keyword, 100.0, 1, new ArrayList<PhotoInventory>()));
        }
    }

    @Test
    public void GetInventoriesPaginatedByItemNameTest_ReturnSuccess() {
        // Arrange
        when(inventoryRepository.findAllPaginatedByItemName(keyword, pageable)).thenReturn(inventories);

        // Act
        List<Inventory> expectedResult = getInventoriesPaginatedByItemName.execute(Optional.empty());

        // Assert
        Assert.isTrue(expectedResult.size() > 0, "should be more than 0");
        expectedResult.forEach(inventory -> {
            Assert.isTrue(inventory.getItemName().equalsIgnoreCase(keyword), "itemName should be the same");
            Assert.isTrue(inventory.getItemName().equals(keyword), "itemName case should be the same");
            Assert.isTrue(inventory.getFkSellerId() == fkSellerId, "fkSellerId should be the same");
            Assert.isTrue(inventory.getPhotos().equals(new ArrayList<PhotoInventory>()), "PhotoInventory in Photos should be the same");
            Assert.isTrue(inventory.getPrice() == 100.0, "price should be the same");
        });

        // Verify
        verify(inventoryRepository, times(1)).findAllPaginatedByItemName(keyword, pageable);
    }

}