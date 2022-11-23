package lpnu.service.impl;

import lpnu.config.DTOConvertor;
import lpnu.dto.OrderDTO;
import lpnu.entity.Order;
import lpnu.enums.OrderStatus;
import lpnu.repository.OrderRepository;
import lpnu.service.OrderService;
import lpnu.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static lpnu.enums.OrderStatus.*;

@Service
public class OrderServiceImpl implements OrderService {

    private final WarehouseService warehouseService;
    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(WarehouseService warehouseService, OrderRepository orderRepository) {
        this.warehouseService = warehouseService;
        this.orderRepository = orderRepository;
    }

    public static OrderDTO toDTO(final Order order){
        return DTOConvertor.convertToDto(order, OrderDTO.class);
    }

    @Override
    public OrderDTO getOrderById(final Long id) {
        return toDTO(orderRepository.getOrderById(id));
    }

    @Override
    public OrderDTO createOrder(final Order order) {
        order.setOrderStatus(OrderStatus.CREATED);
        final Double orderPrice = warehouseService.getOrderPrice(order);
        if (orderPrice == null) {
            throw new RuntimeException();
        }
        order.setOrderPrice(orderPrice);
        orderRepository.addOrder(order);
        return toDTO(order);
    }

    @Override
    public OrderDTO payOrder(final Long orderId, final Double sum) {
        final Order order = orderRepository.getOrderById(orderId);
        if (Objects.equals(order.getOrderPrice(), sum)) {
            order.setOrderStatus(PAID);
        }
        return toDTO(orderRepository.updateOrder(order));
    }

    @Override
    public OrderDTO removeOrder(final Long orderId) {
        final Order order = orderRepository.getOrderById(orderId);
        if (order.getOrderStatus() == CREATED) {
            return toDTO(orderRepository.removeOrderById(orderId));
        } else {
            // Якщо статус інший, то це потрібно робити через контрол складу
            throw new RuntimeException();
        }
    }

    @Override
    public OrderDTO cancelOrder(final Long orderId) {
        final Order order = orderRepository.getOrderById(orderId);
        order.setOrderStatus(CANCELLED);
        return toDTO(orderRepository.updateOrder(order));
    }

    @Override
    public OrderDTO approveOrderReceiving(Long orderId) {
        final Order order = orderRepository.getOrderById(orderId);
        order.setOrderStatus(RECEIVED);
        return toDTO(orderRepository.updateOrder(order));
    }
}
