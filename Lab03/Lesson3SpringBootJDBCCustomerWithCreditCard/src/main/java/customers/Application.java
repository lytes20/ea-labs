package customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private CustomerDAO customerDao;

	@Autowired
	private ProductDAO productDAO;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		customerDao.clearDB();
		Customer customer = new Customer(101, "John doe", "johnd@acme.com", "0622341678");
		CreditCard creditCard = new CreditCard("12324564321", "Visa", "11/23");
		customer.setCreditCard(creditCard);
		customerDao.save(customer);
		customer = new Customer(66, "James Johnson", "jj123@acme.com", "068633452");
		creditCard = new CreditCard("99876549876", "MasterCard", "01/24");
		customer.setCreditCard(creditCard);
		customerDao.save(customer);
		System.out.println(customerDao.getCustomer(101));
		System.out.println(customerDao.getCustomer(66));
		System.out.println("-----------All customers ----------------");
		System.out.println(customerDao.getAllCustomers());

		productDAO.clearDB();
		Supplier supplier = new Supplier("Web Services", "localhost:8080");
		Product product = new Product(404, "Can not be found", 4004.00, supplier);
		Product product1 = new Product(200, "Success", 10000, supplier);
		Product product2 = new Product(201, "Create", 20000, supplier);
		productDAO.save(product);
		productDAO.save(product1);
		productDAO.save(product2);
		System.out.println(productDAO.findByProductNumber(201));
		System.out.println(productDAO.removeProduct(201));
		System.out.println(productDAO.findByProductName("Success"));
	}
}
