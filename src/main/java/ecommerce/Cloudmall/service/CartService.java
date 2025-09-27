package ecommerce.Cloudmall.service;

import ecommerce.Cloudmall.model.Cart;
import ecommerce.Cloudmall.model.CartItem;
import ecommerce.Cloudmall.repository.CartRepository;
import ecommerce.Cloudmall.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    public Cart getCartByUserId(String userId) {
        return cartRepository.findByUserId(userId).orElse(new Cart());
    }

    public Cart addItemToCart(String userId, CartItem item) {
        Cart cart = cartRepository.findByUserId(userId).orElse(new Cart());
        cart.setUserId(userId);

        // Check if product already in cart
        Optional<CartItem> existingItem = cart.getItems()
                .stream()
                .filter(i -> i.getProductId().equals(item.getProductId()))
                .findFirst();

        if (existingItem.isPresent()) {
            existingItem.get().setQuantity(existingItem.get().getQuantity() + item.getQuantity());
        } else {
            cart.getItems().add(item);
        }

        return cartRepository.save(cart);
    }

    public void removeItemFromCart(String userId, String productId) {
        Cart cart = cartRepository.findByUserId(userId).orElseThrow();
        cart.getItems().removeIf(item -> item.getProductId().equals(productId));
        cartRepository.save(cart);
    }

    public void clearCart(String userId) {
        Cart cart = cartRepository.findByUserId(userId).orElseThrow();
        cart.getItems().clear();
        cartRepository.save(cart);
    }
}
