package ecommerce.Cloudmall.controller;

import ecommerce.Cloudmall.model.Product;
import ecommerce.Cloudmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // ✅ Add new product
    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product); // method exists in ProductService
    }

    // ✅ Get all products
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // ✅ Get products by seller
    @GetMapping("/seller/{sellerId}")
    public List<Product> getProductsBySeller(@PathVariable String sellerId) {
        return productService.getProductsBySeller(sellerId);
    }

    // ✅ Get product by ID
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable String id) {
        return productService.getProductById(id).orElse(null);
    }
}
