package com.unsri.ecommerce.infrastructure.repository;

import com.unsri.ecommerce.domain.models.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Integer> {
    Optional<Seller> findByEmail(String email);
    Optional<Seller> findById(Integer id);
}
