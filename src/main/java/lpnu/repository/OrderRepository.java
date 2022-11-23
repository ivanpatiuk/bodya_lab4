package lpnu.repository;

import lpnu.entity.Order;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.TreeMap;

@Repository
public class OrderRepository {
    private final Map<Long, Order> orderRepository = new TreeMap<>();
    private static Long freeId = 0L;

    public Order addOrder(final Order order){
        order.setOrderId(++freeId);
        orderRepository.put(order.getOrderId(), order);
        return order;
    }

    public Order getOrderById(final Long orderId){
        return orderRepository.get(orderId);
    }

    public Order updateOrder(final Order order){
        orderRepository.put(order.getOrderId(), order);
        return orderRepository.get(order.getOrderId());
    }

    public Order removeOrderById(final Long orderId){
        return orderRepository.remove(orderId);
    }
}
