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
import org.example.javafx_kzbo.exception.ValidationException;
import org.example.javafx_kzbo.model.User;
import org.example.javafx_kzbo.repository.UserRepository;
import org.example.javafx_kzbo.validation.FieldsValidation;

import java.io.IOException;

@Setter
@Getter
public class SingUpController {

    FieldsValidation fieldsValidation = new FieldsValidation();
    UserRepository userRepository = new UserRepository();

    @FXML
    private Button SingUp;
    @FXML
    private PasswordField password;
    @FXML
    private TextField username;

    @FXML
    void initialize() {
        SingUp.setOnAction(this::signUp);
    }


    private void signUp(ActionEvent actionEvent) {
        String userPassword;
        String userName;

        try {
            userPassword = fieldsValidation.passwordValidation(password.getText());
            userName = fieldsValidation.usernameValidation(username.getText());
        } catch (ValidationException e) {
            incorrectData();
            return;
        }

        int userId = userRepository.signUpUser(new User(userName, userPassword));
        successfulSignUp(userId);

        SingUp.getScene().getWindow().hide();
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

    private void successfulSignUp(int userId) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sign Up Successful");
        alert.setHeaderText("User created successfully");
        alert.setContentText("User ID: " + userId);
        alert.showAndWait();
    }

    private void incorrectData() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Validation Error");
        alert.setHeaderText("Invalid Password or Invalid Username");
        alert.showAndWait();
    }
}
