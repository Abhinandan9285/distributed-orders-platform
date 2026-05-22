package order_service.event.consumer;

import common_lib.event.InventoryFailedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import order_service.config.RabbitMQConfig;
import order_service.service.OrderService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import static common_lib.constant.RabbitMQConstants.INVENTORY_FAILED_QUEUE;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryEventConsumer {

    private final OrderService orderService;

    @RabbitListener(queues = INVENTORY_FAILED_QUEUE)
    public void consumeInventoryFailedEvent(InventoryFailedEvent event) {

        log.info("Inventory Failed Event Received : {}", event);

        orderService.markOrderFailed(event.getOrderId(), event.getReason());
    }
}