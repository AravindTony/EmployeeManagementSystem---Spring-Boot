package com.ideas2it.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.ideas2it.dto.CreateEmployeeDto;
import com.ideas2it.exceptions.EmployeeException;
import com.ideas2it.mapper.MentorMapper;
import com.ideas2it.dto.EmployeeDto;
import com.ideas2it.mapper.DepartmentMapper;
import com.ideas2it.mapper.EmployeeMapper;
import com.ideas2it.model.Department;
import com.ideas2it.model.SalaryAccount;
import com.ideas2it.dao.EmployeeDao;
import com.ideas2it.model.Employee;
import com.ideas2it.model.Mentor;

/**
 * <p>
 *This Class is the service class for the Employee Controller
 * and Dao for the Business Logic
 * </p>
 * @author Aravind
 */
public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private MentorService mentorService;
    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public List<EmployeeDto> getEmployeeDetails() {
        List<EmployeeDto> result = new ArrayList<>();
        List<Employee> employees = employeeDao.findByIsDeletedFalse();
        if (employees.isEmpty()) {
            logger.warn("Employee List is Empty while getting Employees");
        } else {
            for (Employee employee : employees) {
                result.add(EmployeeMapper.convertEntityToDto(employee));
            }
        }
        return result;
    }

    @Override
    public CreateEmployeeDto addData(EmployeeDto employeeDto) {
        Department department = DepartmentMapper.convertToEntity(departmentService.getDepartmentById(employeeDto.getDepartmentId()));
        SalaryAccount account = new SalaryAccount(employeeDto.getAccountNumber(), employeeDto.getBankName());
        Employee employee = EmployeeMapper.convertDtoToEntity(employeeDto);
        employee.setDepartment(department);
        employee.setAccount(account);
        logger.info("Employee Created Successfully with name : {}", employee.getEmployeeName());
        return EmployeeMapper.convertToDto(employeeDao.save(employee));
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.convertDtoToEntity(employeeDto);
        Employee existingEmployee = employeeDao.findByIsDeletedFalseAndEmployeeId(employeeDto.getEmployeeId());
        existingEmployee.setEmployeeName(employee.getEmployeeName());
        existingEmployee.setDateOfBirth(employee.getDateOfBirth());
        existingEmployee.setMobileNumber(employee.getMobileNumber());
        existingEmployee.setEmployeeEmail(employee.getEmployeeEmail());
        existingEmployee.setExperience(employee.getExperience());
        logger.info("Employee Updated Successfully..");
        return EmployeeMapper.convertEntityToDto(employeeDao.save(existingEmployee));
    }

    @Override
    public void deleteEmployee(int employeeId) {
        Employee employee = employeeDao.findByIsDeletedFalseAndEmployeeId(employeeId);
        if (null == employee) {
            logger.warn("Employee Not found while Delete Employee with Id :{}", employeeId);
            throw new NoSuchElementException("Employee Not found with Id :" + employeeId);
        }
        employee.setDeleted(true);
        employeeDao.save(employee);
    }

    @Override
    public EmployeeDto getEmployeeById(int employeeId) {
        Employee employee = employeeDao.findByIsDeletedFalseAndEmployeeId(employeeId);
        if (null == employee) {
            logger.warn("Employee data not found while get Employee with Id : {}", employeeId);
            throw new NoSuchElementException("Employee Not Found with Id : " + employeeId);
        }
        return EmployeeMapper.convertEntityToDto(employee);
    }

    public EmployeeDto assignEmployee(int employeeId, int mentorId) {
        Mentor mentor = MentorMapper.convertToEntity(mentorService.getMentorById(mentorId));
        Employee employee = employeeDao.findByIsDeletedFalseAndEmployeeId(employeeId);
        if (null == employee) {
            if (null == mentor) {
                logger.warn("Mentor not found..");
                throw new NoSuchElementException("Mentor Not found with Id :" + mentorId);
            }
            logger.warn("Employee not found while Assign..");
            throw new NoSuchElementException("Employee Not Found with Id : " + employeeId);
        }
        for (Mentor currentMentor : employee.getMentors()) {
                if (currentMentor.getMentorId() == mentorId) {
                    throw new EmployeeException("Employee already Assigned with This Mentor with" +
                            " Id :" + mentorId);
                }
                employee.getMentors().add(mentor);
                logger.info("Employee Assigned Successfully..");
        }
        return EmployeeMapper.convertEntityToDto(employeeDao.save(employee));
    }
}