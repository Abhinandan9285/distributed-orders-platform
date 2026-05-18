package payment_service.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import payment_service.constant.PaymentStatus;
import payment_service.entity.Payment;
import payment_service.event.payload.InventoryReservedEvent;
import payment_service.event.payload.InventoryRollbackEvent;
import payment_service.event.payload.PaymentFailedEvent;
import payment_service.event.payload.PaymentSuccessEvent;
import payment_service.event.producer.PaymentEventProducer;
import payment_service.repository.PaymentRepository;
import payment_service.service.PaymentService;

import java.util.Random;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentEventProducer paymentEventProducer;

    @Override
    public void processPayment(InventoryReservedEvent event) {

        log.info("Processing payment for order : {}", event.getOrderId());

        boolean paymentSuccess = new Random().nextBoolean();

        if (paymentSuccess) {

            Payment payment = Payment.builder()
                    .orderId(event.getOrderId())
                    .amount(1000.0)
                    .status(PaymentStatus.SUCCESS)
                    .build();

            paymentRepository.save(payment);

            paymentEventProducer.publishPaymentSuccessEvent(
                    new PaymentSuccessEvent(
                            event.getOrderId(),
                            1000.0
                    )
            );

        } else {

            Payment payment = Payment.builder()
                    .orderId(event.getOrderId())
                    .amount(1000.0)
                    .status(PaymentStatus.FAILED)
                    .build();

            paymentRepository.save(payment);

            paymentEventProducer.publishPaymentFailedEvent(
                    new PaymentFailedEvent(
                            event.getOrderId(),
                            "Insufficient balance"
                    )
            );

            paymentEventProducer.publishInventoryRollBackEvent(
                    new InventoryRollbackEvent(
                    event.getOrderId(),
                    event.getProductCode(),
                    event.getQuantity()
            ));

        }
    }
}