package org.example.javafx_kzbo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import org.example.javafx_kzbo.HelloApplication;
import org.example.javafx_kzbo.repository.UserRepository;
import org.example.javafx_kzbo.validation.FieldsValidation;

import java.io.IOException;

@Setter
@Getter
public class AuthController {

    FieldsValidation fieldsValidation = new FieldsValidation();
    UserRepository userRepository = new UserRepository();

    @FXML
    private TextField id;

    @FXML
    private Button login;

    @FXML
    private PasswordField password;

    @FXML
    private Button signUp;

    @FXML
    private TextField username;


    @FXML
    void initialize() {
        login.setOnAction(this::login);

        signUp.setOnAction(AuthController::signUp);
    }

    private void login(ActionEvent actionEvent) {
        String userId = id.getText();
        String userPassword = password.getText();
        String userName = username.getText();

        if (!userRepository.login(userId, userName, userPassword)) {
            incorrectData();
            return;
        }

        login.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main-page.fxml"));

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Stage stage = new Stage();
        stage.setScene(new Scene(fxmlLoader.getRoot()));
        stage.show();
    }

    private static void signUp(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("sign-up.fxml"));

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Stage stage = new Stage();
        stage.setScene(new Scene(fxmlLoader.getRoot()));
        stage.show();
    }

    private void incorrectData() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Login Error");
        alert.setHeaderText("Login Failed");
        alert.setContentText("Invalid username, password or id. Please try again.");
        alert.showAndWait();
    }
}
