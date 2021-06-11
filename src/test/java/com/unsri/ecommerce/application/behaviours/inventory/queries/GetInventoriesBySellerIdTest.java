package com.unsri.ecommerce.application.behaviours.inventory.queries;

import com.unsri.ecommerce.domain.models.Inventory;
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


class GetInventoriesBySellerIdTest {

    @Mock
    private InventoryRepository repository;

    public GetInventoriesBySellerIdTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void GetInventoriesBySellerId_ShouldReturnInventoriesBySellerId_Success() {
        // arrange
        int sellerId = 1;
        Pageable pageable = PageRequest.of(0, 1);

        List<Inventory> inventories = new ArrayList<>();
        Inventory inventory = new Inventory();
        inventory.setId(1);
        inventory.setItemName("Laptop");
        inventory.setPrice(10000000.0);
        inventory.setPhotos(new ArrayList<>());
        inventory.setFkSellerId(sellerId);

        inventories.add(inventory);

        when(repository.findAllPaginatedByfkSellerId(sellerId, pageable))
                .thenReturn(inventories);

        GetInventoriesBySellerId command = new GetInventoriesBySellerId(repository, sellerId, pageable);

        // act
        List<Inventory> expectedResult = command.execute(Optional.empty());

        // assert
        Assert.isTrue(expectedResult.size() > 0, "Inventories should not null");
        Assert.isTrue(expectedResult.get(0).getFkSellerId() == sellerId, "Seller id should match with the requester");

        // verify
        verify(repository, times(1)).findAllPaginatedByfkSellerId(sellerId, pageable);
    }

    @Test
    public void GetInventoriesBySellerId_InventoriesIsNull_Success() {
        // arrange
        int sellerId = 1;
        Pageable pageable = PageRequest.of(0, 1);

        List<Inventory> inventories = new ArrayList<>();

        when(repository.findAllPaginatedByfkSellerId(sellerId, pageable))
                .thenReturn(inventories);

        GetInventoriesBySellerId command = new GetInventoriesBySellerId(repository, sellerId, pageable);

        // act
        List<Inventory> expectedResult = command.execute(Optional.empty());

        // assert
        Assert.isTrue(expectedResult.size() == 0, "Inventories should zero");

        // verify
        verify(repository, times(1)).findAllPaginatedByfkSellerId(sellerId, pageable);
    }
}