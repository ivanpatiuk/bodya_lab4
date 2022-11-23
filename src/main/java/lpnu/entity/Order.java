package lpnu.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lpnu.config.Convertable;
import lpnu.enums.OrderStatus;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Order implements Convertable {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long orderId;
    private Long warehouseId;
    private List<OrderItem> orderItems = new ArrayList<>();
    private String deliveryCity;
    private String deliveryAddress;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OrderStatus orderStatus;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Double orderPrice;
}
