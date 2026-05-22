package order_service.service.feign;

import common_lib.dto.response.InventoryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "inventory-service")
public interface InventoryFeignClient {

    @GetMapping("/inventory/{productCode}")
    InventoryResponse getInventory(@PathVariable("productCode") String productCode);
}