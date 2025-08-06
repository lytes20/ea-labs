package miu.edu.Lab12Part1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

@RestController
public class Routes {

    // In-memory user storage
    private static final Map<String, User> users = new HashMap<>();

    static {
        // Initialize users
        User bob = new User("Bob", "sales", Arrays.asList("/shop", "/orders"));
        User mary = new User("Mary", "finance", Arrays.asList("/shop", "/orders", "/payments"));

        users.put("Bob", bob);
        users.put("Mary", mary);
    }

    @GetMapping("/shop")
    ResponseEntity<String> shop(@RequestHeader(value = "User", required = false) String username) {
        if (username == null || !users.containsKey(username)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Unauthorized: Please provide a valid User header");
        }

        User user = users.get(username);
        if (!user.hasAccess("/shop")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Access denied: " + username + " does not have permission to access /shop");
        }

        return ResponseEntity.ok("Thank you for shopping with us, " + username + "!");
    }

    @GetMapping("/orders")
    ResponseEntity<String> orders(@RequestHeader(value = "User", required = false) String username) {
        if (username == null || !users.containsKey(username)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Unauthorized: Please provide a valid User header");
        }

        User user = users.get(username);
        if (!user.hasAccess("/orders")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Access denied: " + username + " does not have permission to access /orders");
        }

        return ResponseEntity.ok("These are your orders, " + username + "!");
    }

    @GetMapping("/payments")
    ResponseEntity<String> payments(@RequestHeader(value = "User", required = false) String username) {
        if (username == null || !users.containsKey(username)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Unauthorized: Please provide a valid User header");
        }

        User user = users.get(username);
        if (!user.hasAccess("/payments")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Access denied: " + username + " does not have permission to access /payments");
        }

        return ResponseEntity.ok("Thank you for making this payment, " + username + "!");
    }

    // Helper class for user management
    private static class User {
        private final String name;
        private final String department;
        private final Set<String> allowedEndpoints;

        public User(String name, String department, List<String> allowedEndpoints) {
            this.name = name;
            this.department = department;
            this.allowedEndpoints = new HashSet<>(allowedEndpoints);
        }

        public boolean hasAccess(String endpoint) {
            return allowedEndpoints.contains(endpoint);
        }

        public String getName() {
            return name;
        }

        public String getDepartment() {
            return department;
        }

        public Set<String> getAllowedEndpoints() {
            return new HashSet<>(allowedEndpoints);
        }
    }
}
