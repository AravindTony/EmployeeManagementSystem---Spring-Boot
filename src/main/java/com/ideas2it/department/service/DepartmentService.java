package com.ideas2it.department.service;

import com.ideas2it.department.dto.DepartmentDto;
import com.ideas2it.model.Department;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* <p>
* This interface has Abstract methods to implements the 
* methods like Department Operations 
* </p>
* @author Aravind
*/
@Service
public interface DepartmentService {

    /**
     * <p>
     * This method Return the Employee Departments to the Controller
     * </p>
     * @return Departments as the Map objects
     *
     */
    List<DepartmentDto> getDepartments();

    /** 
     * <p>
     * This method passes department object as argument to  
     * department Dao
     * </p>
     * @param departmentDto - Department Dto Object
     *
     */
    DepartmentDto addDepartment(DepartmentDto departmentDto);

    /**
     * <p>
     * This method to delete a department by the department
     * id given by the user
     * </p>
     * @param id - Department ID given by the user
     *
     */
    void deleteDepartment(int id);

    /**  
     * <p>
     * This method return the Department Object 
     * with the Department Id
     * </p>
     * @param departmentId - ID of the Department to get Department Object
     *
     * @return Department - Department as the Object
     * 
     */
    DepartmentDto getDepartmentById(int departmentId);

    /**
     * <p>
     * This method update Department name with id
     * </p>
     * @param departmentDto - Department as the Object
     * 
     */
    DepartmentDto updateDepartmentRecord(int id, DepartmentDto departmentDto);
}