package com.kannect.user.service.dto.response;

import java.time.LocalDateTime;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private Long id;
    private String email;
    private String userName;
    private String firstName;
    private String lastName;
    private String department;
    private String techStack;
    private String profilePhotoUrl;
    private Set<String> roleNames;
    private Boolean active;
    private Integer walletBalance;
    private LocalDateTime lastLogin;
}
