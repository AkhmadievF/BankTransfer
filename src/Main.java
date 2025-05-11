import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args){
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        Bank bank = new Bank();

        Account acc1 = new Account("bob", "1", 1_000_000.0);
        Account acc2 = new Account("max", "2", 0);
        Account acc3 = new Account("alex", "3", 1_700_000.0);
        Account acc4 = new Account("john", "4", 15_000.0);
        Account acc5 = new Account("alice", "5", 35_000.0);
        Account acc6 = new Account("rob", "6", 200_000);

        bank.addAccount(acc1);
        bank.addAccount(acc2);
        bank.addAccount(acc3);
        bank.addAccount(acc4);
        bank.addAccount(acc5);
        bank.addAccount(acc6);

        User user1 = new User("1", "bob", "1234");
        User user2 = new User("2", "max", "1234");
        User user3 = new User("3", "alex", "1234");
        User user4 = new User("4", "john", "1234");
        User user5 = new User("5", "alice", "1234");
        User user6 = new User("6", "rob", "1234");
        bank.addUser(user1);
        bank.addUser(user2);
        bank.addUser(user3);
        bank.addUser(user4);
        bank.addUser(user5);
        bank.addUser(user6);

//        List<Account> allAccounts = Arrays.asList(acc1, acc2, acc3, acc4, acc5, acc6);

//        int threadCount = 10;
//        ExecutorService executor = Executors.newFixedThreadPool(threadCount);

//        System.out.println("Starting transactions...");
//        for (int i = 0; i < 100; i++) {
//            executor.submit(new TransactionTask(bank, allAccounts));
//
//        }

        LoginWindow loginWindow = new LoginWindow(bank);
        loginWindow.start(stage);

//        executor.shutdown();
//        executor.awaitTermination(1, TimeUnit.MINUTES);

        System.out.println("\nSimulation finished.");
        System.out.println("Final balance in the system: " + bank.getTotalBalance());
    }
}