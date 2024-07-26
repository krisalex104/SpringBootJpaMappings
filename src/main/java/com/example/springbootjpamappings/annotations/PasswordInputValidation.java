package com.example.springbootjpamappings.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordInputValidator.class)
public @interface PasswordInputValidation {

    String message() default "password should contain one uppercase , one lowercase letter and one special character";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
