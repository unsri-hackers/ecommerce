package com.unsri.ecommerce.application.behaviours.seller.queries;

import com.unsri.ecommerce.application.behaviours.BaseCommand;
import com.unsri.ecommerce.application.domain.Seller;
import com.unsri.ecommerce.infrastructure.repository.SellerRepository;

import java.util.Optional;

public class VerifySeller implements BaseCommand<String> {

    private final SellerRepository sellerRepository;
    private final String verificationCode;

    public VerifySeller(SellerRepository sellerRepository, String verificationCode) {
        this.sellerRepository = sellerRepository;
        this.verificationCode = verificationCode;
    }

    @Override
    public String execute(Optional<String> param) {
        Seller seller = sellerRepository.findByVerificationCode(verificationCode);

        if (seller == null || seller.getIsActivated()) {
            return "Verification is failed";
        }

        seller.setVerificationCode(null);
        seller.setIsActivated(true);
        sellerRepository.save(seller);

        return "Verification is success";
    }
}
