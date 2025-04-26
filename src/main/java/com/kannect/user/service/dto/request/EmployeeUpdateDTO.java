package com.kannect.user.service.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeUpdateDTO {
	@NotBlank(message = "First name must not be blank.")
    private String firstName;

    @NotBlank(message = "Last name must not be blank.")
    private String lastName;

    @NotBlank(message = "Password must not be blank.")
    private String password;
}
