package com.example.springbootjpamappings.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;

import java.util.List;

public class EmployeeRoleValidator implements ConstraintValidator<EmployeeRoleValidation,String> {

    private static final List<String> roles = List.of("USER","ADMIN");
    @Override
    public boolean isValid(String inputRole, ConstraintValidatorContext constraintValidatorContext) {
        if(!StringUtils.hasText(inputRole)) return false;
        return roles.contains(inputRole);
    }
}
