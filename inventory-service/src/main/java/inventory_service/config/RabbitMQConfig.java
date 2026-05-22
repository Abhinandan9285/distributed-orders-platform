package inventory_service.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.CircuitBreaker;

import java.util.HashMap;

import static common_lib.constant.RabbitMQConstants.*;

@Configuration
public class RabbitMQConfig {
//
//    /*
//     * Exchange
//     */
//    public static final String ORDER_EXCHANGE = "order.exchange";
//
//    /*
//     * Queues
//     */
//    public static final String ORDER_QUEUE = "order.queue";
//    public static final String INVENTORY_SUCCESS_QUEUE = "inventory.success.queue";
//    public static final String INVENTORY_FAILED_QUEUE = "inventory.failed.queue";
//    public static final String INVENTORY_ROLLBACK_QUEUE = "inventory.rollback.queue";
//
//    /*
//     * Routing Keys
//     */
//    public static final String ORDER_ROUTING_KEY = "order.routing.key";
//    public static final String INVENTORY_SUCCESS_ROUTING_KEY = "inventory.success.routing.key";
//    public static final String INVENTORY_FAILED_ROUTING_KEY = "inventory.failed.routing.key";
//    public static final String INVENTORY_ROLLBACK_ROUTING_KEY = "inventory.rollback.routing.key";

    @Bean
    public DirectExchange orderExchange() {
        return new DirectExchange(ORDER_EXCHANGE);
    }

    @Bean
    public Queue orderQueue() {

        HashMap<String, Object> args = new HashMap<>();

        args.put("x-dead-letter-exchange", "");
        args.put("x-dead-letter-routing-key", "order.dlq");

        return new Queue(
                ORDER_QUEUE,
                true,
                false,
                false,
                args
        );
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
    public Queue inventoryFailedQueue() {
        return new Queue(INVENTORY_FAILED_QUEUE);
    }

    /*
     * Bindings
     */

    @Bean
    public Binding inventoryRollbackBinding(@Qualifier("inventoryRollbackQueue") Queue inventoryRollbackQueue, DirectExchange orderExchange) {
        return BindingBuilder
                .bind(inventoryRollbackQueue)
                .to(orderExchange)
                .with(INVENTORY_ROLLBACK_ROUTING_KEY);
    }

    @Bean
    public Binding orderBinding(@Qualifier("orderQueue") Queue orderQueue, DirectExchange orderExchange) {
        return BindingBuilder
                .bind(orderQueue)
                .to(orderExchange)
                .with(ORDER_ROUTING_KEY);
    }

    @Bean
    public Binding inventorySuccessBinding(@Qualifier("inventorySuccessQueue") Queue inventorySuccessQueue, DirectExchange orderExchange) {
        return BindingBuilder
                .bind(inventorySuccessQueue)
                .to(orderExchange)
                .with(INVENTORY_SUCCESS_ROUTING_KEY);
    }

    @Bean
    public Binding inventoryFailedBinding(@Qualifier("inventoryFailedQueue") Queue inventoryFailedQueue, DirectExchange orderExchange) {
        return BindingBuilder
                .bind(inventoryFailedQueue)
                .to(orderExchange)
                .with(INVENTORY_FAILED_ROUTING_KEY);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}