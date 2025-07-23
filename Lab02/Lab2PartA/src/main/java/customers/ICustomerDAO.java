package customers;

import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerDAO {
	void save(Customer customer) ;
}
