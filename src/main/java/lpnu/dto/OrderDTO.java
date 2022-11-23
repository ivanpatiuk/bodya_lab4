package lpnu.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lpnu.entity.OrderItem;
import lpnu.enums.OrderStatus;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO {
    private Long orderId;
    private Long warehouseId;
    private List<OrderItem> orderItems = new ArrayList<>();
    private String deliveryCity;
    private String deliveryAddress;
    private OrderStatus orderStatus;
    private Double orderPrice;
}
