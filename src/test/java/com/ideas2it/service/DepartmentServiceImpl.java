package com.ideas2it.service;

import com.ideas2it.dao.DepartmentDao;
import com.ideas2it.dto.DepartmentDto;
import com.ideas2it.mapper.DepartmentMapper;
import com.ideas2it.model.Department;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DepartmentServiceImpl implements DepartmentServiceTest {
    private DepartmentDao departmentDao;

    public List<DepartmentDto> getDepartments() {
        List<DepartmentDto> departmentDtos = new ArrayList<>();
        List<Department> departments = departmentDao.findAll();
        for (Department department : departments) {
            departmentDtos.add(DepartmentMapper.convertToDto(department));
        }
        return departmentDtos;
    }

    @Override
    public DepartmentDto getDepartmentById(int departmentId) {
        Department department = departmentDao.findById(departmentId).orElse(null);
        return DepartmentMapper.convertToDto(department);
    }

    @Override
    public DepartmentDto addDepartment(DepartmentDto departmentDto) {
        Department department = departmentDao.save(DepartmentMapper.convertToEntity(departmentDto));
        return DepartmentMapper.convertToDto(department);
    }

    @Override
    public void deleteDepartment(int departmentId) {
        Department department = departmentDao.findByIsDeletedFalseAndDepartmentId(departmentId);
        department.setDeleted(true);
        departmentDao.save(department);
    }

    @Override
    public DepartmentDto updateDepartmentRecord(DepartmentDto departmentDto) {
        Department department = DepartmentMapper.convertToEntity(departmentDto);
        Department existingDepartment = departmentDao.findByIsDeletedFalseAndDepartmentId(departmentDto.getId());
        existingDepartment.setDepartmentName(department.getDepartmentName());
        return DepartmentMapper.convertToDto(departmentDao.save(existingDepartment));
    }
}

