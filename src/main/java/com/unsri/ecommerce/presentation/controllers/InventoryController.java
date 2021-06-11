package com.unsri.ecommerce.presentation.controllers;

import java.util.ArrayList;
import java.util.List;

import com.unsri.ecommerce.application.behaviours.inventory.commands.CreateInventory;
import com.unsri.ecommerce.application.behaviours.inventory.commands.UpdateInventory;
import com.unsri.ecommerce.application.behaviours.inventory.queries.GetInventory;
import com.unsri.ecommerce.application.behaviours.inventory.queries.GetInventoriesPaginatedByItemName;
import com.unsri.ecommerce.application.behaviours.inventory.queries.GetSellerInventories;
import com.unsri.ecommerce.domain.models.Inventory;
import com.unsri.ecommerce.domain.models.InventoryResponse;
import com.unsri.ecommerce.domain.models.PhotoInventory;
import com.unsri.ecommerce.domain.models.Seller;
import com.unsri.ecommerce.infrastructure.repository.InventoryRepository;

import com.unsri.ecommerce.infrastructure.repository.SellerRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class InventoryController {

    private InventoryRepository _InventoryRepository;
    private SellerRepository sellerRepository;

    public InventoryController(InventoryRepository inventoryRepository, SellerRepository sellerRepository) {
        _InventoryRepository = inventoryRepository;
        this.sellerRepository = sellerRepository;
    }

    @GetMapping("api/v1/storefront/products")
    public List<Inventory> getInventory() {
        GetInventory command = new GetInventory(_InventoryRepository);
        return command.execute(Optional.empty());
    }

    @GetMapping(value = "api/v1/storefront/products/paging/keyword")
    public List<Inventory> getInventoriesPaginatedByKeyword(
            @RequestParam(value = "search") String keyword,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        GetInventoriesPaginatedByItemName command = new GetInventoriesPaginatedByItemName(_InventoryRepository, keyword, pageable);
        return command.execute(Optional.empty());
    }

    @GetMapping(value = "api/v1/storefront/products/paging")
    public BaseResponse<List<InventoryResponse>> getInventoriesPaginatedBySellerTypeAndSellerId(
            @RequestParam(value = "sellerType") int sellerType,
            @RequestParam(value = "sellerId") int sellerId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);

        GetSellerInventories command = new GetSellerInventories(sellerRepository, sellerType, sellerId, pageable);
        BaseResponse<List<InventoryResponse>> response = new BaseResponse<>();
        List<InventoryResponse> responseHolder = new ArrayList<>();

        Seller seller = command.execute(Optional.empty());
        seller.getInventories()
                .forEach(inventory -> responseHolder.add(
                        new InventoryResponse(
                                seller.getId(),
                                seller.getUsername(),
                                inventory.getId(),
                                inventory.getItemName(),
                                inventory.getPrice(),
                                inventory.getPhotos()
                        )
                )
        );

        response.setResult(responseHolder);
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Item dapat " + responseHolder.size());

        return response;
    }

    @PostMapping(value = "/api/v1/storefront/products")
    public Inventory addInventory(@RequestBody Inventory item) {
        CreateInventory command = new CreateInventory(_InventoryRepository);
        return command.execute(Optional.ofNullable(item));
    }

    @PutMapping("/api/v1/storefront/products/{id}")
    Inventory updateInventory(@PathVariable int id, @RequestBody Inventory newInventory) {
        UpdateInventory command = new UpdateInventory(id, _InventoryRepository);
        return command.execute(Optional.ofNullable(newInventory));
    }
}
