package order_service.event.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import order_service.config.RabbitMQConfig;
import order_service.constant.OrderStatus;
import order_service.entity.Order;
import order_service.event.payload.PaymentFailedEvent;
import order_service.event.payload.PaymentSuccessEvent;
import order_service.repository.OrderRepository;
import order_service.service.OrderService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentEventConsumer {
    private final OrderService orderService;

    @RabbitListener(queues = RabbitMQConfig.PAYMENT_SUCCESS_QUEUE)
    public void consumePaymentSuccessEvent(PaymentSuccessEvent event) {
        orderService.markOrderConfirmed(event.getOrderId());
        log.info("Order confirmed : {}", event.getOrderId());
    }

    @RabbitListener(queues = RabbitMQConfig.PAYMENT_FAILED_QUEUE)
    public void consumePaymentFailedEvent(PaymentFailedEvent event) {
        orderService.markOrderFailed(event.getOrderId(),event.getReason());
        log.info("Order failed : {}", event.getOrderId());
    }
}