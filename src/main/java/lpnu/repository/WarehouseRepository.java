package lpnu.repository;

import lombok.ToString;
import lpnu.entity.Warehouse;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.TreeMap;

@Repository
public class WarehouseRepository {
    private final Map<Long, Warehouse> warehouseRepository = new TreeMap<>();
    private static Long freeId = 0L;

    public Warehouse createWarehouse(final Warehouse warehouse){
        warehouse.setWarehouseId(++freeId);
        warehouseRepository.put(warehouse.getWarehouseId(), warehouse);
        return warehouse;
    }

    public Warehouse getWarehouseById(final Long warehouseId){
        System.out.println(2);
        return warehouseRepository.get(warehouseId);
    }

    public Warehouse updateWarehouse(final Warehouse warehouse){
        warehouseRepository.put(warehouse.getWarehouseId(), warehouse);
        return warehouseRepository.get(warehouse.getWarehouseId());
    }

    public Warehouse removeWarehouse(final Long warehouseId){
        return warehouseRepository.remove(warehouseId);
    }

}
