package com.example.springbootjpamappings.annotations;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PrimeNumberInputValidator.class)
public @interface PrimeNumberInputValidation {

    String message() default "input should be a prime number";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
