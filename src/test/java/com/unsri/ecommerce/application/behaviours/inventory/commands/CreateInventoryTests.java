package com.unsri.ecommerce.application.behaviours.inventory.commands;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.unsri.ecommerce.domain.models.Inventory;
import com.unsri.ecommerce.infrastructure.repository.InventoryRepository;

import com.unsri.ecommerce.presentation.payload.request.UploadInventoryRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.util.Assert;

public class CreateInventoryTests {

    @Mock
    private InventoryRepository inventoryRepository;

    private CreateInventory createInventory;

    public CreateInventoryTests() {
        MockitoAnnotations.openMocks(this);

        createInventory = new CreateInventory(inventoryRepository);
    }

    @Test
    public void CreateInventoryWithSellerIdTests_ReturnSuccess() {

        String itemName = "Apple";
        Double price = 1000.0;
        int sellerId = 1;

        // Arrange
        UploadInventoryRequest inventoryRequest = new UploadInventoryRequest();
        inventoryRequest.setProductName(itemName);
        inventoryRequest.setPrice(price);

        Inventory inventory = new Inventory();
        inventory.setItemName(inventoryRequest.getProductName());
        inventory.setPrice(inventoryRequest.getPrice());
        inventory.setPhotos(inventoryRequest.getPhoto());
        inventory.setFkSellerId(sellerId);

        when(inventoryRepository.save(inventory)).thenReturn(inventory);

        // Act
        Inventory expectedResult = createInventory.execute(java.util.Optional.of(inventory));

        // Assert
        Assert.isTrue(expectedResult != null, "should not null");
        Assert.isTrue(expectedResult.getItemName() == inventory.getItemName(), "item name should be same");
        Assert.isTrue(expectedResult.getPrice() == inventory.getPrice(), "price should be same");
        Assert.isTrue(expectedResult.getFkSellerId() == sellerId, "seller id should be same");

        // Verify
        verify(inventoryRepository, times(1)).save(inventory);
    }

}
