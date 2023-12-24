package org.example.javafx_kzbo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import lombok.Getter;
import lombok.Setter;
import org.example.javafx_kzbo.model.User;
import org.example.javafx_kzbo.repository.UserRepository;

@Setter
@Getter
public class MainController {

    UserRepository userRepository = new UserRepository();

    @FXML
    private Button allUsers;

    @FXML
    private Label userInfoLabel;

    @FXML
    void initialize() {
        allUsers.setOnAction(actionEvent -> {
            User randomUser = userRepository.getUser();
            if (randomUser != null) {
                displayUserInfo(randomUser);
            } else {
                userInfoLabel.setText("No users found");
            }
        });
    }

    private void displayUserInfo(User user) {
        userInfoLabel.setText("User ID: " + user.getId() +
                "\nUsername: " + user.getUsername() +
                "\nPassword: " + user.getPassword());
    }

}
