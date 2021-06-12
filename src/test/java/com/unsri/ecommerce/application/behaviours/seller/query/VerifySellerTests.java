package com.unsri.ecommerce.application.behaviours.seller.query;

import com.unsri.ecommerce.domain.models.Seller;
import com.unsri.ecommerce.infrastructure.repository.SellerRepository;
import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class VerifySellerTests {

    @Mock
    private SellerRepository sellerRepository;

    private VerifySeller verifySeller;

    private String verificationCode;

    public VerifySellerTests() {
        MockitoAnnotations.openMocks(this);

        verificationCode = "AddUilqHUdF3BYsbHIv2waBMPvgXehyXp2hGiNrwZTVIpj6kHDnyaII0qQ4SHaG1";

        verifySeller = new VerifySeller(sellerRepository, verificationCode);
    }

    @Test
    public void VerifySellerTests_ShouldReturnVerifyIsSuccess_Success() {
        // Arrange
        Seller seller = new Seller(
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
            verificationCode
        );

        when(sellerRepository.findByVerificationCode(verificationCode)).thenReturn(seller);

        // Act
        String expectedResult = verifySeller.execute(Optional.empty());

        // Assert
        Assert.isTrue(expectedResult.equals("Verification is success"), "Should return verification is success");

        // Verify
        verify(sellerRepository, times(1)).findByVerificationCode(verificationCode);
    }

    @Test
    public void VerifySellerTests_ShouldReturnVerifyIsFailed_Success() {
        // Arrange
        when(sellerRepository.findByVerificationCode(verificationCode)).thenReturn(null);

        // Act
        String expectedResult = verifySeller.execute(Optional.empty());

        // Assert
        Assert.isTrue(expectedResult.equals("Verification is failed"), "Should return verification is failed");

        // Verify
        verify(sellerRepository, times(1)).findByVerificationCode(verificationCode);
    }

}