package com.unsri.ecommerce.application.behaviours.inventory.queries;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.unsri.ecommerce.domain.models.Inventory;
import com.unsri.ecommerce.infrastructure.repository.InventoryRepository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
public class GetInventoryTests {
    
    @InjectMocks
    private InventoryRepository inventoryRepository;

    @Mock
    private GetInventory getInventory;

    @BeforeAll
    public static void setup() {
    }

    @Test
    public void GetInventoryTests_ReturnSuccess() {
        // arrange
        when(inventoryRepository.findAll())
        .thenReturn(new ArrayList());

        // act
        List<Inventory> expectedResult = getInventory.execute();

        // assert
        Assert.isTrue(expectedResult.size() == 1, "should more than 1");

        // verify
        verify(inventoryRepository, times(1)).findAll();
    }
}
