package common_lib.constant;

public class RabbitMQConstants {

    private RabbitMQConstants(){}

    public static final String ORDER_EXCHANGE =
            "order.exchange";

    public static final String ORDER_QUEUE =
            "order.queue";

    public static final String ORDER_ROUTING_KEY =
            "order.routing.key";

    public static final String ORDER_DLQ =
            "order.dlq";

    public static final String INVENTORY_SUCCESS_QUEUE =
            "inventory.success.queue";

    public static final String INVENTORY_FAILED_QUEUE =
            "inventory.failed.queue";

    public static final String INVENTORY_SUCCESS_ROUTING_KEY =
            "inventory.success.routing.key";

    public static final String INVENTORY_FAILED_ROUTING_KEY =
            "inventory.failed.routing.key";

    public static final String PAYMENT_SUCCESS_QUEUE =
            "payment.success.queue";

    public static final String PAYMENT_FAILED_QUEUE =
            "payment.failed.queue";

    public static final String PAYMENT_SUCCESS_ROUTING_KEY =
            "payment.success.routing.key";

    public static final String PAYMENT_FAILED_ROUTING_KEY =
            "payment.failed.routing.key";

    public static final String ORDER_CONFIRMED_ROUTING_KEY =
            "order.confirmed.routing.key";

    public static final String ORDER_FAILED_ROUTING_KEY =
            "order.failed.routing.key";

    public static final String INVENTORY_ROLLBACK_QUEUE =
            "inventory.rollback.queue";

    public static final String INVENTORY_ROLLBACK_ROUTING_KEY =
            "inventory.rollback.routing.key";

    public static final String ORDER_CONFIRMED_NOTIFICATION_QUEUE =
            "order.confirmed.notification.queue";
    public static final String ORDER_FAILED_NOTIFICATION_QUEUE =
            "order.failed.notification.queue";
}