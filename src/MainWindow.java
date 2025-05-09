import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class MainWindow extends Application {
    private final Bank bank;
    private final User user;

    public MainWindow(Bank bank, User user) {
        this.bank = bank;
        this.user = user;
    }

    @Override
    public void start(Stage primaryStage) {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));

        Label welcomeLabel = new Label("Welcome, " + user.getUsername() + "!");

        Account account = bank.getAccountByClientId(user.getClientId());
        Label balanceLabel = new Label("Your balance: " + account.getBalance());

        Button transferButton = new Button("Input clientID:");
        transferButton.setOnAction(e -> {
            TransactionTask task = new TransactionTask(bank, List.of(account));
            new Thread(task).start();
            balanceLabel.setText("Your balance: " + account.getBalance());
        });

        layout.getChildren().addAll(welcomeLabel, balanceLabel, transferButton);

        Scene scene = new Scene(layout, 300, 150);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Main Menu");
        primaryStage.show();
    }
}