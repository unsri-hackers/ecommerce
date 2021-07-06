package com.unsri.ecommerce.application.behaviours.inventory.commands;

import com.unsri.ecommerce.infrastructure.mediator.Handler;
import com.unsri.ecommerce.infrastructure.mediator.Response;
import com.unsri.ecommerce.application.entities.EnumStateCode;
import com.unsri.ecommerce.application.domain.Inventory;
import com.unsri.ecommerce.application.domain.PhotoInventory;
import com.unsri.ecommerce.infrastructure.repository.InventoryRepository;
import com.unsri.ecommerce.presentation.controllers.request.PhotoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddInventoryCommandHandler implements Handler<AddInventoryCommand, Response<Inventory, EnumStateCode>> {

    @Autowired
    InventoryRepository _repository;

    @Override
    public Response<Inventory, EnumStateCode> handle(AddInventoryCommand addInventoryCommand) {
        try {
            Inventory inventory = this._repository.save(toInventory(addInventoryCommand));
            return new Response<>(inventory, EnumStateCode.SUCCESS, null);
        } catch (Exception ex) {
            return new Response<>(null, EnumStateCode.ERROR, ex.getMessage());
        }
    }

    private Inventory toInventory(AddInventoryCommand command) {
        Inventory inventory = new Inventory();

        inventory.setItemName(command.getProductName());
        inventory.setPrice(Double.parseDouble(command.getProductPrice()));
        inventory.setFkSellerId(command.getSellerId());

        List<PhotoInventory> inventories = new ArrayList<>();

        for (PhotoDTO photoDTO : command.getPhotos()) {
            PhotoInventory newInventory = new PhotoInventory();
            newInventory.setName(photoDTO.getName());
            newInventory.setPath(photoDTO.getPath());

            inventories.add(newInventory);
        }

        inventory.setPhotos(inventories);

        return inventory;
    }
}
