package lpnu.service.impl;

import lpnu.config.DTOConvertor;
import lpnu.dto.InvoiceDTO;
import lpnu.dto.OrderDTO;
import lpnu.entity.Invoice;
import lpnu.entity.Order;
import lpnu.enums.OrderStatus;
import lpnu.repository.DeliveryRepository;
import lpnu.repository.OrderRepository;
import lpnu.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final OrderRepository orderRepository;

    private static long freeId = 0;

    @Autowired
    public DeliveryServiceImpl(DeliveryRepository deliveryRepository, OrderRepository orderRepository) {
        this.deliveryRepository = deliveryRepository;
        this.orderRepository = orderRepository;
    }

    public static InvoiceDTO toDTO(final Invoice invoice) {
        return DTOConvertor.convertToDto(invoice, InvoiceDTO.class);
    }

    @Override
    public InvoiceDTO createInvoice(final Order order) {
        final Invoice invoice = new Invoice(++freeId, order);
        return toDTO(deliveryRepository.createInvoice(invoice));
    }

    @Override
    public OrderDTO createDelivery(final Long orderId) {
        final Order order = orderRepository.getOrderById(orderId);
        if(order.getOrderStatus() == OrderStatus.PACKED) {
            order.setOrderStatus(OrderStatus.AWAITING_ON_DELIVERY);
            // ... відправлення до відділу доставки (служба складу)
            return OrderServiceImpl.toDTO(orderRepository.updateOrder(order));
        } else {
            // Якщо статус інший, то створити доставку не можна
            throw new RuntimeException();
        }
    }

    @Override
    public InvoiceDTO sendOrder(final Long orderId) {
        final Order order = orderRepository.getOrderById(orderId);
        if (order.getOrderStatus() == OrderStatus.AWAITING_ON_DELIVERY) {
            // ... відправлення доставки
            order.setOrderStatus(OrderStatus.SENT);
            return createInvoice(order);
        } else {
            throw new RuntimeException();
        }
    }


    @Override
    public InvoiceDTO getInvoiceById(final Long invoiceId) {
        return toDTO(deliveryRepository.getInvoiceById(invoiceId));
    }

    @Override
    public InvoiceDTO removeInvoice(final Long invoiceId) {
        return toDTO(deliveryRepository.removeInvoice(invoiceId));
    }
}
