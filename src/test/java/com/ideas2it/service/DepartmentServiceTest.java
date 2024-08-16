package com.ideas2it.service;

import java.util.List;

import com.ideas2it.dto.DepartmentDto;
import com.ideas2it.model.Department;
import org.springframework.stereotype.Service;

@Service
public interface DepartmentServiceTest {

    /**
     * Retrieves all departments.
     *
     * @return List of DepartmentDto
     */
    List<DepartmentDto> getDepartments();

    DepartmentDto getDepartmentById(int departmentId);

    DepartmentDto addDepartment(DepartmentDto departmentDto);

    void deleteDepartment(int departmentId);

    DepartmentDto updateDepartmentRecord(DepartmentDto departmentDto);
}