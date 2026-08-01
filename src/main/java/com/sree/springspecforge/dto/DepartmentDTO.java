package com.sree.springspecforge.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Data Transfer Object for Department details.
 * Used for creating and updating Department entities and for nested responses.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Data Transfer Object for Department details")
public class DepartmentDTO {

    @Schema(description = "Unique identifier of the department", example = "a1b2c3d4-e5f6-7890-1234-567890abcdef")
    private UUID id; // Changed from Integer deptno to UUID id

    @NotBlank(message = "Department name cannot be empty")
    @Size(max = 255, message = "Department name cannot exceed 255 characters")
    @Schema(description = "Name of the department", example = "Engineering")
    private String name; // Changed from deptname to name

    @Schema(description = "Location of the department", example = "New York")
    private String location;

    @DecimalMin(value = "0.00", inclusive = true, message = "Salary must be a non-negative value")
    @DecimalMax(value = "99999999.99", inclusive = true, message = "Salary cannot exceed 99,999,999.99")
    @Schema(description = "The average or standard salary for this department", example = "100000.00")
    private BigDecimal salary;
}