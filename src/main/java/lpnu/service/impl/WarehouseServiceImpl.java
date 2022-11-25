package lpnu.service.impl;

import lpnu.config.DTOConvertor;
import lpnu.dto.OrderDTO;
import lpnu.dto.WarehouseDTO;
import lpnu.entity.*;
import lpnu.enums.OrderStatus;
import lpnu.repository.ItemRepository;
import lpnu.repository.OrderRepository;
import lpnu.repository.WarehouseRepository;
import lpnu.service.DeliveryService;
import lpnu.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepository warehouseRepository;
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;
    private final DeliveryService deliveryService;

    @Autowired
    public WarehouseServiceImpl(WarehouseRepository warehouseRepository, ItemRepository itemRepository,
                                OrderRepository orderRepository, DeliveryService deliveryService) {
        this.warehouseRepository = warehouseRepository;
        this.itemRepository = itemRepository;
        this.orderRepository = orderRepository;
        this.deliveryService = deliveryService;
    }

    @Override
    public WarehouseDTO getWarehouseById(final Long warehouseId) {
        return DTOConvertor.convertToDto(warehouseRepository.getWarehouseById(warehouseId), WarehouseDTO.class);
    }

    @Override
    public Double getOrderPrice(final Order order) {
        final Warehouse warehouse = warehouseRepository.getWarehouseById(order.getWarehouseId());
        Double orderPrice = 0.0;
        for (OrderItem orderItem : order.getOrderItems()) {
            final Item item = itemRepository.getItemById(orderItem.getItemId());
            // Якщо на складі недостатньо товару, викинути ексепшн
            if(orderItem.getQuantity() > warehouseRepository
                    .getWarehouseById(order
                            .getWarehouseId())
                    .getStorage()
                    .get(item
                            .getItemId())
                    .getQuantity()){
                throw new RuntimeException();
            }
            orderPrice += warehouse.getStorage().get(item.getItemId()).getPricePerOne();
        }
        if (orderPrice == 0.0) {
            throw new RuntimeException();
        }
        return orderPrice;
    }

    @Override
    public WarehouseDTO createWarehouse(final Warehouse warehouse) {
        return DTOConvertor.convertToDto(warehouseRepository.createWarehouse(warehouse), WarehouseDTO.class);
    }

    @Override
    public OrderDTO packOrder(final Long orderId) {
        final Order order = orderRepository.getOrderById(orderId);
        if(order.getOrderStatus()==OrderStatus.PAID) {
            // ... пакування
            order.setOrderStatus(OrderStatus.PACKED);
            orderRepository.updateOrder(order);
            return OrderServiceImpl.toDTO(order);
        } else {
            // Якщо статус інший, то запакувати замовлення не можна
            throw new RuntimeException();
        }
    }

    @Override
    public OrderDTO sendOrderToDelivery(final Long orderId) {
        return deliveryService.createDelivery(orderId);
    }

    @Override
    public OrderDTO cancelOrder(final Long orderId) {
        final Order order = orderRepository.getOrderById(orderId);
        if (order.getOrderStatus() == OrderStatus.CREATED || order.getOrderStatus() == OrderStatus.AWAITING_ON_DELIVERY || order.getOrderStatus() == OrderStatus.PACKED) {
            order.setOrderStatus(OrderStatus.CANCELLED);
            return OrderServiceImpl.toDTO(orderRepository.updateOrder(order));
        } else {
            // Якщо статус інший, то скасувати замовлення не можна
            throw new RuntimeException();
        }
    }

    @Override
    public OrderDTO removeOrder(final Long orderId) {
        final Order order = orderRepository.getOrderById(orderId);
        if(order.getOrderStatus() == OrderStatus.CREATED || order.getOrderStatus() == OrderStatus.AWAITING_ON_DELIVERY || order.getOrderStatus() == OrderStatus.PACKED){
            // ... повернути кошти
            return OrderServiceImpl.toDTO(orderRepository.removeOrderById(orderId));
        } else {
            // Якщо статус інший, то видалити замовлення не можна
            throw new RuntimeException();
        }
    }
}
