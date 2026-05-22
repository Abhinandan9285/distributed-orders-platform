package order_service.event.consumer;

import common_lib.event.PaymentFailedEvent;
import common_lib.event.PaymentSuccessEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import order_service.config.RabbitMQConfig;
import order_service.service.OrderService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import static common_lib.constant.RabbitMQConstants.PAYMENT_FAILED_QUEUE;
import static common_lib.constant.RabbitMQConstants.PAYMENT_SUCCESS_QUEUE;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentEventConsumer {
    private final OrderService orderService;

    @RabbitListener(queues = PAYMENT_SUCCESS_QUEUE)
    public void consumePaymentSuccessEvent(PaymentSuccessEvent event) {
        orderService.markOrderConfirmed(event.getOrderId());
        log.info("Order confirmed : {}", event.getOrderId());
    }

    @RabbitListener(queues = PAYMENT_FAILED_QUEUE)
    public void consumePaymentFailedEvent(PaymentFailedEvent event) {
        orderService.markOrderFailed(event.getOrderId(),event.getReason());
        log.info("Order failed : {}", event.getOrderId());
    }
}