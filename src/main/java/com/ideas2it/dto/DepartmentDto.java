package com.ideas2it.dto;

import com.ideas2it.service.DepartmentService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * This class represents the Dto Object
 * for Department
 */
@Getter
@Setter
@AllArgsConstructor
public class DepartmentDto {
    private int id;

    @NotBlank (message = "Department Name Must not be Blank")
    @Pattern(regexp = "[a-zA-Z\\s]+$", message = "Department Name contains only Alphabet")
    private String name;

    public DepartmentDto(String name) {
        this.name = name;
    }
}

