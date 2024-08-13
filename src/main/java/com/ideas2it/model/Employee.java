package com.ideas2it.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;
import com.ideas2it.util.DateCalculation;

/**
* <p>This class is used to get and set the 
* Employee credentials like Employee Id
* Name of the Employee, Date of birth
* Mobile Number, Email, Qualification
* Experience and List of Mentors for the Employee</p>
* @author Aravind
*/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {		

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private int employeeId;	

    @Column (name = "name", nullable = false)
    private String employeeName;

    @Column (name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column (name = "mobile")
    private long mobileNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    @JsonBackReference
    private Department department;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private SalaryAccount account;

    @Column (name = "email", unique = true)
    private String employeeEmail;

    @Column (name = "qualification")
    private String qualification;

    @Column (name = "experience")
    private int experience;

    @Column (name = "is_deleted")
    public boolean isDeleted = false;
   
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "association",
	joinColumns = @JoinColumn(name = "employee_id"),
        inverseJoinColumns = @JoinColumn(name = "mentor_id"))
    private Set<Mentor> mentors;

    public String getNames() {
        StringBuilder mentorList = new StringBuilder();
        for (Mentor mentor : mentors) {
	        mentorList.append(mentor.getMentorName()).append(",");
        }	
	    return mentorList.toString();
    }

    public String getAge() {
	    if (dateOfBirth != null) {
	        return DateCalculation.calculateDifferenceBetweenDates(dateOfBirth);
	    }
	    return "";
    }
}