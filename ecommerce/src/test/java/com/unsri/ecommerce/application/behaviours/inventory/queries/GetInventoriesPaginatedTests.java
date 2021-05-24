package com.unsri.ecommerce.application.behaviours.inventory.queries;

import com.unsri.ecommerce.domain.models.Inventory;
import com.unsri.ecommerce.infrastructure.repository.InventoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
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
    private GetInventoriesPaginated getInventoriesPaginated;
    private List<Inventory> inventories = new ArrayList<>();
    private Pageable pageable = PageRequest.of(0, 10);
    private Page<Inventory> inventoryPage;


    public GetInventoriesPaginatedTests() {
        getInventoriesPaginated = new GetInventoriesPaginated(inventoryRepository, pageable);
        inventoryPage = Page.empty(pageable);
    }

    @Test
    void GetInventoriesPaginatedTests_ReturnSuccess(){
        // Arrange
        when(inventoryRepository.findAll(pageable)).thenReturn(inventoryPage);
        when(inventoryRepository.findAll(pageable).getContent()).thenReturn(inventories);

        // Act
        List<Inventory> expectedResult = getInventoriesPaginated.execute(Optional.empty()).getContent();

        // Assert
        Assert.isTrue(expectedResult.size() > 0, "should be more than 0");

        // Verify
        verify(inventoryRepository, times(1)).findAll(pageable);
    }
}