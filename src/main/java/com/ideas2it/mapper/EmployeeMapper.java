package com.ideas2it.mapper;

import com.ideas2it.dto.CreateEmployeeDto;
import com.ideas2it.dto.EmployeeDto;
import com.ideas2it.model.Department;
import com.ideas2it.model.Employee;

public class EmployeeMapper {

    /**
     * This method convert the Employee Object to Dto Object
     * @param employee - Employee as Object
     * @return - EmployeeDto as Dto Object
     */
    public static EmployeeDto convertEntityToDto(Employee employee) {
        EmployeeDto dto = new EmployeeDto();
        dto.setEmployeeId(employee.getEmployeeId());
        dto.setEmployeeName(employee.getEmployeeName());
        dto.setDateOfBirth(employee.getDateOfBirth());
        dto.setAge(employee.getAge());
        dto.setDepartmentName(employee.getDepartment().getDepartmentName());
        dto.setMobileNumber(employee.getMobileNumber());
        dto.setQualification(employee.getQualification());
        dto.setEmployeeEmail(employee.getEmployeeEmail());
        dto.setExperience(employee.getExperience());
        dto.setBankName(employee.getAccount().getBankName());
        dto.setMentorName(employee.getNames());
        return dto;
    }

    /**
     * This method Convert the Dto Object to Employee Object
     * @param employeeDto - Employee Dto
     * @return Employee - Employee as Object
     */
    public static Employee convertDtoToEntity(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setEmployeeId(employeeDto.getEmployeeId());
        employee.setEmployeeName(employeeDto.getEmployeeName());
        employee.setDepartment(new Department(employeeDto.getDepartmentName()));
        employee.setDateOfBirth(employeeDto.getDateOfBirth());
        employee.setMobileNumber(employeeDto.getMobileNumber());
        employee.setEmployeeEmail(employeeDto.getEmployeeEmail());
        employee.setExperience(employeeDto.getExperience());
        employee.setQualification(employeeDto.getQualification());
        return employee;
    }

    public static CreateEmployeeDto convertToDto(Employee employee) {
        CreateEmployeeDto dto = new CreateEmployeeDto();
        dto.setEmployeeId(employee.getEmployeeId());
        dto.setEmployeeName(employee.getEmployeeName());
        dto.setAge(employee.getAge());
        dto.setDepartmentName(employee.getDepartment().getDepartmentName());
        dto.setMobileNumber(employee.getMobileNumber());
        dto.setQualification(employee.getQualification());
        dto.setEmployeeEmail(employee.getEmployeeEmail());
        dto.setExperience(employee.getExperience());
        dto.setBankName(employee.getAccount().getBankName());
        return dto;
    }
}
