package com.unsri.ecommerce.application.behaviours.seller.commands;

import com.unsri.ecommerce.domain.models.Seller;
import com.unsri.ecommerce.infrastructure.repository.SellerRepository;
import com.unsri.ecommerce.infrastructure.webconfig.jwt.JwtUtils;
import com.unsri.ecommerce.infrastructure.webconfig.service.SellerDetailsImpl;
import com.unsri.ecommerce.presentation.payload.response.RegisterResponse;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.*;

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

    private final Seller seller;

    private final RegisterSeller registerSeller;

    public RegisterSellerTests() {
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
            "qwerty123"
        );

        registerSeller = new RegisterSeller(
            authenticationManager,
            sellerRepository,
            request,
            encoder,
            jwtUtils,
            mailSender,
            seller,
            "abcd"
        );
    }

    @Test
    public void RegisterSellerTests_ShouldReturnRegisterIsSuccess_Success()
        throws UnsupportedEncodingException, MessagingException {
        // Arrange

        // Save seller to Db
        when(encoder.encode(seller.getPassword()))
            .thenReturn("$2a$10$/PYlcw.8IXqJu8nmrVFKXOBFQCN7JIkEN/gg4WJHB.7T8HDbeJ/Uq");

        seller.setPassword(encoder.encode(seller.getPassword()));
        seller.setIsActivated(false);
        seller.setVerificationCode(RandomString.make(64));

        when(sellerRepository.save(seller)).thenReturn(seller);

        // Authenticate seller
        when(authenticationManager.authenticate(any())).thenReturn(initAuthentication());
        when(jwtUtils.generateJwt(any()))
            .thenReturn("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ3ZXdndTEyM0BnbWFpbC5jb20iLCJpYXQiOjE2MjMxMzAxNTIsImV4cCI6MzIwMDk3Nzc1Mn0._gG__jU6i_Mr4YK655ZwuW0-GG4sXFHv5qIehG6Xr0spyxDYGn3i0vyu79ZZcuLSkV3n4J0ZHc1VumZYByauQA");

        // Send verification email
        when(request.getRequestURL()).thenReturn(new StringBuffer("http://localhost:8080/api/v1/register"));
        when(request.getServletPath()).thenReturn("http://localhost:8080");
        when(mailSender.createMimeMessage()).thenReturn(new MimeMessage((Session) any()));

        // Act
        RegisterResponse expectedResult = registerSeller.execute(Optional.empty());

        // Assert
        Assert.isTrue(
            expectedResult.getJwtResponse().getId() == seller.getId(),
            "Seller id should match with the requester"
        );
        Assert.isTrue(
            expectedResult.getJwtResponse().getUsername().equals(seller.getUsername()),
            "Seller username should match with the requester"
        );
        Assert.isTrue(
            expectedResult.getJwtResponse().getUsername().equals(seller.getEmail()),
            "Seller email should match with the requester");
        Assert.isTrue(expectedResult.getStatusCode().substring(4).equals("OK"), "Status code should be OK");

        // Verify
        verify(sellerRepository, times(1)).save(seller);
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
                    seller.getId(),
                    seller.getUsername(),
                    seller.getEmail(),
                    seller.getPassword(),
                    authorities
                );
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

    @Test
    public void RegisterSellerTests_ShouldReturnEmailIsAlreadyInUse_Success()
        throws UnsupportedEncodingException, MessagingException {
        // Arrange
        when(sellerRepository.existsByEmail(seller.getEmail())).thenReturn(true);

        // Act
        RegisterResponse expectedResult = registerSeller.execute(Optional.empty());

        // Assert
        Assert.isTrue(
            expectedResult.getStatusCode().substring(4).equals("CONFLICT"),
            "Status code should be CONFLICT"
        );

        // Verify
        verify(sellerRepository, times(1)).existsByEmail(seller.getEmail());
    }

}
