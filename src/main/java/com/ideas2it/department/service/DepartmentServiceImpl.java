package com.ideas2it.department.service;

import com.ideas2it.department.dao.DepartmentDao;
import com.ideas2it.department.departmentMapping.DepartmentMapper;
import com.ideas2it.department.dto.DepartmentDto;
import com.ideas2it.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.ideas2it.department.departmentMapping.DepartmentMapper.convertToDto;

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

    public List<DepartmentDto> getDepartments() {
        List<DepartmentDto> result = new ArrayList<>();
        Iterable<Department> departments = departmentDao.findAll();
        for (Department department : departments) {
            result.add(DepartmentMapper.convertToDto(department));
        }
        return result;
    }

    @Override
    public DepartmentDto addDepartment(DepartmentDto departmentDto) {
        Department department = DepartmentMapper.convertToEntity(departmentDto);
        Department savedDepartment = departmentDao.save(department);
        return DepartmentMapper.convertToDto(savedDepartment);
    }
    
    @Override
    public void deleteDepartment(int id) {
 	    departmentDao.deleteById(id);
    }

    @Override
    public DepartmentDto getDepartmentById(int departmentId) {
        Department department = departmentDao.findById(departmentId)
                .orElseThrow(() -> new IllegalArgumentException("Department not found with ID: " + departmentId));
        return DepartmentMapper.convertToDto(department);
    }

    @Override
    public DepartmentDto updateDepartmentRecord(int id, DepartmentDto departmentDto) {
        Department existingDepartment = departmentDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Department not found with ID: " + id));
        existingDepartment.setDepartmentName(departmentDto.getName());
        Department updatedDepartment = departmentDao.save(existingDepartment);
        return DepartmentMapper.convertToDto(updatedDepartment);
    }
}