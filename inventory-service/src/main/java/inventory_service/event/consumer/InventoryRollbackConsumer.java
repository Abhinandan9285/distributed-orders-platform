package inventory_service.event.consumer;

import common_lib.event.InventoryRollbackEvent;
import inventory_service.config.RabbitMQConfig;
import inventory_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import static common_lib.constant.RabbitMQConstants.INVENTORY_ROLLBACK_QUEUE;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryRollbackConsumer {

    private final InventoryService inventoryService;

    @RabbitListener(queues = INVENTORY_ROLLBACK_QUEUE)
    public void rollbackInventory(InventoryRollbackEvent event) {
        log.info("Received Rollback inventory for order : {}", event.getOrderId());
        inventoryService.restoreInventory(event);
    }
}