package com.ideas2it.department.service;

import com.ideas2it.department.dao.DepartmentDao;
import com.ideas2it.model.Department;
import com.ideas2it.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * This class is the Service class for Department
 * it send the Data to the Department Repository or Dao
 * </p>
 * @author Aravind
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentDao departmentDao;

    public List<Department> getDepartments() {
        return (List<Department>) departmentDao.findAll();
    }

    public Department addDepartment(Department department) {
        return departmentDao.save(department);
    }
    
    @Override
    public void deleteDepartment(int id) {
        Department department = departmentDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Department not found with ID: " + id));
        department.setIsDeleted(true);
        departmentDao.save(department);
    }

    @Override
    public Department getDepartmentById(int departmentId) {
        Department department = departmentDao.findById(departmentId)
                .orElseThrow(() -> new IllegalArgumentException("Department not found with ID: " + departmentId));
        return department;
    }

    @Override
    public Department updateDepartmentRecord(int id, Department department) {
        Department existingDepartment = departmentDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Department not found with ID: " + id));
        existingDepartment.setDepartmentName(department.getDepartmentName());
        return departmentDao.save(existingDepartment);
    }

    public List<Employee> getEmployeesByDepartmentId(int departmentId) {
        Department department = departmentDao.findById(departmentId)
                .orElseThrow(() -> new IllegalArgumentException("Department Id not found :" + departmentId));
        List<Employee> activeEmployee = new ArrayList<>();
        for(Employee employee : department.getEmployees()) {
            if (employee.getIsDeleted()) {
                activeEmployee.add(employee);
            }
        }
        return activeEmployee;
    }
}