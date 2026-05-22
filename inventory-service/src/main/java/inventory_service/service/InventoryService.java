package inventory_service.service;

import common_lib.dto.response.InventoryResponse;
import common_lib.event.InventoryRollbackEvent;
import common_lib.event.OrderCreatedEvent;
import org.springframework.web.bind.annotation.PathVariable;

public interface InventoryService {
    void reserveInventory(OrderCreatedEvent event);
    void restoreInventory(InventoryRollbackEvent event);
    InventoryResponse getInventory(String productCode);
}
