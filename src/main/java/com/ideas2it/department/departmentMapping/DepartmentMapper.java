package com.ideas2it.department.departmentMapping;

import com.ideas2it.department.dto.DepartmentDto;
import com.ideas2it.model.Department;

public class DepartmentMapper {

    public static DepartmentDto convertToDto(Department department) {
        return new DepartmentDto(department.getDepartmentId(), department.getDepartmentName());
    }

    public static Department convertToEntity(DepartmentDto departmentDto) {
        Department department = new Department();
        department.setDepartmentId(departmentDto.getId());
        department.setDepartmentName(departmentDto.getName());
        return department;
    }
}
