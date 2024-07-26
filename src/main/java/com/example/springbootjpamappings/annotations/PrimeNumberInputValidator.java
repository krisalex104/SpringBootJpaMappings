package com.example.springbootjpamappings.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;

import java.util.Objects;

public class PrimeNumberInputValidator implements ConstraintValidator<PrimeNumberInputValidation,Integer> {
    @Override
    public void initialize(PrimeNumberInputValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer input, ConstraintValidatorContext constraintValidatorContext) {
        if(!Objects.isNull(input)){
            isPrime(input);
        }
        return true;
    }
    private  boolean isPrime(int number) {
        if (number <= 1) {
            return false; // Numbers less than or equal to 1 are not prime
        }
        if (number == 2 || number == 3) {
            return true; // 2 and 3 are prime numbers
        }
        if (number % 2 == 0 || number % 3 == 0) {
            return false; // Eliminate multiples of 2 and 3
        }
        // Check from 5 to the square root of the number
        for (int i = 5; i * i <= number; i += 6) {
            if (number % i == 0 || number % (i + 2) == 0) {
                return false;
            }
        }
        return true; // If no divisors found, the number is prime
    }


}
