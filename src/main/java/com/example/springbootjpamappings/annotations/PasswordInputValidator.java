package com.example.springbootjpamappings.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;

public class PasswordInputValidator implements ConstraintValidator<PasswordInputValidation,String> {
    @Override
    public void initialize(PasswordInputValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String input, ConstraintValidatorContext constraintValidatorContext) {
        if(StringUtils.hasText(input)){
            return isValidPassword(input);
        }
        return false;
    }

    public static boolean isValidPassword(String password) {
        if (password == null) {
            return false;
        }

        // Regular expressions for each condition
        String upperCasePattern = ".*[A-Z].*";
        String lowerCasePattern = ".*[a-z].*";
        String specialCharPattern = ".*[!@#$%^&*(),.?\":{}|<>].*";

        // Check if password contains at least one uppercase letter
        if (!password.matches(upperCasePattern)) {
            return false;
        }

        // Check if password contains at least one lowercase letter
        if (!password.matches(lowerCasePattern)) {
            return false;
        }

        // Check if password contains at least one special character
        if (!password.matches(specialCharPattern)) {
            return false;
        }

        return true;
    }
}
