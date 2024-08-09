package com.ideas2it.department.controller;

import com.ideas2it.department.dto.DepartmentDto;
import com.ideas2it.department.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping ("api/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    /**
     * This method add the Department to the Database
     * @param departmentDto - Department Dto as Object
     * @return DepartmentDto - DepartmentDto as Object
     */
    @PostMapping
    public DepartmentDto addDepartment(@RequestBody DepartmentDto departmentDto) {
	    return departmentService.addDepartment(departmentDto);
    }

    /** 
     * <p>
     * This method to delete a department 
     * in the Department Database
     * @param departmentId - ID of the Department
     * </p>
     */
    @DeleteMapping("{id}")
    public void deleteDepartment(@PathVariable("id") int departmentId) {
	    departmentService.deleteDepartment(departmentId);
    }

    /**
     * This method return the Department By ID
     * @param departmentId - ID of the department
     * @return DepartmentDto as Department Json Object
     */
    @GetMapping("{id}")
    public DepartmentDto getDepartmentById(@PathVariable("id") int departmentId) {
        return departmentService.getDepartmentById(departmentId);
    }

    /**
     * <p>
     * This method to Update an Department Name with id
     * </p>
     */
    @PutMapping("{id}")
    public DepartmentDto updateDepartment(@PathVariable("id") int departmentId, @RequestBody DepartmentDto departmentDto) {
        return departmentService.updateDepartmentRecord(departmentId, departmentDto);
    }
}