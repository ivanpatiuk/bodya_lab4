package lpnu.controller;

import lpnu.dto.OrderDTO;
import lpnu.service.OrderService;
import lpnu.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/customer/")
@RestController
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("get-order-by-id/{id}")
    public OrderDTO getOrderById(@PathVariable final Long id) {
        return orderService.getOrderById(id);
    }

    @PostMapping("create-order")
    public OrderDTO createOrder(@RequestBody final Order order) {
        return orderService.createOrder(order);
    }

    @PutMapping("pay-order/{id}")
    public OrderDTO payOrder(@PathVariable final Long id, @RequestHeader(name = "sum") final Double sum) {
        return orderService.payOrder(id, sum);
    }

    @PutMapping("cancel-order/{id}")
    public OrderDTO cancelOrder(@PathVariable final Long id) {
        return orderService.cancelOrder(id);
    }

    @DeleteMapping("remove-order/{id}")
    public OrderDTO removeOrder(@PathVariable final Long id) {
        return orderService.removeOrder(id);
    }

    @PutMapping("approve-order-receiving/{id}")
    public OrderDTO approveOrderReceiving(@PathVariable final Long id) {
        return orderService.approveOrderReceiving(id);
    }
}