package lpnu.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class WarehouseItem {
    private Item item;
    private Integer quantity;
    private Double pricePerOne;
}