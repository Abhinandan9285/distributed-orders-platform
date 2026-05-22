package payment_service.service;

import common_lib.dto.response.PaymentResponse;
import common_lib.event.InventoryReservedEvent;

import java.util.UUID;

public interface PaymentService {
    void processPayment(InventoryReservedEvent event);
    PaymentResponse getPayment(UUID orderId);
}