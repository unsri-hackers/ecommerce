package com.unsri.ecommerce.application.behaviours.seller.queries;

import com.unsri.ecommerce.application.behaviours.seller.commands.GetSellerInventories;
import com.unsri.ecommerce.domain.models.Seller;
import com.unsri.ecommerce.infrastructure.repository.SellerRepository;
import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class GetSellerInventoriesTests {

    @Mock
    private SellerRepository sellerRepository;
    private final Integer sellerType = 1;
    private final Integer sellerId = 1;
    private final List<Seller> sellers = new ArrayList<>();
    private GetSellerInventories getSellerInventories;
    private final Pageable pageable = PageRequest.of(0, 10);

    public GetSellerInventoriesTests() {
        MockitoAnnotations.openMocks(this);
        getSellerInventories = new GetSellerInventories(sellerRepository, sellerType, sellerId, pageable);

        for (int i = 0; i < 10; i++) {
            sellers.add(new Seller(sellerId, "a@gmail.com", "a@gmail.com"));
        }
    }

    @Test
    public void GetInventoriesTest_ReturnSuccess() {
        // Arrange
        when(sellerRepository.findSellerByIdAndType(sellerId, sellerType, pageable)).thenReturn(sellers);

        // Act
        List<Seller> expectedResult = getSellerInventories.execute(Optional.empty());

        // Assert
        Assert.isTrue(expectedResult.size() > 0, "Should be more than 0");

        // Verify
        verify(sellerRepository, times(1)).findSellerByIdAndType(sellerId, sellerType, pageable);
    }

}