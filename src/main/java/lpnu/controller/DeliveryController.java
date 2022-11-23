package lpnu.controller;

import lpnu.dto.InvoiceDTO;
import lpnu.entity.Invoice;
import lpnu.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/delivery/")
@RestController
public class DeliveryController {

    private final DeliveryService deliveryService;
    @Autowired
    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @GetMapping("get-invoice-by-id/{id}")
    public InvoiceDTO getInvoiceById(@PathVariable final Long id) { return deliveryService.getInvoiceById(id); }

    @PutMapping("send-order/{id}")
    public InvoiceDTO sendOrder(@PathVariable final Long id) {
        return deliveryService.sendOrder(id);
    }

    @DeleteMapping("remove-invoice/{id}")
    public InvoiceDTO removeInvoice(@PathVariable final Long id) {
        return deliveryService.removeInvoice(id);
    }
}
