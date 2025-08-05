package accounts.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import accounts.domain.Account;
import accounts.repositories.AccountRepository;

@DataJpaTest
@ActiveProfiles("test")
public class AccountRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AccountRepository accountRepository;

    private Account testAccount;

    @BeforeEach
    void setUp() {
        testAccount = new Account("12345", 1000.0, "Gideon Bamuleseyo");

        entityManager.persistAndFlush(testAccount);
    }

    @Test
    void testFindByAccountHolder_WhenAccountHolderExists_ShouldReturnAccount() {

        String accountHolder = "Gideon Bamuleseyo";

        Account foundAccount = accountRepository.findByAccountHolder(accountHolder);

        assertNotNull(foundAccount);
        assertEquals("12345", foundAccount.getAccountNumber());
        assertEquals("Gideon Bamuleseyo", foundAccount.getAccountHolder());
        assertEquals(1000.0, foundAccount.getBalance(), 0.01);
    }

    @Test
    void testFindByAccountHolder_WhenAccountHolderDoesNotExist_ShouldReturnNull() {

        String nonExistentAccountHolder = "Non Existent Person";

        Account foundAccount = accountRepository.findByAccountHolder(nonExistentAccountHolder);

        assertNull(foundAccount);
    }

}