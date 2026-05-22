package inventory_service.controller;

import common_lib.dto.response.InventoryResponse;
import inventory_service.entity.Inventory;
import inventory_service.repository.InventoryRepository;
import inventory_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/{productCode}")
    public InventoryResponse getInventory(@PathVariable("productCode") String productCode) {
        return inventoryService.getInventory(productCode);
    }
}