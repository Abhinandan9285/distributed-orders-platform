package order_service.dto.response;

import common_lib.enums.OrderStatus;
import common_lib.dto.response.InventoryResponse;
import common_lib.dto.response.PaymentResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsResponse {

    private UUID orderId;

    private String productCode;

    private Integer quantity;

    private OrderStatus status;

    private InventoryResponse inventory;

    private PaymentResponse payment;
}