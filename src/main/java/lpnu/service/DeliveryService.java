package lpnu.service;

import lpnu.dto.InvoiceDTO;
import lpnu.dto.OrderDTO;
import lpnu.entity.Order;

public interface DeliveryService {
    InvoiceDTO createInvoice(final Order order);
    OrderDTO createDelivery(final Long invoiceId);
    InvoiceDTO sendOrder(final Long invoiceId);
    InvoiceDTO getInvoiceById(final Long invoiceId);
    InvoiceDTO removeInvoice(final Long invoiceId);
}
