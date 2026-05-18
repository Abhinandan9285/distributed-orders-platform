package payment_service.event.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import payment_service.config.RabbitMQConfig;
import payment_service.event.payload.InventoryReservedEvent;
import payment_service.service.PaymentService;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentEventConsumer {

    private final PaymentService paymentService;

    @RabbitListener(queues = RabbitMQConfig.INVENTORY_SUCCESS_QUEUE)
    public void processPayment(InventoryReservedEvent event) {
        log.info("Inventory success event received : {}", event);
        paymentService.processPayment(event);
    }
}