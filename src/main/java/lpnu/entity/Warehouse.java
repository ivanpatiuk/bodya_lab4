package lpnu.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lpnu.config.Convertable;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Data
@ToString
public class Warehouse implements Convertable {
    private Long warehouseId;
    @NonNull
    private String address;
    @NonNull
    private Map<Long, WarehouseItem> storage;
}
