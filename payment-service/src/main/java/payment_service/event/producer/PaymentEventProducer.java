package payment_service.event.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import payment_service.config.RabbitMQConfig;
import payment_service.event.payload.PaymentFailedEvent;
import payment_service.event.payload.PaymentSuccessEvent;

@Service
@RequiredArgsConstructor
public class PaymentEventProducer {

    private final RabbitTemplate rabbitTemplate;

    public void publishPaymentSuccessEvent(PaymentSuccessEvent event) {

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.ORDER_EXCHANGE,
                RabbitMQConfig.PAYMENT_SUCCESS_ROUTING_KEY,
                event
        );
    }

    public void publishPaymentFailedEvent(PaymentFailedEvent event) {

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.ORDER_EXCHANGE,
                RabbitMQConfig.PAYMENT_FAILED_ROUTING_KEY,
                event
        );
    }
}