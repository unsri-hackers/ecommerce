package com.unsri.ecommerce.application.behaviours.seller.commands;

import com.unsri.ecommerce.domain.models.Seller;
import com.unsri.ecommerce.infrastructure.repository.SellerRepository;
import com.unsri.ecommerce.infrastructure.security.jwt.JwtUtils;
import com.unsri.ecommerce.presentation.payload.response.RegisterResponse;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class RegisterSellerTests {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private SellerRepository sellerRepository;

    @Mock
    private HttpServletRequest request;

    @Mock
    private PasswordEncoder encoder;

    @Mock
    private JwtUtils jwtUtils;

    @Mock
    private JavaMailSender mailSender;

    private Authentication authentication;

    private Seller seller;

    private RegisterSeller registerSeller;

    public RegisterSellerTests() {
        MockitoAnnotations.openMocks(this);

        seller = new Seller(
            "test@email.com",
            "test@email.com",
            "test12345",
            "Yanto",
            "Yeager",
            "abcd",
            new Date(),
            "L",
            1,
            false
        );

        registerSeller = new RegisterSeller(
            authenticationManager,
            sellerRepository,
            request,
            encoder,
            jwtUtils,
            mailSender,
            seller
        );
    }

    @Test
    public void RegisterSellerTests_ReturnSuccess()
        throws UnsupportedEncodingException, MessagingException {
        // Arrange
        String email = seller.getEmail();
        String password = seller.getPassword();

        when(encoder.encode(seller.getPassword()))
                .thenReturn("$2a$10$/PYlcw.8IXqJu8nmrVFKXOBFQCN7JIkEN/gg4WJHB.7T8HDbeJ/Uq");
        seller.setPassword(encoder.encode(seller.getPassword()));
        seller.setIsActivated(false);
        seller.setVerificationCode(RandomString.make(64));

        when(sellerRepository.save(seller)).thenReturn(seller);
        when(authenticationManager.authenticate(any()))
            .thenReturn(initAuthentication());

        // Act
        RegisterResponse expectedResult = registerSeller.execute(Optional.empty());

        // Assert
        Assert.isTrue(expectedResult.getStatusCode().equals("OK"), "status code should be OK");

        // Verify
        verify(sellerRepository, times(1)).save(seller);
    }

    private Authentication initAuthentication() {
        return new Authentication() {
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
                return null;
            }

            @Override
            public boolean isAuthenticated() {
                return false;
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
