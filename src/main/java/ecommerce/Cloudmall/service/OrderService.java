package ecommerce.Cloudmall.service;

import ecommerce.Cloudmall.model.Order;
import ecommerce.Cloudmall.model.OrderItem;
import ecommerce.Cloudmall.model.Cart;
import ecommerce.Cloudmall.repository.OrderRepository;
import ecommerce.Cloudmall.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    public Order placeOrder(String userId) {
        Cart cart = cartRepository.findByUserId(userId).orElseThrow();

        double totalPrice = cart.getItems()
                .stream()
                .mapToDouble(item -> item.getQuantity() * 100.0) // dummy price logic (replace later)
                .sum();

        Order order = new Order();
        order.setUserId(userId);
        order.setItems(cart.getItems().stream()
                .map(item -> new OrderItem(item.getProductId(), item.getQuantity(), 100.0)) // dummy price
                .toList());
        order.setTotalPrice(totalPrice);
        order.setStatus("PLACED");
        order.setCreatedAt(LocalDateTime.now());

        cart.getItems().clear(); // empty cart after placing order
        cartRepository.save(cart);

        return orderRepository.save(order);
    }

    public List<Order> getOrdersByUser(String userId) {
        return orderRepository.findByUserId(userId);
    }

    public Order updateOrderStatus(String orderId, String status) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        order.setStatus(status);
        return orderRepository.save(order);
    }
}
