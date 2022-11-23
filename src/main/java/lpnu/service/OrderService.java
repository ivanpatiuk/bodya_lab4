package lpnu.service;

import lpnu.dto.OrderDTO;
import lpnu.entity.Order;

public interface OrderService {
    OrderDTO getOrderById(final Long id);
    OrderDTO createOrder(final Order order);
    OrderDTO payOrder(final Long orderId, final Double sum);
    OrderDTO removeOrder(final Long orderId);
    OrderDTO cancelOrder(final Long orderId);
    OrderDTO approveOrderReceiving(final Long orderId);
}
