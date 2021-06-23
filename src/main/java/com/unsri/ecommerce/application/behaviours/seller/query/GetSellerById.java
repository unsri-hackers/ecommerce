package com.unsri.ecommerce.application.behaviours.seller.query;

import com.unsri.ecommerce.application.behaviours.BaseCommand;
import com.unsri.ecommerce.domain.models.Seller;
import com.unsri.ecommerce.infrastructure.repository.SellerRepository;

import java.util.Optional;

public class GetSellerById implements BaseCommand<Seller> {

    private final SellerRepository sellerRepository;

    public GetSellerById(SellerRepository sellerRepository){
        this.sellerRepository = sellerRepository;
    }

    @Override
    public Seller execute(Optional<Seller> sellerId) {
        Optional<Seller> seller = sellerRepository.findById(sellerId.get().getId());

        return seller.get();
    }

}
