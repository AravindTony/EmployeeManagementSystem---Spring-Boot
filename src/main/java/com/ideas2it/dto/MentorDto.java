package com.ideas2it.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * This Dto class represents the mentor details like
 * Mentor ID, Name Information etc.,
 */
@Getter
@Setter
@AllArgsConstructor
public class MentorDto {
    private int id;
    @NotBlank (message = "Mentor name cannot be blank.")
    @Pattern (regexp = "[a-zA-Z\\s]+$")
    private String name;
}
