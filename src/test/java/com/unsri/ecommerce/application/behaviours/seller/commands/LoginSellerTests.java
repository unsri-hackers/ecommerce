package com.unsri.ecommerce.application.behaviours.seller.commands;

import com.unsri.ecommerce.infrastructure.webconfig.jwt.JwtUtils;
import com.unsri.ecommerce.infrastructure.webconfig.service.SellerDetailsImpl;
import com.unsri.ecommerce.presentation.payload.response.JwtResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class LoginSellerTests {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtUtils jwtUtils;

    private LoginSeller loginSeller;

    public LoginSellerTests() {
        MockitoAnnotations.openMocks(this);

        loginSeller = new LoginSeller(
          authenticationManager,
          jwtUtils,
          "abcd",
          "test@email.com",
          "test12345"
        );
    }

    @Test
    public void LoginSellerTests_ShouldReturnLoginIsSuccess_Success() {
        // Arrange
        int id = 1;
        String username = "test@email.com";
        String email = "test@email.com";

        when(authenticationManager.authenticate(any())).thenReturn(initAuthentication());
        when(jwtUtils.generateJwt(any()))
            .thenReturn("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ3ZXdndTEyM0BnbWFpbC5jb20iLCJpYXQiOjE2MjMxMzAxNTIsImV4cCI6MzIwMDk3Nzc1Mn0._gG__jU6i_Mr4YK655ZwuW0-GG4sXFHv5qIehG6Xr0spyxDYGn3i0vyu79ZZcuLSkV3n4J0ZHc1VumZYByauQA");

        // Act
        JwtResponse expectedResult = loginSeller.execute(Optional.empty());

        // Assert
        Assert.isTrue(
            expectedResult.getId() == id,
            "Seller id should match with the requester"
        );
        Assert.isTrue(
            expectedResult.getUsername().equals(username),
            "Seller username should match with the requester"
        );
        Assert.isTrue(
            expectedResult.getUsername().equals(email),
            "Seller email should match with the requester");

        // Verify
        verify(authenticationManager, times(1)).authenticate(any());
        verify(jwtUtils, times(1)).generateJwt(any());
    }

    private Authentication initAuthentication() {
        return new Authentication() {
            final List<GrantedAuthority> authorities =
                AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN");

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public Object getCredentials() {
                return null;
            }

            @Override
            public Object getDetails() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                return new SellerDetailsImpl(
                    1,
                    "test@email.com",
                    "test@email.com",
                    "$2a$10$/PYlcw.8IXqJu8nmrVFKXOBFQCN7JIkEN/gg4WJHB.7T8HDbeJ/Uq",
                    authorities
                );
            }

            @Override
            public boolean isAuthenticated() {
                return true;
            }

            @Override
            public void setAuthenticated(boolean b) throws IllegalArgumentException {

            }

            @Override
            public String getName() {
                return null;
            }
        };
    }

}