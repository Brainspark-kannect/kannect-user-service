package com.kannect.user.service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleDTO {

	
	private Long id;

	@NotBlank(message = "Role name must not be blank.")
    private String roleName;
}
