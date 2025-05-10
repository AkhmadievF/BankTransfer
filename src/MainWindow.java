import com.sun.javafx.scene.control.DoubleField;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class MainWindow extends Application {
    private final Bank bank;
    private final User user;
    private Label balanceValueLabel;
    private Account account;
    double value;
    public MainWindow(Bank bank, User user) {
        this.bank = bank;
        this.user = user;
        this.account = bank.getAccountByClientId(user.getClientId());
    }
    Account acc2 = new Account("max", "2", 0);
    @Override
    public void start(Stage primaryStage) {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));

        Label welcomeLabel = new Label("Welcome, " + user.getUsername() + "!");

        Account account = bank.getAccountByClientId(user.getClientId());
        Label balanceLabel = new Label("Your balance: " + account.getBalance());
        Button transferButton = new Button("transfer to:");

        TextField doubleField = new TextField();
        doubleField.setPromptText("Введите сумму");

        Label resultLabel = new Label("Результат будет здесь");

        Button submitButton = new Button("Подтвердить");
        submitButton.setOnAction(event -> {
            String input = doubleField.getText().trim();

            try {
                value = Double.parseDouble(input);

                if (value <= 0) {
                    resultLabel.setText("Ошибка: введите положительное число");
                    resultLabel.setStyle("-fx-text-fill: red;");
                } else {
                    resultLabel.setText("Вы ввели: " + value);
                    resultLabel.setStyle("-fx-text-fill: green;");
                }

            } catch (NumberFormatException e) {
                resultLabel.setText("Ошибка: это не число");
                resultLabel.setStyle("-fx-text-fill: red;");
            }
        });
        transferButton.setOnAction(e -> {

            TransactionTask task = new TransactionTask(bank, account, acc2, value);
            new Thread(task).start();
            balanceLabel.setText("Your balance: " + account.getBalance());
        });
        Button refreshButton = new Button("🔄 Refresh");
        balanceValueLabel = new Label(String.format("%.2f", account.getBalance()));
        refreshButton.setOnAction(e -> refreshBalance());
        layout.getChildren().addAll(welcomeLabel, balanceLabel,
                transferButton,refreshButton, doubleField,
                submitButton, resultLabel
        );

        Scene scene = new Scene(layout, 300, 150);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Main Menu");
        primaryStage.show();
    }

    private void refreshBalance() {
        double currentBalance = account.getBalance();
        balanceValueLabel.setText(String.format("%.2f", currentBalance));
    }
}