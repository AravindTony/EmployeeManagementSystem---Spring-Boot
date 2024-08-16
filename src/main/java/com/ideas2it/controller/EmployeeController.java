package com.ideas2it.controller;

import java.util.List;

import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ideas2it.dto.CreateEmployeeDto;
import com.ideas2it.service.DepartmentService;
import com.ideas2it.dto.EmployeeDto;
import com.ideas2it.service.EmployeeService;
import com.ideas2it.service.MentorService;



/**
 * <p>
 * This class is the Controller for Employee get all the Information's
 * about the Employee and also various methods like
 * Create Employee Record 
 * Display the Employee record(s)
 * Update the Employee Record
 * Delete the Employee Record in the Repository
 *</p>
 * @author Aravind
 */
@RestController
@RequestMapping ("api/v1/employees")
public class EmployeeController {
    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private MentorService mentorService;

    /**
     * This method get the Credentials from User and add it to
     * the Database
     * @param employeeDto - Employee as Dto Object
     * @return - EmployeeDto - Employee as Dto Object
     */
    @PostMapping
    public ResponseEntity<CreateEmployeeDto> createEmployeeRecord(@Valid @RequestBody EmployeeDto employeeDto) {
        CreateEmployeeDto savedEmployee = employeeService.addData(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    /** 
     * <p>
     * This method to Display 
     * all the Records from the List
     * Retrieving from the Database
     * </p>
     * @return EmployeeDto {@link EmployeeDto}- List of Employee
     */
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> displayRecords() {
        return new ResponseEntity<>(employeeService.getEmployeeDetails(), HttpStatus.OK);
    }

    /**
     * <p>
     * This Method is used to display the 
     * Specific Employee Record in the list
     * retrieving from the Database
     * </p>
     * @param employeeId - Employee ID
     * @return EmployeeDto {@link EmployeeDto}- Employee as Dto Object
     */
    @GetMapping ("/{id}")
    public ResponseEntity<EmployeeDto> displayEmployeeRecord(@PathVariable ("id") int employeeId) {
        EmployeeDto employeeDto = employeeService.getEmployeeById(employeeId);
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }

    /**
     * <p>
     * This Method to Update an Employee Record
     * in the List with the Employee Id
     * </p>
     * @param employeeDto {@link EmployeeDto} - Employee Dto as Object
     * @return EmployeeDto - Employee as Dto Object with Response Entity
     */
    @PutMapping
    public ResponseEntity<EmployeeDto> updateEmployeeRecord(@RequestBody EmployeeDto employeeDto) {
        return new ResponseEntity<>(employeeService.updateEmployee(employeeDto), HttpStatus.OK);
    }

    /**
     * <p>
     * This method to Delete an Employee Record in the Database
     * with the Employee Id 
     * </p>
     * @param employeeId - To Identify Employee
     */
    @DeleteMapping ("{id}")
    public ResponseEntity<Void> deleteEmployeeRecord(@PathVariable("id") int employeeId) {
        employeeService.deleteEmployee(employeeId);
        logger.info("Employee Deleted Successfully..{}", employeeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * <p>
     * This method assign the Employee to Mentor
     * </p>
     * @param employeeId - Employee ID to Identify Employee
     * @param mentorId - Mentor ID to Identify Mentor
     * @return EmployeeDto {@link EmployeeDto} - Employee as Dto Object
     */
    @PutMapping ("{employeeId}/mentor/{mentorId}")
    public ResponseEntity<EmployeeDto> assignEmployeeToMentor(@PathVariable int employeeId, @PathVariable int mentorId) {
        EmployeeDto employeeDto = employeeService.assignEmployee(employeeId, mentorId);
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }
}
