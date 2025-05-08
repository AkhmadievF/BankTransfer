import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Bank bank = new Bank();

        Account acc1 = new Account("Иванов", "C1", 1_000_000.0);
        Account acc2 = new Account("Сидоров", "C2", 500_000.0);
        Account acc3 = new Account("Петров", "C3", 1_700_000.0);
        Account acc4 = new Account("Савельев", "C4", 15_000.0);
        Account acc5 = new Account("Абдуллин", "C5", 35_000.0);
        Account acc6 = new Account("Хафизов", "C6", 200_000);
        Account acc7 = new Account("Попов", "C7", 5_000_000);

        bank.addAccount(acc1);
        bank.addAccount(acc2);
        bank.addAccount(acc3);
        bank.addAccount(acc4);
        bank.addAccount(acc5);
        bank.addAccount(acc6);
        bank.addAccount(acc7);

        List<Account> allAccounts = Arrays.asList(acc1, acc2, acc3, acc4, acc5, acc6, acc7);

        int threadCount = 10;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);

        System.out.println("Starting transactions...");
        for (int i = 0; i < 100; i++) {
            executor.submit(new TransactionTask(bank, allAccounts));

        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        System.out.println("\nSimulation finished.");
        System.out.println("Final balance in the system: " + bank.getTotalBalance());
    }
}