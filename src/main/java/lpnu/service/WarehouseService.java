package lpnu.service;

import lpnu.dto.OrderDTO;
import lpnu.dto.WarehouseDTO;
import lpnu.entity.Order;
import lpnu.entity.Warehouse;

public interface WarehouseService {
    WarehouseDTO getWarehouseById(final Long warehouseId);
    WarehouseDTO createWarehouse(final Warehouse warehouse);
    OrderDTO sendOrderToDelivery(final Long orderId);
    OrderDTO packOrder(final Long orderId);
    OrderDTO cancelOrder(final Long orderId);
    OrderDTO removeOrder(final Long orderId);
    Double getOrderPrice(final Order order);
}
