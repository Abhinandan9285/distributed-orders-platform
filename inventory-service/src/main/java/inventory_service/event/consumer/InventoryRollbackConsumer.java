package inventory_service.event.consumer;

import inventory_service.config.RabbitMQConfig;
import inventory_service.event.payload.InventoryRollbackEvent;
import inventory_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryRollbackConsumer {

    private final InventoryService inventoryService;

    @RabbitListener(queues = RabbitMQConfig.INVENTORY_ROLLBACK_QUEUE)
    public void rollbackInventory(InventoryRollbackEvent event) {
        log.info("Received Rollback inventory for order : {}", event.getOrderId());
        inventoryService.restoreInventory(event);
    }
}