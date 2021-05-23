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

class GetInventoriesPaginatedTests {

    @Mock
    private InventoryRepository inventoryRepository;

    private GetInventoriesPaginated getInventoriesPaginated;
    private Pageable pageable = PageRequest.of(0, 10);
    private List<Inventory> inventories = new ArrayList<>();

    public GetInventoriesPaginatedTests() {
        MockitoAnnotations.openMocks(this);
        getInventoriesPaginated = new GetInventoriesPaginated(inventoryRepository, pageable);
    }

    @Test
    public void GetInventoriesPaginatedTests_ReturnSuccess(){

        // Arrange
        when(inventoryRepository.findAll(pageable).getContent()).thenReturn(inventories);

        // Act
        List<Inventory> expectedResult = getInventoriesPaginated.execute(Optional.empty());

        // Assert
        Assert.isTrue(expectedResult.size() > 0, "should be more than 0");

        // Verify
        verify(inventoryRepository, times(1)).findAll(pageable).getContent();
    }
}