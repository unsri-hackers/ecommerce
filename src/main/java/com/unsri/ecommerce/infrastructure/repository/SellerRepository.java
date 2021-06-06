package com.unsri.ecommerce.infrastructure.repository;

import com.unsri.ecommerce.domain.models.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Integer> {
    Optional<Seller> findByEmail(String email);
    Boolean existsByEmail(String email);

    @Query("SELECT s FROM Seller s WHERE verificationCode = ?1")
    Seller findByVerificationCode(String code);
}
