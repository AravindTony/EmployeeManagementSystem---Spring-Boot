package com.ideas2it.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import com.ideas2it.dto.DepartmentDto;
import com.ideas2it.dto.EmployeeDto;
import com.ideas2it.exceptions.EmployeeException;
import com.ideas2it.mapper.DepartmentMapper;
import com.ideas2it.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ideas2it.dao.DepartmentDao;
import com.ideas2it.model.Department;
import com.ideas2it.model.Employee;

/**
 * <p>
 * This class is the Service class for Department
 * it send the Data to the Department Repository or Dao
 * </p>
 * @author Aravind
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {
    private static final Logger logger = LogManager.getLogger();

    @Autowired
    DepartmentDao departmentDao;

    public List<DepartmentDto> getDepartments() {
        List<DepartmentDto> result = new ArrayList<>();
        Iterable<Department> departments = departmentDao.findAll();
        for (Department department : departments) {
            result.add(DepartmentMapper.convertToDto(department));
        }
        return result;
    }

    public DepartmentDto addDepartment(DepartmentDto departmentDto) {
        Department department = DepartmentMapper.convertToEntity(departmentDto);
        if (departmentDao.existsByDepartmentName(department.getDepartmentName())) {
            throw new EmployeeException("Department Already Added with Name :" + departmentDto.getName());
        }
        return DepartmentMapper.convertToDto(departmentDao.save(department));
    }
    
    @Override
    public void deleteDepartment(int departmentId) {
        Department department = departmentDao.findByIsDeletedFalseAndDepartmentId(departmentId);
        if (null == department) {
            logger.warn("Department not found while Delete Department with Id :{}", departmentId);
            throw new NoSuchElementException("Department Not found with Id :" + departmentId);
        }
        department.setDeleted(true);
        departmentDao.save(department);
    }

    @Override
    public DepartmentDto getDepartmentById(int departmentId) {
        Department department = departmentDao.findByIsDeletedFalseAndDepartmentId(departmentId);
        if (department == null) {
            logger.warn("Department not found while getting Department with Id :{}", departmentId);
            throw new NoSuchElementException("Department Not found with Id : " +departmentId);
        } else {
            return DepartmentMapper.convertToDto(department);
        }
    }

    @Override
    public DepartmentDto updateDepartmentRecord(DepartmentDto departmentDto) {
        Department department = DepartmentMapper.convertToEntity(departmentDto);
        Department existingDepartment = departmentDao.findByIsDeletedFalseAndDepartmentId(departmentDto.getId());
        existingDepartment.setDepartmentName(department.getDepartmentName());
        return DepartmentMapper.convertToDto(departmentDao.save(existingDepartment));
    }

    public List<EmployeeDto> getEmployeesByDepartmentId(int departmentId) {
        Department department = departmentDao.findByIsDeletedFalseAndDepartmentId(departmentId);
        List<EmployeeDto> isEmployee = new ArrayList<>();
        for(Employee employee : department.getEmployees()) {
            isEmployee.add(EmployeeMapper.convertEntityToDto(employee));
        }
        return isEmployee;
    }
}