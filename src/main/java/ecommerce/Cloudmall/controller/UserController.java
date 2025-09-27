package ecommerce.Cloudmall.controller;

import ecommerce.Cloudmall.model.User;
import ecommerce.Cloudmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // ✅ Register new user
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.registerUser(user); // use correct service method
    }

    // ✅ Get all users
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // ✅ Get user by email
    @GetMapping("/{email}")
    public User getUserByEmail(@PathVariable String email) {
        return userService.findByEmail(email).orElse(null); // correct service method
    }
}
