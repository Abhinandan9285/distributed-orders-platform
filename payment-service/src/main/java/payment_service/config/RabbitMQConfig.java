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

@Configuration
public class RabbitMQConfig {

    /*
     * Exchanges
     */
    public static final String ORDER_EXCHANGE = "order.exchange";

    /*
     * Queues
     */
    public static final String INVENTORY_SUCCESS_QUEUE = "inventory.success.queue";

    public static final String PAYMENT_SUCCESS_QUEUE = "payment.success.queue";

    public static final String PAYMENT_FAILED_QUEUE = "payment.failed.queue";


    /*
     * Routing Keys
     */
    public static final String INVENTORY_SUCCESS_ROUTING_KEY = "inventory.success.routing.key";

    public static final String PAYMENT_SUCCESS_ROUTING_KEY = "payment.success.routing.key";

    public static final String PAYMENT_FAILED_ROUTING_KEY = "payment.failed.routing.key";


    @Bean
    public DirectExchange orderExchange() {
        return new DirectExchange(ORDER_EXCHANGE);
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