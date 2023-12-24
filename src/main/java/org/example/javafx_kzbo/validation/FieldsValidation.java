package org.example.javafx_kzbo.validation;

import org.example.javafx_kzbo.exception.ValidationException;

public class FieldsValidation {

    public String usernameValidation(String username) throws ValidationException {
        if (username.matches("^[a-zA-Z]+$")) {
            return username;
        } else
            throw new ValidationException("The username must contain only letters");
    }

    public String passwordValidation(String password) throws ValidationException {
        if (password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")) {
            return password;
        } else
            throw new ValidationException("The password must contain a letter, a number, and must be at least 8 characters long");
    }
}
