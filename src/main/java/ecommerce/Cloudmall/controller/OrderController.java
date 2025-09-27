package ecommerce.Cloudmall.controller;

import ecommerce.Cloudmall.model.Order;
import ecommerce.Cloudmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // ✅ Place order
    @PostMapping("/{userId}")
    public Order placeOrder(@PathVariable String userId) {
        return orderService.placeOrder(userId); // correct service method
    }

    // ✅ Get orders by user
    @GetMapping("/{userId}")
    public List<Order> getOrdersByUser(@PathVariable String userId) {
        return orderService.getOrdersByUser(userId);
    }

    // ✅ Update order status
    @PutMapping("/{orderId}/status")
    public Order updateOrderStatus(@PathVariable String orderId, @RequestParam String status) {
        return orderService.updateOrderStatus(orderId, status);
    }
}
