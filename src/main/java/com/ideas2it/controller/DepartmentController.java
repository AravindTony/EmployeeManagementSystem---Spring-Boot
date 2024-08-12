package com.ideas2it.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ideas2it.dto.DepartmentDto;
import com.ideas2it.service.DepartmentService;
import com.ideas2it.model.Department;
import com.ideas2it.model.Employee;

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
@RequestMapping ("api/v1/department")
public class DepartmentController {
    private static final Logger logger = LogManager.getLogger();
    @Autowired
    private DepartmentService departmentService;

    /**
     * This method add the Department to the Database
     * @param departmentDto - Department Dto as Object
     * @return DepartmentDto - DepartmentDto as Object
     */
    @PostMapping ("/add")
    public DepartmentDto addDepartment(@RequestBody DepartmentDto departmentDto) {
        logger.info("Department Added Successfully..");
	    return convertToDto(departmentService.addDepartment(convertToEntity(departmentDto)));
    }

    @GetMapping ("/get")
    public List<DepartmentDto> getDepartments() {
        List<DepartmentDto> result = new ArrayList<>();
        List<Department> Departments = departmentService.getDepartments();
        for (Department department : Departments) {
            if (!department.isDeleted()){
                result.add(convertToDto(department));
            }
        }
        return result;
    }
    /** 
     * <p>
     * This method to delete a department 
     * in the Department Database
     * </p>
     * @param departmentId - ID of the Department
     */
    @DeleteMapping("/delete/{id}")
    public void deleteDepartment(@PathVariable("id") int departmentId) {
	    departmentService.deleteDepartment(departmentId);
        logger.info("Department deleted Successfully..");
    }

    /**
     * <p>
     * This method return the Department By ID
     * </p>
     * @param departmentId - ID of the department
     * @return DepartmentDto as Department Json Object
     */
    @GetMapping("/get/{id}")
    public DepartmentDto getDepartmentById(@PathVariable("id") int departmentId) {
        return convertToDto(departmentService.getDepartmentById(departmentId));
    }

    /**
     * <p>
     * This method to Update an Department Name with id
     * </p>
     * @param departmentId - Department ID given by User
     * @return DepartmentDto - Department as Dto Object
     */
    @PutMapping("/update/{id}")
    public DepartmentDto updateDepartment(@PathVariable("id") int departmentId, @RequestBody DepartmentDto departmentDto) {
        return convertToDto(departmentService.updateDepartmentRecord(departmentId, convertToEntity(departmentDto)));
    }

    public static DepartmentDto convertToDto(Department department) {
        return new DepartmentDto(department.getDepartmentId(), department.getDepartmentName());
    }

    public static Department convertToEntity(DepartmentDto departmentDto) {
        Department department = new Department();
        department.setDepartmentId(departmentDto.getId());
        department.setDepartmentName(departmentDto.getName());
        return department;
    }

    /**
     * <p>
     * This method return the Employees by Department ID
     * </p>
     * @param departmentId - Department ID given by the User
     * @return - Employee - List of Employee
     */
    @GetMapping("/employees/{departmentId}")
    public ResponseEntity<List<Employee>> getEmployeesByDepartmentId(@PathVariable("departmentId") int departmentId) {
        List<Employee> employees = departmentService.getEmployeesByDepartmentId(departmentId);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
}