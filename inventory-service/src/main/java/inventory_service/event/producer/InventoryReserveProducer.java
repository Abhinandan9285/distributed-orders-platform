package inventory_service.event.producer;

import inventory_service.config.RabbitMQConfig;
import inventory_service.event.payload.InventoryFailedEvent;
import inventory_service.event.payload.InventoryReservedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryReserveProducer {

    private final RabbitTemplate rabbitTemplate;

    public void publishSuccessEvent(InventoryReservedEvent event) {
        log.info("Inventory Reserved For Order Event: {}",event);
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.ORDER_EXCHANGE,
                RabbitMQConfig.INVENTORY_SUCCESS_ROUTING_KEY,
                event
        );
    }

    public void publishFailureEvent(InventoryFailedEvent event) {
        log.info("Inventory Failed To Reserve For Order Event: {}",event);
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.ORDER_EXCHANGE,
                RabbitMQConfig.INVENTORY_FAILED_ROUTING_KEY,
                event
        );
    }
}