package lpnu.controller;

import lpnu.dto.OrderDTO;
import lpnu.dto.WarehouseDTO;
import lpnu.entity.Order;
import lpnu.entity.Warehouse;
import lpnu.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/warehouse/")
@RestController
public class WarehouseController {

    private final WarehouseService warehouseService;

    @Autowired
    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @GetMapping("get-warehouse-by-id/{id}")
    public WarehouseDTO getWarehouseById(@PathVariable final Long id) {
        return warehouseService.getWarehouseById(id); }

    @PostMapping("create-warehouse")
    public WarehouseDTO createWarehouse(@RequestBody final Warehouse warehouse) {
        return warehouseService.createWarehouse(warehouse);
    }

    @PutMapping("pack-order/{id}")
    public OrderDTO packOrder(@PathVariable final Long id) {
        return warehouseService.packOrder(id);
    }

    @PutMapping("send-order-to-delivery/{id}")
    public OrderDTO sendOrderToDelivery(@PathVariable final Long id) {
        return warehouseService.sendOrderToDelivery(id);
    }

    @PutMapping("cancel-order/{id}")
    public OrderDTO cancelOrder(@PathVariable final Long id) {
        return warehouseService.cancelOrder(id);
    }

    @DeleteMapping("remove-order/{id}")
    public OrderDTO removeOrder(@PathVariable final Long id) {
        return warehouseService.removeOrder(id);
    }
}