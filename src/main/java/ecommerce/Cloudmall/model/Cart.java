package ecommerce.Cloudmall.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
@Document(collection = "carts")
public class Cart {
    @Id
    private String id;
    private String userId; // linked to a CUSTOMER
    private List<CartItem> items = new ArrayList<>(); // initialize here

    public Cart() {
        this.items = new ArrayList<>();
    }

    public Cart(String id, String userId, List<CartItem> items) {
        this.id = id;
        this.userId = userId;
        this.items = items;
    }

    // Getters & Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public List<CartItem> getItems() { return items; }
    public void setItems(List<CartItem> items) { this.items = items; }
}
