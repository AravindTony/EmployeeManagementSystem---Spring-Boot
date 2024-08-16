package com.ideas2it.service;

import java.util.List;

import com.ideas2it.dto.CreateEmployeeDto;
import com.ideas2it.dto.EmployeeDto;
import com.ideas2it.model.Employee;
import com.ideas2it.model.Mentor;

/**
 * <p>
 * This interface has Abstract methods for Employee Service
 * </p>
 */
public interface EmployeeService {
 
    /** 
     * <p>
     * This method return the Employee Records from the Database to 
     * the Controller
     * </p>
     *
     * @return Employee - List of Employees
     */
    List<EmployeeDto> getEmployeeDetails();

    /** 
     * <p>
     * This method send the Data to the insertData method 
     * to insert the data to Dao
     * </p>
     * @return Employee as the Object
     *
     */
    CreateEmployeeDto addData(EmployeeDto employeeDto);

    /**
     * <p>
     * This method update the records in the Employee List based
     * on the User Choice
     * </p>
     */
    EmployeeDto updateEmployee(EmployeeDto employeeDto);

    /**
     * <p>
     * This method to delete an Employee Record
     * by using the Employee Id
     * </p>
     * @param deleteId is used to match the id from the List 
     *
     */
    void deleteEmployee(int deleteId);

    /** 
     * <p>
     * This method return the Employee Object by the id
     * </p>
     * @return Employee Record
     *
     */
    EmployeeDto getEmployeeById(int id);

    /**
     * <p>
     * This method assign the Employee to Mentor
     * </p>
     * @param employeeId - Employee ID to Identify Employee
     * @param mentorId - Mentor ID to Identify Mentor
     * @return EmployeeDto {@link EmployeeDto}- Employee as Dto Object
     */
    EmployeeDto assignEmployee(int employeeId, int mentorId);
}