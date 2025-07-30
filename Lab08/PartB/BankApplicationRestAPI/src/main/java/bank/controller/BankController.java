package bank.controller;

import bank.service.AccountDTO;
import bank.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/bank")
@CrossOrigin(origins = "*")
public class BankController {

    @Autowired
    private IAccountService accountService;

    // Create a new account
    @PostMapping("/accounts")
    public ResponseEntity<AccountDTO> createAccount(@RequestBody CreateAccountRequest request) {
        try {
            AccountDTO account = accountService.createAccount(request.getAccountNumber(), request.getCustomerName());
            return ResponseEntity.status(HttpStatus.CREATED).body(account);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Get all accounts
    @GetMapping("/accounts")
    public ResponseEntity<Collection<AccountDTO>> getAllAccounts() {
        try {
            Collection<AccountDTO> accounts = accountService.getAllAccounts();
            return ResponseEntity.ok(accounts);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // Get account by account number
    @GetMapping("/accounts/{accountNumber}")
    public ResponseEntity<AccountDTO> getAccount(@PathVariable long accountNumber) {
        try {
            AccountDTO account = accountService.getAccount(accountNumber);
            if (account != null) {
                return ResponseEntity.ok(account);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // Deposit money
    @PostMapping("/accounts/{accountNumber}/deposit")
    public ResponseEntity<String> deposit(@PathVariable long accountNumber, @RequestBody DepositRequest request) {
        try {
            accountService.deposit(accountNumber, request.getAmount());
            return ResponseEntity.ok("Deposit successful");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Deposit failed: " + e.getMessage());
        }
    }

    // Withdraw money
    @PostMapping("/accounts/{accountNumber}/withdraw")
    public ResponseEntity<String> withdraw(@PathVariable long accountNumber, @RequestBody WithdrawRequest request) {
        try {
            accountService.withdraw(accountNumber, request.getAmount());
            return ResponseEntity.ok("Withdrawal successful");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Withdrawal failed: " + e.getMessage());
        }
    }

    // Deposit euros
    @PostMapping("/accounts/{accountNumber}/deposit-euros")
    public ResponseEntity<String> depositEuros(@PathVariable long accountNumber, @RequestBody DepositRequest request) {
        try {
            accountService.depositEuros(accountNumber, request.getAmount());
            return ResponseEntity.ok("Euro deposit successful");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Euro deposit failed: " + e.getMessage());
        }
    }

    // Withdraw euros
    @PostMapping("/accounts/{accountNumber}/withdraw-euros")
    public ResponseEntity<String> withdrawEuros(@PathVariable long accountNumber,
            @RequestBody WithdrawRequest request) {
        try {
            accountService.withdrawEuros(accountNumber, request.getAmount());
            return ResponseEntity.ok("Euro withdrawal successful");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Euro withdrawal failed: " + e.getMessage());
        }
    }

    // Transfer funds
    @PostMapping("/transfer")
    public ResponseEntity<String> transferFunds(@RequestBody TransferRequest request) {
        try {
            accountService.transferFunds(
                    request.getFromAccountNumber(),
                    request.getToAccountNumber(),
                    request.getAmount(),
                    request.getDescription());
            return ResponseEntity.ok("Transfer successful");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Transfer failed: " + e.getMessage());
        }
    }

    // Request DTOs
    public static class CreateAccountRequest {
        private long accountNumber;
        private String customerName;

        public long getAccountNumber() {
            return accountNumber;
        }

        public void setAccountNumber(long accountNumber) {
            this.accountNumber = accountNumber;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }
    }

    public static class DepositRequest {
        private double amount;

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }
    }

    public static class WithdrawRequest {
        private double amount;

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }
    }

    public static class TransferRequest {
        private long fromAccountNumber;
        private long toAccountNumber;
        private double amount;
        private String description;

        public long getFromAccountNumber() {
            return fromAccountNumber;
        }

        public void setFromAccountNumber(long fromAccountNumber) {
            this.fromAccountNumber = fromAccountNumber;
        }

        public long getToAccountNumber() {
            return toAccountNumber;
        }

        public void setToAccountNumber(long toAccountNumber) {
            this.toAccountNumber = toAccountNumber;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}