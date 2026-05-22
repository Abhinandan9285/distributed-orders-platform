package payment_service.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static common_lib.constant.RabbitMQConstants.*;

@Configuration
public class RabbitMQConfig {

    @Bean
    public DirectExchange orderExchange() {
        return new DirectExchange(ORDER_EXCHANGE);
    }

    @Bean
    public Queue inventoryRollbackQueue() {
        return new Queue(INVENTORY_ROLLBACK_QUEUE);
    }

    @Bean
    public Queue inventorySuccessQueue() {
        return new Queue(INVENTORY_SUCCESS_QUEUE);
    }

    @Bean
    public Queue paymentSuccessQueue() {
        return new Queue(PAYMENT_SUCCESS_QUEUE);
    }

    @Bean
    public Queue paymentFailedQueue() {
        return new Queue(PAYMENT_FAILED_QUEUE);
    }


    /*
     * Bindings
     */

    @Bean
    public Binding inventorySuccessBinding(@Qualifier("inventorySuccessQueue")Queue inventorySuccessQueue, DirectExchange orderExchange) {
        return BindingBuilder
                .bind(inventorySuccessQueue)
                .to(orderExchange)
                .with(INVENTORY_SUCCESS_ROUTING_KEY);
    }
    @Bean
    public Binding inventoryRollbackBinding(@Qualifier("inventoryRollbackQueue") Queue inventoryRollbackQueue, DirectExchange orderExchange) {
        return BindingBuilder
                .bind(inventoryRollbackQueue)
                .to(orderExchange)
                .with(INVENTORY_ROLLBACK_ROUTING_KEY);
    }

    @Bean
    public Binding paymentSuccessBinding( @Qualifier("paymentSuccessQueue") Queue paymentSuccessQueue, DirectExchange orderExchange) {
        return BindingBuilder
                .bind(paymentSuccessQueue)
                .to(orderExchange)
                .with(PAYMENT_SUCCESS_ROUTING_KEY);
    }

    @Bean
    public Binding paymentFailedBinding( @Qualifier("paymentFailedQueue") Queue paymentFailedQueue, DirectExchange orderExchange) {
        return BindingBuilder
                .bind(paymentFailedQueue)
                .to(orderExchange)
                .with(PAYMENT_FAILED_ROUTING_KEY);
    }


    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}