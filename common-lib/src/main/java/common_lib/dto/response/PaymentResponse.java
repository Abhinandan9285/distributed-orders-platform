package common_lib.dto.response;

import common_lib.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponse {

    private UUID orderId;

    private Double amount;

    private PaymentStatus status;
}