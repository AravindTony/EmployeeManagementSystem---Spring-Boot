package com.ideas2it.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ideas2it.model.Employee;

/** 
 * <p> 
 *This interface has Abstract Methods handles all the Database Operations like
 * Insertion, Display the Records, Update and Delete the Records
 * </p>
 * @author Aravind
 */
public interface EmployeeDao extends CrudRepository<Employee, Integer> {

    /**
     * This method return the Employee List if Employee is not Deleted
     * @return Employee - List of Employee
     */
    List<Employee> findByIsDeletedFalse();

    /**
     * This method Return Specific Employee with EmployeeId
     * if Employee is Not Deleted
     * @param employeeId - Employee ID to Identify
     * @return - Employee as Object
     */
    Employee findByIsDeletedFalseAndEmployeeId(int employeeId);
}