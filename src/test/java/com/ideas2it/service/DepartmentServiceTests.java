package com.ideas2it.service;

import com.ideas2it.dao.DepartmentDao;
import com.ideas2it.dto.DepartmentDto;
import com.ideas2it.model.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTests {
    @Mock
    private DepartmentDao departmentDao;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    private Department department;

    @BeforeEach
    void setUp() {
        department = new Department(1, "Admin");
    }

    @DisplayName("JUnit test for getAllDepartments method")
    @Test
    public void givenDepartmentList_whenGetAllDepartments_thenReturnDepartmentList(){
        when(departmentDao.findAll()).thenReturn(List.of(department));
        List<DepartmentDto> departments = departmentService.getDepartments();

        assertThat(departments).isNotNull();
        assertThat(departments.size()).isEqualTo(1);
    }

    @Test
    public void givenDepartmentId_whenGetDepartmentById_thenReturnDepartmentObject(){
        when(departmentDao.findById(1)).thenReturn(Optional.of(department));
        DepartmentDto savedDepartment = departmentService.getDepartmentById(department.getDepartmentId());
        assertThat(savedDepartment).isNotNull();
    }
}
