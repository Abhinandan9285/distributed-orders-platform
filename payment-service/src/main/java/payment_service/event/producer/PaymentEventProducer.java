package payment_service.event.producer;

import common_lib.event.InventoryRollbackEvent;
import common_lib.event.PaymentFailedEvent;
import common_lib.event.PaymentSuccessEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import payment_service.config.RabbitMQConfig;

import static common_lib.constant.RabbitMQConstants.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentEventProducer {

    private final RabbitTemplate rabbitTemplate;

    public void publishPaymentSuccessEvent(PaymentSuccessEvent event) {
        log.info("Payment success for order : {}", event.getOrderId());
        rabbitTemplate.convertAndSend(
                ORDER_EXCHANGE,
                PAYMENT_SUCCESS_ROUTING_KEY,
                event
        );
    }

    public void publishPaymentFailedEvent(PaymentFailedEvent event) {
        log.info("Payment failed for order : {}", event);
        rabbitTemplate.convertAndSend(
                ORDER_EXCHANGE,
                PAYMENT_FAILED_ROUTING_KEY,
                event
        );
    }

    public void publishInventoryRollBackEvent(InventoryRollbackEvent event) {
        log.info("Inventory rollback triggered for order : {}", event);
        rabbitTemplate.convertAndSend(
                ORDER_EXCHANGE,
                INVENTORY_ROLLBACK_ROUTING_KEY,
                event
        );
    }
}