package com.unsri.ecommerce.application.behaviours.inventory.commands;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.unsri.ecommerce.domain.models.Inventory;
import com.unsri.ecommerce.domain.models.PhotoInventory;
import com.unsri.ecommerce.infrastructure.repository.InventoryRepository;

import com.unsri.ecommerce.presentation.payload.request.UploadInventoryRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

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
        PhotoInventory photo = new PhotoInventory("https://photolemur.com/uploads/blog/unnamed.jpg","unnamed.jpg", 1);
        List<PhotoInventory> photos = new ArrayList<>();
        photos.add(photo);

        // Arrange
        UploadInventoryRequest inventoryRequest = new UploadInventoryRequest();
        inventoryRequest.setProductName(itemName);
        inventoryRequest.setPrice(price);
        inventoryRequest.setPhotos(photos);
        Inventory inventory = new Inventory();
        inventory.setItemName(inventoryRequest.getProductName());
        inventory.setPrice(inventoryRequest.getPrice());
        inventory.setPhotos(inventoryRequest.getPhotos());

        when(inventoryRepository.save(inventory)).thenReturn(inventory);

        // Act
        Inventory expectedResult = createInventory.execute(java.util.Optional.of(inventory));

        // Assert
        Assert.isTrue(expectedResult != null, "should not null");
        Assert.isTrue(expectedResult.getItemName().equals(inventory.getItemName()) , "item name should be same");
        Assert.isTrue(expectedResult.getPrice().equals(inventory.getPrice()), "price should be same");
        Assert.isTrue(expectedResult.getPhotos().get(0) == inventory.getPhotos().get(0), "photos should be same");

        // Verify
        verify(inventoryRepository, times(1)).save(inventory);
    }

}
