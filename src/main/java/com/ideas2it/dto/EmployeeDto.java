package com.ideas2it.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * This Dto class represents the employee details like
 * Employee ID, Name, Department Information etc.,
 */
@Getter
@Setter
public class EmployeeDto {
    private int employeeId;
    private String employeeName;
    private LocalDate dateOfBirth;
    private String age;
    private int departmentId;
    private String departmentName;
    private long mobileNumber;
    private String qualification;
    private String employeeEmail;
    private int experience;
    private long accountNumber;
    private String bankName;
    private String mentorName;
}
