package com.unsri.ecommerce.application.behaviours.seller.commands;

import com.unsri.ecommerce.application.behaviours.BaseCommand;
import com.unsri.ecommerce.domain.models.Seller;
import com.unsri.ecommerce.infrastructure.repository.SellerRepository;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public class GetSellerInventories implements BaseCommand<List<Seller>> {

    private SellerRepository sellerRepository;

    private Integer sellerType;
    private Integer sellerId;
    private Pageable pageable;

    public GetSellerInventories(SellerRepository sellerRepository, Integer sellerType, Integer sellerId, Pageable pageable) {
        this.sellerRepository = sellerRepository;
        this.sellerType = sellerType;
        this.sellerId = sellerId;
        this.pageable = pageable;
    }

    @Override
    public List<Seller> execute(Optional<List<Seller>> param) {
        return sellerRepository.findSellerByIdAndType(this.sellerId, this.sellerType, this.pageable);
    }
}
