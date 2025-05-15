package com.kannect.user.service.masters.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kannect.user.service.dto.request.AdminHrUserUpdateDTO;
import com.kannect.user.service.dto.request.EmployeeUpdateDTO;
import com.kannect.user.service.dto.request.UserRegisterRequestDTO;
import com.kannect.user.service.dto.response.SuccessResponse;
import com.kannect.user.service.exception.RequestValidationFailedException;
import com.kannect.user.service.masters.interfaces.IUserContoller;
import com.kannect.user.service.masters.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user/masters/user")
@Validated
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UserController implements IUserContoller {

	private final UserService userService;
	public static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Override
	@PreAuthorize("hasAnyRole('ADMIN', 'HR', 'EMPLOYEE')")
	@GetMapping("/{id}")
	public
	ResponseEntity<SuccessResponse> getUserById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(new SuccessResponse(HttpStatus.OK.value(), HttpStatus.OK, "User fetched successfully",
				userService.getUserById(id)));
	}

	@Override
	@PostMapping("/register")
	public ResponseEntity<SuccessResponse> registerUser(@RequestPart("data") String dto,@RequestPart("file")MultipartFile  profilePicture)
			throws IOException, RequestValidationFailedException {
		ObjectMapper objectmapper = new ObjectMapper();
		UserRegisterRequestDTO userRegisterRequestDTO = new UserRegisterRequestDTO();
		try {
			userRegisterRequestDTO = objectmapper.readValue(dto,
					UserRegisterRequestDTO.class);
		} catch (JsonProcessingException e) {
			LOGGER.error("Error mapping json String to UserRegisterRequestDTO while adding");
		}
		return ResponseEntity.ok(new SuccessResponse(HttpStatus.OK.value(), HttpStatus.OK,
				"User registered successfully", userService.registerUser(userRegisterRequestDTO,profilePicture)));
	}

	@Override
	@PreAuthorize("hasAnyRole('ADMIN', 'HR', 'EMPLOYEE')")
	@GetMapping
	public ResponseEntity<SuccessResponse> getAllUsers() {
		return ResponseEntity.ok(new SuccessResponse(HttpStatus.OK.value(), HttpStatus.OK, "User fetched successfully",
				userService.getAllUsers()));
	}

	@Override
	@PreAuthorize("hasAnyRole('ADMIN', 'HR')")
	@GetMapping("/activated")
	public ResponseEntity<SuccessResponse> getUsersByActivationStatus(@RequestParam("status") Boolean status) {
		return ResponseEntity.ok(new SuccessResponse(HttpStatus.OK.value(), HttpStatus.OK,
				"User fetched successfully", userService.getUserByActivatedByHr(status)));
	}

	@Override
	@PreAuthorize("hasAnyRole('ADMIN', 'HR')")
	@PutMapping("/{id}/admin-hr-update")
	public ResponseEntity<SuccessResponse> updateUserByAdminOrHR(@PathVariable Long id,
			@RequestPart("data") String dto,@RequestPart("file")MultipartFile  profilePicture) throws IOException, RequestValidationFailedException {
		
		ObjectMapper objectmapper = new ObjectMapper();
		AdminHrUserUpdateDTO adminHrUserUpdateDTO = new AdminHrUserUpdateDTO();
		try {
			adminHrUserUpdateDTO = objectmapper.readValue(dto,
					AdminHrUserUpdateDTO.class);
		} catch (JsonProcessingException e) {
			LOGGER.error("Error mapping json String to adminHrUserUpdateDTO while updating");
		}
		return ResponseEntity.ok(new SuccessResponse(HttpStatus.OK.value(), HttpStatus.OK, "User updated successfully",
				userService.updateUserByAdminOrHR(id, adminHrUserUpdateDTO,profilePicture)));
	}

	@Override
	@PreAuthorize("hasAnyRole('ADMIN', 'HR', 'EMPLOYEE')")
	@PutMapping("/{id}/employee-update")
	public ResponseEntity<SuccessResponse> updateEmployeeProfile(@PathVariable Long id,
			@RequestPart("data") String dto,@RequestPart("file")MultipartFile  profilePicture) throws IOException, RequestValidationFailedException {
			ObjectMapper objectmapper = new ObjectMapper();
			EmployeeUpdateDTO employeeUpdateDTO = new EmployeeUpdateDTO();
			try {
				employeeUpdateDTO = objectmapper.readValue(dto,
						EmployeeUpdateDTO.class);
			} catch (JsonProcessingException e) {
				LOGGER.error("Error mapping json String to EmployeeUpdateDTO while updating");
			}
		return ResponseEntity.ok(new SuccessResponse(HttpStatus.OK.value(), HttpStatus.OK, "User updated successfully",
				userService.updateEmployeeProfile(id, employeeUpdateDTO,profilePicture)));
	}

	@Override
	@PreAuthorize("hasAnyRole('ADMIN', 'HR')")
	@DeleteMapping("/{id}")
	public ResponseEntity<SuccessResponse> deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
		return ResponseEntity
				.ok(new SuccessResponse(HttpStatus.OK.value(), HttpStatus.OK, "User deleted successfully", null));
	}

	@Override
	@PreAuthorize("hasAnyRole('ADMIN', 'HR')")
	@PutMapping("/activate")
	public ResponseEntity<SuccessResponse> activateUsers(@RequestBody List<Long> userIds) {
		return ResponseEntity.ok(new SuccessResponse(HttpStatus.OK.value(), HttpStatus.OK,
				"User activated successfully", userService.activateUsers(userIds)));
	}

}
