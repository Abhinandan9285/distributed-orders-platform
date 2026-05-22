package notification_service.event.consumer;

import common_lib.event.OrderConfirmedEvent;
import common_lib.event.OrderFailedEvent;
import lombok.extern.slf4j.Slf4j;
import notification_service.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import static common_lib.constant.RabbitMQConstants.ORDER_CONFIRMED_NOTIFICATION_QUEUE;
import static common_lib.constant.RabbitMQConstants.ORDER_FAILED_NOTIFICATION_QUEUE;

@Service
@Slf4j
public class NotificationConsumer {
    @RabbitListener(queues = ORDER_CONFIRMED_NOTIFICATION_QUEUE)
    public void consumeOrderConfirmedEvent(OrderConfirmedEvent event) {
        log.info("EMAIL SENT : Order Confirmed : {}", event.getOrderId());
    }

    @RabbitListener(queues = ORDER_FAILED_NOTIFICATION_QUEUE)
    public void consumeOrderFailedEvent(OrderFailedEvent event) {
        log.info("EMAIL SENT : Order Failed : {} Reason : {}",
                event.getOrderId(),
                event.getReason()
        );
    }
}