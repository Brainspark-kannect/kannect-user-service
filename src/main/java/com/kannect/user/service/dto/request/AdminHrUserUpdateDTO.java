package com.kannect.user.service.dto.request;

import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminHrUserUpdateDTO {
	@NotBlank(message = "Username must not be blank.")
    private String userName;

    @NotBlank(message = "Password must not be blank.")
    private String password;

    @Email(message = "Email must be valid.")
    @NotBlank(message = "Email must not be blank.")
    private String email;

    @NotBlank(message = "First name must not be blank.")
    private String firstName;

    @NotBlank(message = "Last name must not be blank.")
    private String lastName;

    private String department; // Optional

    private String techStack; // Optional

    @NotEmpty(message = "At least one role must be assigned.")
    private Set<String> roles;
}
