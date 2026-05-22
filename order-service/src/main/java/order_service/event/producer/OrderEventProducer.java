package order_service.event.producer;

import common_lib.event.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import order_service.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import static common_lib.constant.RabbitMQConstants.ORDER_EXCHANGE;
import static common_lib.constant.RabbitMQConstants.ORDER_ROUTING_KEY;

@Service
@RequiredArgsConstructor
public class OrderEventProducer {

    private final RabbitTemplate rabbitTemplate;

    public void publishOrderCreatedEvent(OrderCreatedEvent event) {

        rabbitTemplate.convertAndSend(
                ORDER_EXCHANGE,
                ORDER_ROUTING_KEY,
                event
        );
    }
}