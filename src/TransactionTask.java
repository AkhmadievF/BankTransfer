import java.util.List;
import java.util.Random;

//public class TransactionTask implements Runnable {
//    private final Bank bank;
//    private final List<Account> allAccounts;
//    private static final Random random = new Random();
//
//    public TransactionTask(Bank bank, List<Account> allAccounts) {
//        this.bank = bank;
//        this.allAccounts = allAccounts;
//    }
//
//    @Override
//    public void run() {
//        try {
//            // Выбираем два случайных счёта
//            Account fromAccount = getRandomAccount();
//            Account toAccount = getRandomAccount();
//
//            // Не переводим самому себе
//            while (toAccount == fromAccount && allAccounts.size() > 1) {
//                toAccount = getRandomAccount();
//            }
//
//            double maxTransfer = fromAccount.getBalance();
//            double amount;
//
//            if (maxTransfer > 0) {
//                amount = random.nextDouble(maxTransfer); // Случайная сумма до баланса
//            } else {
//                System.out.println("[" + Thread.currentThread().getName() + "] " +
//                        "No funds to transfer from account " + fromAccount.getAccountId());
//                return;
//            }
//
//            boolean success = bank.transfer(
//                    fromAccount.getAccountId(),
//                    toAccount.getAccountId(),
//                    amount
//            );
//
//            System.out.println("[" + Thread.currentThread().getName() + "] " +
//                    "Transfer " + String.format("%.2f", amount) +
//                    " from " + fromAccount.getAccountId() +
//                    " to " + toAccount.getAccountId() +
//                    " — " + (success ? "SUCCESS" : "FAILED (not enough funds)"));
//
//        } catch (Exception e) {
//            System.err.println("[" + Thread.currentThread().getName() + "] Error during transaction: " + e.getMessage());
//        }
//    }
//
//    public Account getRandomAccount() {
//        return allAccounts.get((int) (Math.random()*allAccounts.size()));
//    }
//}

public class TransactionTask implements Runnable {
    private final Bank bank;
    private final Account from;
    private final Account to;
    private final double amount;

    public TransactionTask(Bank bank, Account from, Account to, double amount) {
        this.bank = bank;
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    @Override
    public void run() {
        try {
            // Выбираем два случайных счёта
            Account fromAccount = from;
            Account toAccount = to;

            // Не переводим самому себе
//            while (toAccount == fromAccount && allAccounts.size() > 1) {
//                toAccount = getRandomAccount();
//            }

            double maxTransfer = fromAccount.getBalance();


//            if (maxTransfer > 0) {
//                amount = random.nextDouble(maxTransfer); // Случайная сумма до баланса
//            } else {
//                System.out.println("[" + Thread.currentThread().getName() + "] " +
//                        "No funds to transfer from account " + fromAccount.getAccountId());
//                return;
//            }

            boolean success = bank.transfer(
                    fromAccount.getAccountId(),
                    toAccount.getAccountId(),
                    amount
            );

            System.out.println("[" + Thread.currentThread().getName() + "] " +
                    "Transfer " + String.format("%.2f", amount) +
                    " from " + fromAccount.getAccountId() +
                    " to " + toAccount.getAccountId() +
                    " — " + (success ? "SUCCESS" : "FAILED (not enough funds)"));

        } catch (Exception e) {
            System.err.println("[" + Thread.currentThread().getName() + "] Error during transaction: " + e.getMessage());
        }
    }

//    private Account getRandomAccount() {
//        return allAccounts.get((int) (Math.random()*allAccounts.size()));
//    }
}