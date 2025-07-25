package edu.miu.Lab4PartA;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

@SpringBootApplication
public class Main implements CommandLineRunner {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderLineRepository orderLineRepository;
    @Autowired
    ProductRepository productRepository;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Create Product
        Product product = new Product();
        product.setName("Water");
        product.setPrice(200.0);
        product = productRepository.save(product);

        // Create Address
        Address address = new Address();
        address.setStreet("1000 N 4th St");
        address.setCity("Fairfield");
        address.setZip("52557");
        address.setCountry("USA");

        // Create Customer
        Customer customer = new Customer();
        customer.setFirstname("Gideon");
        customer.setLastname("Bamuleseyo");
        customer.setAddress(address);

        // Create OrderLine
        OrderLine orderLine = new OrderLine();
        orderLine.setProduct(product);
        orderLine.setQuantity(2);

        // Create Order
        Order order = new Order();
        order.setDate(new Date());
        order.setStatus("NEW");
        order.setCustomer(customer);
        order.setOrderLines(Arrays.asList(orderLine));
        orderLine.setOrder(order);

        // Set orders for customer
        customer.setOrders(Arrays.asList(order));

        // Save the order (should cascade to customer, address, orderLine)
        orderRepository.save(order);

        // Retrieve the order
        Optional<Order> retrievedOrderOpt = orderRepository.findById(order.getOrdernumber());
        if (retrievedOrderOpt.isPresent()) {
            Order retrievedOrder = retrievedOrderOpt.get();
            System.out.println("Order: " + retrievedOrder.getOrdernumber() + ", Status: " + retrievedOrder.getStatus());
            System.out.println("Customer: " + retrievedOrder.getCustomer().getFirstname() + " "
                    + retrievedOrder.getCustomer().getLastname());
            System.out.println("Address: " + retrievedOrder.getCustomer().getAddress().getStreet());
            for (OrderLine ol : retrievedOrder.getOrderLines()) {
                System.out.println("OrderLine: " + ol.getId() + ", Product: " + ol.getProduct().getName()
                        + ", Quantity: " + ol.getQuantity());
            }
        } else {
            System.out.println("Order not found!");
        }
    }
}
