package payment_service.event.consumer;

import common_lib.event.InventoryReservedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import payment_service.config.RabbitMQConfig;
import payment_service.service.PaymentService;

import static common_lib.constant.RabbitMQConstants.INVENTORY_SUCCESS_QUEUE;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentEventConsumer {

    private final PaymentService paymentService;

    @RabbitListener(queues = INVENTORY_SUCCESS_QUEUE)
    public void processPayment(InventoryReservedEvent event) {
        log.info("Inventory success event received : {}", event);
        paymentService.processPayment(event);
    }
}