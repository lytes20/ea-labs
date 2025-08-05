
import bank.domain.Account;
import bank.domain.Customer;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.*;

public class AccountTest {
    @Test
    public void testIncrement() {
        Account account = new Account();
        account.deposit(100.0);
        assertThat(account.getBalance(), closeTo(100.0, 0.01));
    }

    @Test
    public void testWithdraw() {
        Account account = new Account();
        account.deposit(200.0);
        account.withdraw(50.0);
        assertThat(account.getBalance(), closeTo(150.0, 0.01));
    }

    @Test
    public void testTransferFunds() {
        Customer customer1 = new Customer("Gideon Bamuleseyo");
        Customer customer2 = new Customer("Moges Y");

        Account fromAccount = new Account(12345);
        fromAccount.setCustomer(customer1);
        fromAccount.deposit(500.0);

        Account toAccount = new Account(54321);
        toAccount.setCustomer(customer2);
        toAccount.deposit(100.0);

        fromAccount.transferFunds(toAccount, 150.0, "I love helping the poor");

        assertThat(fromAccount.getBalance(), closeTo(350.0, 0.01));
        assertThat(toAccount.getBalance(), closeTo(250.0, 0.01));
    }
}