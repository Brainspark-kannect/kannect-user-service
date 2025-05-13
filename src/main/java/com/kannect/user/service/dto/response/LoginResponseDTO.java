package com.kannect.user.service.dto.response;

import java.time.LocalDateTime;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponseDTO {
	private Long id;
	private LocalDateTime lastLogin;
	private String firstName;
	private String lastName;
	private String token;
	private String userName;
	private Set<String> roleNames;
}