import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Bank {
    // Храним все счета в потокобезопасной мапе: accountId -> Account
    private final Map<String, Account> accounts = new ConcurrentHashMap<>();
    private final Map<String, User> users = new HashMap<>();
    /**
     * Добавляет новый счёт в банк
     */

    public void addAccount(Account account) {
        accounts.putIfAbsent(account.getAccountId(), account);
    }
    /**
     * Находит счёт по ID
     */
    public Account getAccount(String accountId) {
        return accounts.get(accountId);
    }

    /**
     * Переводит сумму с одного счёта на другой
     * @return true если перевод успешен, false если недостаточно средств
     */
    public synchronized boolean transfer(String fromAccountId, String toAccountId, double amount) {
        Account from = accounts.get(fromAccountId);
        Account to = accounts.get(toAccountId);

        if (from == null || to == null) {
            System.out.println("One of the accounts not found");
            return false;
        }

        if (from.withdraw(amount)) {
            to.deposit(amount);
            return true;
        }

        return false;
    }

    /**
     * Возвращает общий баланс всех счетов
     */
    public synchronized double getTotalBalance() {
        return accounts.values().stream()
                .mapToDouble(Account::getBalance)
                .sum();
    }

    /**
     * Возвращает количество счетов в системе
     */
    public int getAccountCount() {
        return accounts.size();
    }

    public void addUser(User user) {
        users.put(user.getUsername(), user);
    }

    public User authenticate(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public Account getAccountByClientId(String clientId) {
        return accounts.values().stream()
                .filter(acc -> acc.getClientId().equals(clientId))
                .findFirst()
                .orElse(null);
    }
}