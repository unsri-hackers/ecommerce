package com.unsri.ecommerce.presentation.controllers;

import com.unsri.ecommerce.application.behaviours.inventory.commands.AddInventoryCommand;
import com.unsri.ecommerce.application.behaviours.inventory.commands.AddInventoryCommandHandler;
import com.unsri.ecommerce.application.behaviours.inventory.commands.UpdateInventory;
import com.unsri.ecommerce.application.behaviours.inventory.queries.GetInventoriesBySellerId;
import com.unsri.ecommerce.application.behaviours.inventory.queries.GetInventoriesPaginatedByItemName;
import com.unsri.ecommerce.application.behaviours.inventory.queries.GetInventory;
import com.unsri.ecommerce.application.domain.Inventory;
import com.unsri.ecommerce.application.entities.EnumStateCode;
import com.unsri.ecommerce.application.entities.InventoriesResponse;
import com.unsri.ecommerce.application.entities.InventoryResponse;
import com.unsri.ecommerce.infrastructure.mediator.Mediator;
import com.unsri.ecommerce.infrastructure.mediator.Response;
import com.unsri.ecommerce.infrastructure.repository.InventoryRepository;
import com.unsri.ecommerce.presentation.controllers.request.InventoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class InventoryController extends BaseController {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private Mediator _mediator;

    @Autowired
    private AddInventoryCommandHandler addInventoryCommandHandler;

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
    public BaseResponse<InventoriesResponse> getInventoriesPaginatedBySellerId(HttpServletRequest request,
                                                                               @RequestParam(value = "page", defaultValue = "0") int page,
                                                                               @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);

        int sellerId = getAuthorizedUser(request.getUserPrincipal());

        GetInventoriesBySellerId command = new GetInventoriesBySellerId(inventoryRepository, sellerId, pageable);
        BaseResponse<InventoriesResponse> response = new BaseResponse<>();
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

        response.setResult(new InventoriesResponse(responseHolder));
        response.setStatusCode(HttpStatus.OK.toString());
        response.setMessage("Successfully get data of total " + responseHolder.size());

        return response;
    }

    @PutMapping("/api/v1/storefront/products/{id}")
    Inventory updateInventory(@PathVariable int id, @RequestBody Inventory newInventory) {
        UpdateInventory command = new UpdateInventory(id, inventoryRepository);
        return command.execute(Optional.ofNullable(newInventory));
    }

    @PostMapping(value = "/api/v1/storefront/products")
    public BaseResponse<Inventory> addInventory(HttpServletRequest request, @RequestBody InventoryDTO payload) {
        int sellerId = getAuthorizedUser(request.getUserPrincipal());
        payload.setSellerId(sellerId);

        // todo: this code should go to the mediator impl under reflection
        AddInventoryCommand addInventoryCommand = new AddInventoryCommand();
        addInventoryCommand.setSellerId(payload.getSellerId());
        addInventoryCommand.setProductName(payload.getProductName());
        addInventoryCommand.setPrice(payload.getPrice());
        addInventoryCommand.setPhotos(payload.getPhotos());

        Response<Inventory, EnumStateCode> response = (Response<Inventory, EnumStateCode>)
                _mediator.send(addInventoryCommand, addInventoryCommandHandler);

        BaseResponse<Inventory> baseResponse = new BaseResponse<>();
        baseResponse.setResult(response.get_response());
        baseResponse.setStatusCode(response.get_serverStatusCode().toString());
        baseResponse.setMessage(response.get_errorMessage());

        return baseResponse;
    }
}
