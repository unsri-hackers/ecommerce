package com.unsri.ecommerce.infrastructure.repository;

import com.unsri.ecommerce.domain.models.JwtUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JwtUserRepository extends JpaRepository<JwtUser, Integer> {
    Optional<JwtUser> findByJwt(String jwt);

    @Query("SELECT ju FROM JwtUser ju WHERE userId = :userId AND deviceId = :deviceId")
    JwtUser findByUserAndDeviceId(int userId, String deviceId);
}
