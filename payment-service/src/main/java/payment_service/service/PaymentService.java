package payment_service.service;

import payment_service.event.payload.InventoryReservedEvent;

public interface PaymentService {
    void processPayment(InventoryReservedEvent event);
}