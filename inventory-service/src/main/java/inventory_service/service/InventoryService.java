package inventory_service.service;

import inventory_service.event.payload.InventoryRollbackEvent;
import inventory_service.event.payload.OrderCreatedEvent;

public interface InventoryService {
    void reserveInventory(OrderCreatedEvent event);
    void restoreInventory(InventoryRollbackEvent event);}