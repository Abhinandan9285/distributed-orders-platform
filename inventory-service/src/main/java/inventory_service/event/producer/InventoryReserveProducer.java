package inventory_service.event.producer;

import common_lib.event.InventoryFailedEvent;
import common_lib.event.InventoryReservedEvent;
import inventory_service.config.RabbitMQConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import static common_lib.constant.RabbitMQConstants.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryReserveProducer {

    private final RabbitTemplate rabbitTemplate;

    public void publishSuccessEvent(InventoryReservedEvent event) {
        log.info("Inventory Reserved For Order Event: {}",event);
        rabbitTemplate.convertAndSend(
                ORDER_EXCHANGE,
                INVENTORY_SUCCESS_ROUTING_KEY,
                event
        );
    }

    public void publishFailureEvent(InventoryFailedEvent event) {
        log.info("Inventory Failed To Reserve For Order Event: {}",event);
        rabbitTemplate.convertAndSend(
                ORDER_EXCHANGE,
                INVENTORY_FAILED_ROUTING_KEY,
                event
        );
    }
}