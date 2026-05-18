package payment_service.event.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import payment_service.config.RabbitMQConfig;
import payment_service.event.payload.InventoryRollbackEvent;
import payment_service.event.payload.PaymentFailedEvent;
import payment_service.event.payload.PaymentSuccessEvent;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentEventProducer {

    private final RabbitTemplate rabbitTemplate;

    public void publishPaymentSuccessEvent(PaymentSuccessEvent event) {
        log.info("Payment success for order : {}", event.getOrderId());
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.ORDER_EXCHANGE,
                RabbitMQConfig.PAYMENT_SUCCESS_ROUTING_KEY,
                event
        );
    }

    public void publishPaymentFailedEvent(PaymentFailedEvent event) {
        log.info("Payment failed for order : {}", event);
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.ORDER_EXCHANGE,
                RabbitMQConfig.PAYMENT_FAILED_ROUTING_KEY,
                event
        );
    }

    public void publishInventoryRollBackEvent(InventoryRollbackEvent event) {
        log.info("Inventory rollback triggered for order : {}", event);
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.ORDER_EXCHANGE,
                RabbitMQConfig.INVENTORY_ROLLBACK_ROUTING_KEY,
                event
        );
    }
}