package lpnu.dto;

import lombok.*;
import lpnu.entity.Item;
import lpnu.entity.WarehouseItem;


import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WarehouseDTO {
    private Long warehouseId;
    private String address;
    private Map<Long, WarehouseItem> storage;
}
