package com.sree.springspecforge.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Response DTO for User details")
public class UserResponseDTO {

    @Schema(description = "Unique identifier of the user", example = "a1b2c3d4-e5f6-7890-1234-567890abcdef")
    private UUID id;

    @Schema(description = "First name of the user", example = "John")
    private String firstName;

    @Schema(description = "Last name of the user", example = "Doe")
    private String lastName;

    @Schema(description = "Email address of the user", example = "john.doe@example.com")
    private String email;

    @Schema(description = "The annual salary associated with the user's department", example = "75000.00")
    private BigDecimal salary; // This field is derived from Department.salary

    @Schema(description = "Creation timestamp of the user record", example = "2023-01-01T12:00:00Z")
    private Instant createdAt;

    @Schema(description = "Last update timestamp of the user record", example = "2023-01-01T13:00:00Z")
    private Instant updatedAt;

    @Schema(description = "Address details of the user")
    private AddressDTO address;

    @Schema(description = "Department details of the user")
    private DepartmentDTO department; // Nested DepartmentDTO
}