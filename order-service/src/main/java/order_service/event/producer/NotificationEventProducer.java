package order_service.event.producer;

import common_lib.event.OrderConfirmedEvent;
import common_lib.event.OrderFailedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import order_service.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import static common_lib.constant.RabbitMQConstants.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationEventProducer {

    private final RabbitTemplate rabbitTemplate;

    public void publishOrderConfirmedEvent(OrderConfirmedEvent event) {

        rabbitTemplate.convertAndSend(
                ORDER_EXCHANGE,
                ORDER_CONFIRMED_ROUTING_KEY,
                event
        );
    }

    public void publishOrderFailedEvent(OrderFailedEvent event) {
        rabbitTemplate.convertAndSend(
               ORDER_EXCHANGE,
                ORDER_FAILED_ROUTING_KEY,
                event
        );
    }
}