package ecommerce.Cloudmall.service;

import ecommerce.Cloudmall.model.*;
import ecommerce.Cloudmall.repository.CartRepository;
import ecommerce.Cloudmall.repository.OrderRepository;
import ecommerce.Cloudmall.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository; // ✅ Added

    public Order placeOrder(String userId) {
        Cart cart = cartRepository.findByUserId(userId).orElseThrow();

        double totalPrice = 0.0;
        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem cartItem : cart.getItems()) {
            Product product = productRepository.findById(cartItem.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found: " + cartItem.getProductId()));

            // check stock
            if (product.getStock() < cartItem.getQuantity()) {
                throw new RuntimeException("Not enough stock for product: " + product.getName());
            }

            // reduce stock
            product.setStock(product.getStock() - cartItem.getQuantity());
            productRepository.save(product);

            double itemPrice = product.getPrice() * cartItem.getQuantity();
            totalPrice += itemPrice;

            // save unit price for order
            orderItems.add(new OrderItem(product.getId(), cartItem.getQuantity(), product.getPrice()));
        }

        Order order = new Order();
        order.setUserId(userId);
        order.setItems(orderItems);
        order.setTotalPrice(totalPrice);
        order.setStatus("PLACED");
        order.setCreatedAt(LocalDateTime.now());

        // clear cart after placing order
        cart.getItems().clear();
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
