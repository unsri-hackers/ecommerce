package com.unsri.ecommerce.application.behaviours.seller.queries;

import com.unsri.ecommerce.application.behaviours.inventory.queries.GetSellerInventories;
import com.unsri.ecommerce.domain.models.Inventory;
import com.unsri.ecommerce.domain.models.PhotoInventory;
import com.unsri.ecommerce.domain.models.Seller;
import com.unsri.ecommerce.infrastructure.repository.SellerRepository;
import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.*;

import static org.mockito.Mockito.*;

class GetSellerInventoriesTests {

    @Mock
    private SellerRepository sellerRepository;

    private final Integer sellerType = 1;
    private final Integer sellerId = 1;
    private final String itemName = "mac";
    private final Double itemPrice = 100.0;

    private final List<Inventory> inventories = new ArrayList<>();
    private final Seller seller = new Seller(
            sellerId,
            "test@gmail.com",
            "test@gmail.com",
            "$2a$10$r7HHOhgNeJ0WRMjMTX37AOWBRyuLFrvx2GY/mD4P19ck2oi8KUrjK",
            "Yanto",
            "Yeager",
            "www.google.com",
            new Date(2000, Calendar.JULY, 10),
            "L", 1,
            inventories
    );

    private final GetSellerInventories getSellerInventories;
    private final Pageable pageable = PageRequest.of(0, 10);

    public GetSellerInventoriesTests() {
        MockitoAnnotations.openMocks(this);
        getSellerInventories = new GetSellerInventories(sellerRepository, sellerType, sellerId, pageable);

        List<PhotoInventory> photosInventory = new ArrayList<>();
        inventories.add(new Inventory(itemName, itemPrice, sellerId, photosInventory));
    }

    @Test
    public void GetSellerInventoriesTest_ReturnSuccess() {
        // Arrange
        when(sellerRepository.findSellerByIdAndType(sellerId, sellerType, pageable)).thenReturn(seller);
        List<Inventory> sellerInventories = seller.getInventories();

        // Act
        List<Inventory> expectedResult = getSellerInventories.execute(Optional.empty()).getInventories();

        // Assert
        Assert.isTrue(expectedResult.size() > 0, "Should be more than 0");
        expectedResult.forEach(expectedInventory -> {
            Assert.isTrue(expectedInventory.getFkSellerId().equals(seller.getId()), "fkSellerId should be the same as sellerId");
            sellerInventories.forEach(sellerInventory -> {
                Assert.isTrue(expectedInventory.getId() == sellerInventory.getId(), "id should be the same");
                Assert.isTrue(expectedInventory.getItemName().equals(sellerInventory.getItemName()), "item name should be the same");
                Assert.isTrue(expectedInventory.getItemName().equalsIgnoreCase(sellerInventory.getItemName()), "item name case should be the same");
                Assert.isTrue(expectedInventory.getPrice().equals(sellerInventory.getPrice()), "price should be the same");
            });
        });

        // Verify
        verify(sellerRepository, times(1)).findSellerByIdAndType(sellerId, sellerType, pageable);
    }

}