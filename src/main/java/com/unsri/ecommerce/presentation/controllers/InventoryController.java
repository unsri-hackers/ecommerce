package com.unsri.ecommerce.presentation.controllers;

import java.util.ArrayList;
import java.util.List;

import com.unsri.ecommerce.application.behaviours.inventory.commands.CreateInventory;
import com.unsri.ecommerce.application.behaviours.inventory.commands.UpdateInventory;
import com.unsri.ecommerce.application.behaviours.inventory.queries.GetInventory;
import com.unsri.ecommerce.application.behaviours.inventory.queries.GetInventoriesPaginatedByItemName;
import com.unsri.ecommerce.application.behaviours.inventory.queries.GetInventoriesBySellerId;
import com.unsri.ecommerce.domain.models.Inventory;
import com.unsri.ecommerce.domain.models.InventoryResponse;
import com.unsri.ecommerce.infrastructure.repository.InventoryRepository;

import com.unsri.ecommerce.infrastructure.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
public class InventoryController extends BaseController {

    private InventoryRepository inventoryRepository;

    @Autowired
    JwtUtils jwtUtils;

    public InventoryController(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @GetMapping("api/v1/storefront/products")
    public List<Inventory> getInventory() {
        GetInventory command = new GetInventory(inventoryRepository);
        return command.execute(Optional.empty());
    }

    @GetMapping(value = "api/v1/storefront/products/paging/keyword")
    public List<Inventory> getInventoriesPaginatedByKeyword(
            @RequestParam(value = "search") String keyword,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        GetInventoriesPaginatedByItemName command = new GetInventoriesPaginatedByItemName(inventoryRepository, keyword, pageable);
        return command.execute(Optional.empty());
    }

    @GetMapping(value = "api/v1/storefront/products/paging")
    public BaseResponse<List<InventoryResponse>> getInventoriesPaginatedBySellerTypeAndSellerId(HttpServletRequest request,
                                                                                                @RequestParam(value = "page", defaultValue = "0") int page,
                                                                                                @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);

        int sellerId = getAuthorizedUser(request.getUserPrincipal());

        GetInventoriesBySellerId command = new GetInventoriesBySellerId(inventoryRepository, sellerId, pageable);
        BaseResponse<List<InventoryResponse>> response = new BaseResponse<>();
        List<InventoryResponse> responseHolder = new ArrayList<>();

        List<Inventory> inventories = command.execute(Optional.empty());

        for (Inventory inventory : inventories) {
            InventoryResponse inventoryResponse = new InventoryResponse(
                    inventory.getFkSellerId(),
                    request.getUserPrincipal().getName(),
                    inventory.getId(),
                    inventory.getItemName(),
                    inventory.getPrice(),
                    inventory.getPhotos()
            );

            responseHolder.add(inventoryResponse);
        }

        response.setResult(responseHolder);
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Successfully get data of total " + responseHolder.size());

        return response;
    }

    @PostMapping(value = "/api/v1/storefront/products")
    public Inventory addInventory(@RequestBody Inventory item) {
        CreateInventory command = new CreateInventory(inventoryRepository);
        return command.execute(Optional.ofNullable(item));
    }

    @PutMapping("/api/v1/storefront/products/{id}")
    Inventory updateInventory(@PathVariable int id, @RequestBody Inventory newInventory) {
        UpdateInventory command = new UpdateInventory(id, inventoryRepository);
        return command.execute(Optional.ofNullable(newInventory));
    }
}
