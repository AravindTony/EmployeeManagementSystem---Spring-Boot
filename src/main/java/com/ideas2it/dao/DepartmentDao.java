package com.ideas2it.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ideas2it.model.Department;

/**
 * This interface Handles CRUD Operations for Department extends CRUD Repository
 * @author Aravind
 */
@Repository
public interface DepartmentDao extends JpaRepository<Department, Integer> {

    /**
     * <p>
     * This method return the List of Department
     * if Department is not Deleted
     * </p>
     * @return Department - List of Departments
     */
    List<Department> findByIsDeletedFalse();

    /**
     * <p>
     * This method return the Specific Department Object
     * with departmentId
     * </p>
     * @param departmentId - Department ID to Identify
     * @return Department - Department as Object
     */
    Department findByIsDeletedFalseAndDepartmentId(int departmentId);

    boolean existsByDepartmentName(String departmentName);
}
