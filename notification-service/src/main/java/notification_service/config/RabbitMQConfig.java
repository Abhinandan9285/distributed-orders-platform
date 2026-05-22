package notification_service.config;

import org.springframework.amqp.core.*;
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
    public Queue orderConfirmedNotificationQueue() {
        return new Queue(ORDER_CONFIRMED_NOTIFICATION_QUEUE);
    }

    @Bean
    public Queue orderFailedNotificationQueue() {
        return new Queue(ORDER_FAILED_NOTIFICATION_QUEUE);
    }


    @Bean
    public Binding orderConfirmedBinding(@Qualifier("orderConfirmedNotificationQueue") Queue queue, DirectExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(ORDER_CONFIRMED_ROUTING_KEY);
    }

    @Bean
    public Binding orderFailedBinding(@Qualifier("orderFailedNotificationQueue") Queue queue, DirectExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(ORDER_FAILED_ROUTING_KEY);
    }


    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}