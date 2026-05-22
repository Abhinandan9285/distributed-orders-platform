package payment_service.entity;

import common_lib.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID paymentId;

    private UUID orderId;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
}