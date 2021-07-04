package com.unsri.ecommerce.application.behaviours.seller.queries;

import com.unsri.ecommerce.application.domain.Seller;
import com.unsri.ecommerce.infrastructure.repository.SellerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class GetSellerByIdTests {

    @Mock
    private SellerRepository sellerRepository;

    private GetSellerById getSellerById;

    private Seller seller;

    public GetSellerByIdTests() {
        MockitoAnnotations.openMocks(this);

        seller = new Seller(
                1,
                "test@email.com",
                "test@email.com",
                "test12345",
                "Yanto",
                "Yeager",
                "abcd",
                new Date(),
                "L",
                1,
                new ArrayList<>(),
                false,
                null
        );

        getSellerById = new GetSellerById(sellerRepository);

    }

    @Test
    public void GetSellerByIdTest_ReturnSuccess() {
        //Arrange
        when(sellerRepository.findById(seller.getId())).thenReturn(java.util.Optional.ofNullable(seller));

        //Act
        Seller expectedResult = getSellerById.execute(Optional.of(seller));

        //Assert
        Assert.isTrue(expectedResult != null, "should not null");

        //Verify
        verify(sellerRepository, times(1)).findById(seller.getId());
    }

}
