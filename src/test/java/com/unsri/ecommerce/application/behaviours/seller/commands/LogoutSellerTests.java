package com.unsri.ecommerce.application.behaviours.seller.commands;

import com.unsri.ecommerce.domain.models.JwtUser;
import com.unsri.ecommerce.infrastructure.repository.JwtUserRepository;
import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class LogoutSellerTests {

    @Mock
    private JwtUserRepository jwtUserRepository;

    private LogoutSeller logoutSeller;

    private String jwt = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ3ZXdndTEyM0BnbWFpbC5jb20iLCJpYXQiOjE2MjMxMzAxNTIsImV4cCI6MzIwMDk3Nzc1Mn0._gG__jU6i_Mr4YK655ZwuW0-GG4sXFHv5qIehG6Xr0spyxDYGn3i0vyu79ZZcuLSkV3n4J0ZHc1VumZYByauQA";

    public LogoutSellerTests() {
        MockitoAnnotations.openMocks(this);

        logoutSeller = new LogoutSeller(
          jwtUserRepository,
          jwt
        );
    }

    @Test
    public void LogoutSellerTests_ShouldReturnLogoutIsSuccess_Success() {
        // Arrange
        JwtUser jwtUser = new JwtUser(
            1,
            "abcd",
            jwt,
            true
        );

        when(jwtUserRepository.findByJwt(jwt)).thenReturn(Optional.of(jwtUser));

        // Act
        String expectedResult = logoutSeller.execute(Optional.empty());

        // Assert
        Assert.isTrue(
            expectedResult.equals("Seller is logged out successfully"),
            "Should return seller is logged out successfully"
        );

        // Verify
        verify(jwtUserRepository, times(1)).findByJwt(jwt);
    }

}