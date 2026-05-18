package payment_service.event.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryRollbackEvent {

    private UUID orderId;

    private String productCode;

    private Integer quantity;
}