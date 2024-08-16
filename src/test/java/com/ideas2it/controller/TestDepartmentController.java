package com.ideas2it.controller;

import com.ideas2it.dto.DepartmentDto;
import com.ideas2it.service.DepartmentService;
import com.ideas2it.service.DepartmentServiceTest;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/departments")
public class TestDepartmentController {
    @Autowired
    private DepartmentServiceTest departmentService;

    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getDepartments() {
        return new ResponseEntity<>(departmentService.getDepartments(), HttpStatus.OK);
    }

    /**
     * This method add the Department to the Database
     * @param departmentDto {@link DepartmentDto}- Department Dto as Object
     * @return DepartmentDto {@link DepartmentDto}- DepartmentDto as Object
     */
    @PostMapping
    public ResponseEntity<DepartmentDto> addDepartment(@Valid @RequestBody DepartmentDto departmentDto) {
        DepartmentDto createdDepartmentDto =  departmentService.addDepartment(departmentDto);
        return new ResponseEntity<>(createdDepartmentDto, HttpStatus.OK);
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
        return new ResponseEntity<>(updatedDepartmentDto, HttpStatus.OK);
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
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
