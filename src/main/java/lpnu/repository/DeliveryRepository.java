package lpnu.repository;

import lpnu.entity.Invoice;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.TreeMap;

@Repository
public class DeliveryRepository {

    private final Map<Long, Invoice> deliveryRepository = new TreeMap<>();
    private static Long freeId = 0L;

    public Invoice createInvoice(final Invoice invoice){
        invoice.setInvoiceId(++freeId);
        deliveryRepository.put(invoice.getInvoiceId(), invoice);
        return invoice;
    }

    public Invoice getInvoiceById(final Long invoiceId){
        return deliveryRepository.get(invoiceId);
    }

    public Invoice updateInvoice(final Invoice invoice){
        deliveryRepository.put(invoice.getInvoiceId(), invoice);
        return deliveryRepository.get(invoice.getInvoiceId());
    }

    public Invoice removeInvoice(final Long invoiceId){
        return deliveryRepository.remove(invoiceId);
    }
}
