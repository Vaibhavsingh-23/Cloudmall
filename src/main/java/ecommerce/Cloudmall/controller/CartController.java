package ecommerce.Cloudmall.controller;

import ecommerce.Cloudmall.model.Cart;
import ecommerce.Cloudmall.model.CartItem;
import ecommerce.Cloudmall.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    // ✅ Add item to cart
    @PostMapping("/{userId}/add")
    public Cart addToCart(@PathVariable String userId, @RequestBody CartItem item) {
        return cartService.addItemToCart(userId, item); // correct method in CartService
    }

    // ✅ View cart
    @GetMapping("/{userId}")
    public Cart getCart(@PathVariable String userId) {
        return cartService.getCartByUserId(userId);
    }

    // ✅ Remove item from cart
    @DeleteMapping("/{userId}/remove/{productId}")
    public Cart removeFromCart(@PathVariable String userId, @PathVariable String productId) {
        cartService.removeItemFromCart(userId, productId);
        return cartService.getCartByUserId(userId);
    }

    // ✅ Clear cart
    @DeleteMapping("/{userId}/clear")
    public Cart clearCart(@PathVariable String userId) {
        cartService.clearCart(userId);
        return cartService.getCartByUserId(userId);
    }
}
