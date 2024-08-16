package com.ideas2it.controller;

import com.ideas2it.dto.DepartmentDto;
import com.ideas2it.service.DepartmentServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DepartmentController.class)
public class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentServiceImpl departmentService;

    @Test
    void testGetAllDepartments() throws Exception {
        // Arrange
        DepartmentDto department = new DepartmentDto(1, "Admin");
        given(departmentService.getDepartments()).willReturn(Collections.singletonList(department));

        // Act & Assert
        mockMvc.perform(get("/api/v1/departments")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("HR"));
    }

    @Test
    void testGetDepartmentById() throws Exception {
        // Arrange
        DepartmentDto department = new DepartmentDto(1, "Finance");
        given(departmentService.getDepartmentById(1)).willReturn(department);

        // Act & Assert
        mockMvc.perform(get("/api/v1/departments/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Finance"));
    }

    @Test
    void testAddDepartment() throws Exception {
        // Arrange
        DepartmentDto dto = new DepartmentDto(2, "Marketing");
        given(departmentService.addDepartment(dto)).willReturn(dto);

        // Act & Assert
        mockMvc.perform(post("/api/v1/departments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":2,\"name\":\"Marketing\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Marketing"));
    }

    @Test
    void testDeleteDepartment() throws Exception {
        // Arrange
        // Mocking the service behavior for delete, assuming void return type.
        // You might need to use doNothing() if your method has a void return type.
        // when(departmentService.deleteDepartment(1)).thenReturn();

        // Act & Assert
        mockMvc.perform(delete("/api/v1/departments/1"))
                .andExpect(status().isOk());
    }
}