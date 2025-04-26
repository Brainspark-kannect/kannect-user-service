package com.kannect.user.service.masters.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.kannect.user.service.dto.request.AdminHrUserUpdateDTO;
import com.kannect.user.service.dto.request.EmployeeUpdateDTO;
import com.kannect.user.service.dto.request.UserRegisterRequestDTO;
import com.kannect.user.service.dto.response.UserResponseDTO;
import com.kannect.user.service.exception.RequestValidationFailedException;

public interface UserService {

	UserResponseDTO getUserById(Long id);

	List<UserResponseDTO> getUserByActivatedByHr(Boolean activatedByHr);

	List<UserResponseDTO> getAllUsers();

	UserResponseDTO registerUser(UserRegisterRequestDTO requestDTO, MultipartFile profilePicture) throws IOException, RequestValidationFailedException;

	UserResponseDTO updateUserByAdminOrHR(Long id, AdminHrUserUpdateDTO dto, MultipartFile profilePicture) throws IOException, RequestValidationFailedException;

	UserResponseDTO updateEmployeeProfile(Long id, EmployeeUpdateDTO dto, MultipartFile profilePicture) throws IOException, RequestValidationFailedException;

	void deleteUser(Long id);

	List<UserResponseDTO> activateUsers(List<Long> userIds);

}
