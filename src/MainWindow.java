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
    private Label balanceValueLabel;
    private final Account from;
    private final Account to;
    double value;
    String getClientID = "";

    public MainWindow(Bank bank, User user) {
        this.bank = bank;
        this.user = user;
        this.from = bank.getAccountByClientId(user.getClientId());
        this.to = bank.getAccountByClientId(user.getClientId());
    }

    @Override
    public void start(Stage primaryStage) {

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));

        Label welcomeLabel = new Label("Добро пожаловать, " + user.getUsername() + "!");

//        Account account = bank.getAccountByClientId(user.getClientId());
        Label balanceLabel = new Label("На вашем счету: " + from.getBalance());
        Button transferButton = new Button("перевести на счет:");

        TextField doubleField = new TextField();
        doubleField.setPromptText("Введите сумму");
        TextField toClientIdField = new TextField();
        toClientIdField.setPromptText("Введите номер счета:");

        Label resultLabel = new Label();

        Button submitButton = new Button("Подтвердить");
        submitButton.setOnAction(event -> {
            String input = doubleField.getText().trim();
            getClientID = toClientIdField.getText().trim();
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

            TransactionTask task = new TransactionTask(bank, from, bank.getAccountByClientId(getClientID), value);
            new Thread(task).start();
            balanceLabel.setText("На вашем счету: " + from.getBalance());
        });
        Button refreshButton = new Button("🔄");
        balanceValueLabel = new Label(String.format("%.2f", from.getBalance()));
        refreshButton.setOnAction(e -> start(primaryStage));


        layout.getChildren().addAll(welcomeLabel, balanceLabel,
                transferButton, refreshButton, doubleField, toClientIdField,
                submitButton, resultLabel);

        Scene scene = new Scene(layout, 300, 450);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Главное меню");
        primaryStage.show();
    }
}