package com.unsri.ecommerce.application.behaviours.inventory.queries;

import com.unsri.ecommerce.domain.models.Inventory;
import com.unsri.ecommerce.infrastructure.repository.InventoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
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
    private GetInventoriesPaginated getInventoriesPaginated;
    private List<Inventory> inventories = new ArrayList<>();
    private Pageable pageable = PageRequest.of(0, 1);

    public GetInventoriesPaginatedTests() {
        MockitoAnnotations.openMocks(this);

        getInventoriesPaginated = new GetInventoriesPaginated(inventoryRepository, pageable);
    }

    @Test
    void GetInventoriesPaginatedTests_ReturnSuccess(){
        // Arrange
        List<Inventory> inventories = new ArrayList<>();
        inventories.add(new Inventory());
        Page page = new PageImpl(inventories);
        when(inventoryRepository.findAll(pageable)).thenReturn(page);

        // Act
        List<Inventory> expectedResult = getInventoriesPaginated.execute(Optional.empty()).getContent();

        // Assert
        Assert.isTrue(expectedResult.size() > 0, "should be more than 0");

        // Verify
        verify(inventoryRepository, times(1)).findAll(pageable);
    }
}