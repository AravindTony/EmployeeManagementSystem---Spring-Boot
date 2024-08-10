package com.ideas2it.department.controller;

import com.ideas2it.department.dto.DepartmentDto;
import com.ideas2it.department.service.DepartmentService;
import com.ideas2it.model.Department;
import com.ideas2it.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
@RequestMapping ("department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    /**
     * This method add the Department to the Database
     * @param departmentDto - Department Dto as Object
     * @return DepartmentDto - DepartmentDto as Object
     */
    @PostMapping ("/add")
    public DepartmentDto addDepartment(@RequestBody DepartmentDto departmentDto) {
	    return convertToDto(departmentService.addDepartment(convertToEntity(departmentDto)));
    }

    @GetMapping ("/get")
    public List<DepartmentDto> getDepartments() {
        List<DepartmentDto> result = new ArrayList<>();
        List<Department> Departments = departmentService.getDepartments();
        for (Department department : Departments) {
            if (department.getIsDeleted()){
                result.add(convertToDto(department));
            }
        }
        return result;
    }
    /** 
     * <p>
     * This method to delete a department 
     * in the Department Database
     * @param departmentId - ID of the Department
     * </p>
     */
    @DeleteMapping("/delete/{id}")
    public void deleteDepartment(@PathVariable("id") int departmentId) {
	    departmentService.deleteDepartment(departmentId);
    }

    /**
     * This method return the Department By ID
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

    @GetMapping("/employees/{departmentId}")
    public ResponseEntity<List<Employee>> getEmployeesByDepartmentId(@PathVariable("departmentId") int departmentId) {
        List<Employee> employees = departmentService.getEmployeesByDepartmentId(departmentId);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
}