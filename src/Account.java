import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public class Account {
        private final String accountId;
        private final String clientId;
        private volatile double balance; // volatile для видимости значения между потоками


    public Account(String accountId, String clientId, double initialBalance) {
 /*       if (accountId == null || accountId.isBlank()) {
            throw new IllegalArgumentException("Account ID cannot be null or blank");
        }
        if (clientId == null || clientId.isBlank()) {
            throw new IllegalArgumentException("Client ID cannot be null or blank");
        }
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }
*/
        this.accountId = accountId;
        this.clientId = clientId;
        this.balance = initialBalance;
    }
    public String getAccountId() {
        return accountId;
    }

    public String getClientId() {
        return clientId;
    }

    public synchronized double getBalance() {
        return balance;
    }

    public synchronized boolean withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdraw amount must be positive");
        }
        if (balance < amount) {
            return false; // Недостаточно средств
        }
        balance -= amount;
        return true;
    }

    public synchronized void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        balance += amount;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId='" + accountId + '\'' +
                ", clientId='" + clientId + '\'' +
                ", balance=" + balance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return accountId.equals(account.accountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId);
    }

}
