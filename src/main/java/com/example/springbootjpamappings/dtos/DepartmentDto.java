package com.example.springbootjpamappings.dtos;

import com.example.springbootjpamappings.annotations.PrimeNumberInputValidation;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {

    private Long id;

    @NotBlank(message = "title of department should not be blank")
    private String title;

    @JsonProperty("isActive")
    @AssertTrue
    private Boolean isActive;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent(message = "department created date should be either past or present data")
    private LocalDate createdAt;

    @NotNull(message = "department number shouldn't be null")
    @Range(min = 1, max = 9999999, message = "department number will be in range {1,9999999}")
    @PrimeNumberInputValidation(message = "department number should be a prime number")
    private Integer departmentNumber;

}
