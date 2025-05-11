import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoginWindow extends Application {
    private final Bank bank;

    public LoginWindow(Bank bank) {
        this.bank = bank;
    }
    @Override
    public void start(Stage primaryStage) {


        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label userLabel = new Label("Имя пользователя:");
        TextField userField = new TextField();

        Label passLabel = new Label("Пароль:");
        PasswordField passField = new PasswordField();

        Button loginButton = new Button("Войти");

        Label messageLabel = new Label();

        loginButton.setOnAction(e -> {
            String username = userField.getText();
            String password = passField.getText();

            User user = bank.authenticate(username, password);
            if (user != null) {
                messageLabel.setText("Login successful!");
                primaryStage.close();
                MainWindow mainWindow = new MainWindow(bank, user);
                mainWindow.start(new Stage());
            } else {
                messageLabel.setText("Invalid credentials!");
            }
        });
        Button btn = new Button("Открыть новое окно");
        btn.setOnAction(e -> start(new Stage()));;

        grid.add(userLabel, 0, 0);
        grid.add(userField, 1, 0);
        grid.add(passLabel, 0, 1);
        grid.add(passField, 1, 1);
        grid.add(loginButton, 1, 2);
        grid.add(messageLabel, 1, 3);
        grid.add(btn, 0, 3);

        Scene scene = new Scene(grid, 350, 150);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Авторизация");
        primaryStage.show();
    }


}