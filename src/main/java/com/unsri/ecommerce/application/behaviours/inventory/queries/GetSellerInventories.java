package com.unsri.ecommerce.application.behaviours.inventory.queries;

import com.unsri.ecommerce.application.behaviours.BaseCommand;
import com.unsri.ecommerce.domain.models.Seller;
import com.unsri.ecommerce.infrastructure.repository.SellerRepository;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public class GetSellerInventories implements BaseCommand<Seller> {

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
    public Seller execute(Optional<Seller> param) {
        return sellerRepository.findSellerByIdAndType(this.sellerId, this.sellerType, this.pageable);
    }
}
