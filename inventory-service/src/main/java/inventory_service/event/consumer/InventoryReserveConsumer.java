package inventory_service.event.consumer;

import common_lib.event.OrderCreatedEvent;
import inventory_service.config.RabbitMQConfig;
import inventory_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import static common_lib.constant.RabbitMQConstants.ORDER_QUEUE;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryReserveConsumer {

    private final InventoryService inventoryService;

    @RabbitListener(queues = ORDER_QUEUE)
    public void consumeOrderCreatedEvent(OrderCreatedEvent event) {
        log.info("Received Order Event For Reservation: {}", event);
        inventoryService.reserveInventory(event);
    }
}