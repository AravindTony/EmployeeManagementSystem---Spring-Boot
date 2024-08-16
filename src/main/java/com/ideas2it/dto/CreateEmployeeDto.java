package com.ideas2it.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This Dto class represents the employee details like
 * Employee ID, Name, Department Information etc.,
 */
@Getter
@Setter
public class CreateEmployeeDto {
    private int employeeId;

    @NotBlank (message = "Name Must not be blank")
    @Pattern(regexp = "[a-zA-Z\\s]+$")
    private String employeeName;

    @Past
    private LocalDate dateOfBirth;
    private String age;
    private String departmentName;

    @Size (min = 10, max = 10, message = "Mobile Number must contain 10 Integers")
    private long mobileNumber;

    @NotBlank
    private String qualification;

    @Email (message = "Enter Valid Email..")
    private String employeeEmail;
    private int experience;
    private long accountNumber;
    private String bankName;
}
