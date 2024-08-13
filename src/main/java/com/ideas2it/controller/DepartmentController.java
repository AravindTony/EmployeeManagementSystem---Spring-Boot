package com.ideas2it.controller;

import java.util.List;

import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ideas2it.dto.DepartmentDto;
import com.ideas2it.service.DepartmentService;
import com.ideas2it.dto.EmployeeDto;

/**
 *<p>
 *This class is the Controller of Department for
 * Add departments to the Repository 
 * Display list of the departments in the Repository
 * Delete Departments in the Repository
 *</p>
 * @author Aravind
 */
@RestController
@RequestMapping ("api/v1/departments")
public class DepartmentController {
    private static final Logger logger = LogManager.getLogger();
    @Autowired
    private DepartmentService departmentService;

    /**
     * This method add the Department to the Database
     * @param departmentDto {@link DepartmentDto}- Department Dto as Object
     * @return DepartmentDto {@link DepartmentDto}- DepartmentDto as Object
     */
    @PostMapping
    public ResponseEntity<DepartmentDto> addDepartment(@Valid @RequestBody DepartmentDto departmentDto) {
	    DepartmentDto createdDepartmentDto =  departmentService.addDepartment(departmentDto);
        logger.info("Department Created Successfully with Name : {}", createdDepartmentDto.getName());
        return new ResponseEntity<>(createdDepartmentDto, HttpStatus.OK);
    }

    /**
     * This method return all the Departments in the Database
     * @return DepartmentDto {@link DepartmentDto}- Department as Dto Object
     */
    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getDepartments() {
        return new ResponseEntity<>(departmentService.getDepartments(), HttpStatus.OK);
    }
    /** 
     * <p>
     * This method to delete a department 
     * in the Department Database
     * </p>
     * @param departmentId - ID of the Department
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable("id") int departmentId) {
	    departmentService.deleteDepartment(departmentId);
        logger.info("Department deleted Successfully..");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * <p>
     * This method return the Department By ID
     * </p>
     * @param departmentId - ID of the department
     * @return DepartmentDto {@link DepartmentDto} - Department Dto Object
     */
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable("id") int departmentId) {
        DepartmentDto retrievedDepartment = departmentService.getDepartmentById(departmentId);
        if (null == retrievedDepartment) {
            logger.warn("Department not found..");
        } else {
            logger.info("Department Obtained : {}", retrievedDepartment.getName());
        }
        return new ResponseEntity<>(retrievedDepartment, HttpStatus.OK);
    }

    /**
     * <p>
     * This method to Update an Department Name with id
     * </p>
     * @param departmentDto {@link DepartmentDto} - Department ID given by User
     * @return DepartmentDto {@link DepartmentDto} - Department as Dto Object
     */
    @PatchMapping
    public ResponseEntity<DepartmentDto> updateDepartment(@RequestBody DepartmentDto departmentDto) {
        DepartmentDto updatedDepartmentDto = departmentService.updateDepartmentRecord(departmentDto);
        if (null == updatedDepartmentDto) {
            logger.warn("Department not found with ID : {}", updatedDepartmentDto.getId());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            logger.info("Department Updated Successfully..");
        }
        return new ResponseEntity<>(updatedDepartmentDto, HttpStatus.OK);
    }

    /**
     * <p>
     * This method return the Employees by Department ID
     * </p>
     * @param departmentId - Department ID given by the User
     * @return - EmployeeDto {@link EmployeeDto} - List of Employee
     */
    @GetMapping("/employees/{departmentId}")
    public ResponseEntity<List<EmployeeDto>> getEmployeesByDepartmentId(@PathVariable("departmentId") int departmentId) {
        List<EmployeeDto> employees = departmentService.getEmployeesByDepartmentId(departmentId);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
}