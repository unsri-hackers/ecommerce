package com.unsri.ecommerce.presentation.webconfig.jwt;

import com.unsri.ecommerce.presentation.webconfig.service.SellerDetailsImpl;
import com.unsri.ecommerce.application.domain.JwtUser;
import com.unsri.ecommerce.infrastructure.repository.JwtUserRepository;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class JwtUtils {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Autowired
    JwtUserRepository jwtUserRepository;

    @Value("${jwt.secret}")
    private String jwtSecret;

    private static final long secondInAYear = 31556952;

    private static final long ms = 1000;

    private final long year = 50;

    private final long jwtExpirationMs = year * secondInAYear * ms;

    public String generateJwt(Authentication authentication) {
        SellerDetailsImpl userPrincipal = (SellerDetailsImpl) authentication.getPrincipal();

        return Jwts
            .builder()
            .setSubject((userPrincipal.getEmail()))
            .setIssuedAt(new Date())
            .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs)).signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact();
    }

    public String getEmailFromJwt(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwt(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }

    public void saveJwtUser(String jwt, String deviceId, SellerDetailsImpl sellerDetails) {
        JwtUser jwtUser = jwtUserRepository.findByUserAndDeviceId(sellerDetails.getId(), deviceId);

        if (jwtUser == null) {
            JwtUser newJwtUser = new JwtUser(
                sellerDetails.getId(),
                deviceId,
                jwt,
                false
            );

            jwtUserRepository.save(newJwtUser);
        } else {
            if (jwtUser.isInvalidated()) {
                jwtUser.setJwt(jwt);
                jwtUser.setInvalidated(false);
                jwtUserRepository.save(jwtUser);
            }

            if (jwtUser.getUserId() == sellerDetails.getId() && jwtUser.getDeviceId().equals(deviceId)) {
                jwtUser.setJwt(jwt);
                jwtUserRepository.save(jwtUser);
            }
        }
    }

    public boolean isBlocked(String jwt) {
        Optional<JwtUser> jwtUser = jwtUserRepository.findByJwt(jwt);
        return jwtUser.get().isInvalidated();
    }
}
