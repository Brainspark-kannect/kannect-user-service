package com.kannect.user.service.masters.service.impl;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kannect.user.service.auth.entity.Role;
import com.kannect.user.service.auth.entity.User;
import com.kannect.user.service.auth.repository.UserRepository;
import com.kannect.user.service.dto.mapper.UserMapper;
import com.kannect.user.service.dto.request.AdminHrUserUpdateDTO;
import com.kannect.user.service.dto.request.EmployeeUpdateDTO;
import com.kannect.user.service.dto.request.UserRegisterRequestDTO;
import com.kannect.user.service.dto.response.UserResponseDTO;
import com.kannect.user.service.exception.RequestValidationFailedException;
import com.kannect.user.service.exception.ResourceNotFoundException;
import com.kannect.user.service.masters.repository.RoleRepository;
import com.kannect.user.service.masters.service.EmailService;
import com.kannect.user.service.masters.service.UserService;
import com.kannect.user.service.utils.GcpStorageUploader;

import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	public static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	private final UserRepository userRepository;
	private final UserMapper userMapper;
	private final PasswordEncoder passwordEncoder;
	private final RoleRepository roleRepository;
	private final Validator validator;
	private final GcpStorageUploader gcpStorageUploader;
	private final EmailService emailService;

	private static final List<String> VALID_IMAGE_TYPES = Arrays.asList("image/jpeg", "image/png", "image/jpg",
			"image/gif", "image/webp");

	private static final String FILE_NAME_REGEX = "^[a-zA-Z0-9._-]+$";

	private static final List<String> VALID_IMAGE_EXTENSIONS = Arrays.asList("jpg", "jpeg", "png", "gif", "webp");

	@Override
	public UserResponseDTO getUserById(Long id) {
		Optional<User> userOptional = userRepository.findById(id);
		if (userOptional.isEmpty()) {
			LOGGER.error("User not found for id: " + id);
			throw new ResourceNotFoundException("User not found for id: " + id);
		}

		return userMapper.mapToUserResponseDTO(userOptional.get());
	}

	@Override
	public List<UserResponseDTO> getUserByActivatedByHr(Boolean active) {
		List<User> users = userRepository.findByActive(active);
		if (users.isEmpty()) {
			LOGGER.error("Users not found");
			throw new ResourceNotFoundException("Users not found");
		}

		return userMapper.mapToUserResponseDTOs(users);
	}

	@Override
	public List<UserResponseDTO> getAllUsers() {
		List<User> users = userRepository.findAll();
		if (users.isEmpty()) {
			LOGGER.error("Users not found");
			throw new ResourceNotFoundException("Users not found");
		}

		return userMapper.mapToUserResponseDTOs(users);
	}

	@Override
	@Transactional
	public UserResponseDTO registerUser(UserRegisterRequestDTO requestDTO, MultipartFile profilePhoto)
			throws IOException, RequestValidationFailedException {
		String profilePhotoUrl = null;
		validateDTOAndFile(requestDTO, profilePhoto);

		if (profilePhoto != null && !profilePhoto.isEmpty()) {
			String fileName = "profile-photos/" + UUID.randomUUID() + "-" + profilePhoto.getOriginalFilename();
			profilePhotoUrl = gcpStorageUploader.uploadFile(profilePhoto, fileName);
		}

		String hashedPassword = passwordEncoder.encode(requestDTO.getPassword());

		Set<Role> userRoles = requestDTO.getRoles().stream().map(roleStr -> {
			return roleRepository.findByRoleName(roleStr)
					.orElseThrow(() -> new ResourceNotFoundException("Role not found: " + roleStr));
		}).collect(Collectors.toSet());

		User user = User.builder().userName(requestDTO.getUserName()).email(requestDTO.getEmail())
				.password(hashedPassword).firstName(requestDTO.getFirstName()).lastName(requestDTO.getLastName())
				.department(requestDTO.getDepartment()).techStack(requestDTO.getTechStack())
				.profilePhotoUrl(profilePhotoUrl).roles(userRoles).walletBalance(0).active(false).build();

		user = userRepository.save(user);

		emailService.sendEmail(List.of(user.getEmail()), null, "Welcome to Kannect!", "Hello " + user.getFirstName()
				+ ",\n\nYour account has been successfully created. Awaiting HR activation.\n\nRegards,\nKannect Team");

		return userMapper.mapToUserResponseDTO(user);
	}

	@Override
	@Transactional
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	@Transactional
	public UserResponseDTO updateEmployeeProfile(Long id, EmployeeUpdateDTO dto, MultipartFile profilePhoto)
			throws IOException, RequestValidationFailedException {
		User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
		if (StringUtils.isNotBlank(dto.getOldPassword()) && StringUtils.isNotBlank(dto.getPassword())) {
			// Compare with current user password
			if (!StringUtils.equals(passwordEncoder.encode(dto.getOldPassword()), user.getPassword())) {
				throw new IllegalArgumentException("Old password does not match.");
			}

			if (StringUtils.isNotBlank(dto.getPassword())) {
				user.setPassword(passwordEncoder.encode(dto.getPassword()));
			}
		}

		validateDTOAndFile(dto, profilePhoto);
		if (StringUtils.isNotBlank(dto.getFirstName())) {
			user.setFirstName(dto.getFirstName());
		}
		if (StringUtils.isNotBlank(dto.getLastName())) {
			user.setLastName(dto.getLastName());
		}

		String profilePhotoUrl = user.getProfilePhotoUrl();

		if (profilePhoto != null && !profilePhoto.isEmpty()) {
			String fileName = "profile-photos/" + UUID.randomUUID() + "-" + profilePhoto.getOriginalFilename();
			profilePhotoUrl = gcpStorageUploader.uploadFile(profilePhoto, fileName);
			user.setProfilePhotoUrl(profilePhotoUrl);
		}

		user = userRepository.save(user);

		emailService.sendEmail(List.of(user.getEmail()), null, "Profile Updated Successfully", "Hi "
				+ user.getFirstName() + ",\n\nYour profile has been successfully updated.\n\nRegards,\nKannect Team");

		return userMapper.mapToUserResponseDTO(user);
	}

	@Override
	@Transactional
	public UserResponseDTO updateUserByAdminOrHR(Long id, AdminHrUserUpdateDTO dto, MultipartFile profilePhoto)
			throws IOException, RequestValidationFailedException {
		User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

		validateDTOAndFile(dto, profilePhoto);
		String profilePhotoUrl = user.getProfilePhotoUrl();
		user = userMapper.mapAdminHrUserUpdateDTOToUserEntity(dto, user);
		if (profilePhoto != null && !profilePhoto.isEmpty()) {
			String fileName = "profile-photos/" + UUID.randomUUID() + "-" + profilePhoto.getOriginalFilename();
			profilePhotoUrl = gcpStorageUploader.uploadFile(profilePhoto, fileName);
			user.setProfilePhotoUrl(profilePhotoUrl);
		}

		if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
			user.setPassword(passwordEncoder.encode(dto.getPassword()));
		}
		if (dto.getRoles() != null && !dto.getRoles().isEmpty()) {
			Set<Role> roles = dto.getRoles().stream()
					.map(roleName -> roleRepository.findByRoleName(roleName)
							.orElseThrow(() -> new RuntimeException("Role not found: " + roleName)))
					.collect(Collectors.toSet());
			user.setRoles(roles);
		}
		userRepository.save(user);
		return userMapper.mapToUserResponseDTO(user);
	}

	public <T> void validateDTOAndFile(T dto, MultipartFile multipartFile) throws RequestValidationFailedException {

		// 1. Validate DTO
		Set<ConstraintViolation<T>> violations = validator.validate(dto);
		if (!violations.isEmpty()) {
			Map<String, String> errors = new HashMap<>();
			for (ConstraintViolation<T> violation : violations) {
				errors.put(violation.getPropertyPath().toString(), violation.getMessage());
			}
			LOGGER.error("DTO validation failed: {}", errors);
			throw new RequestValidationFailedException(errors.toString());
		}

		// 2. Validate File (if present)
		if (multipartFile != null && !multipartFile.isEmpty()) {
			String contentType = multipartFile.getContentType();
			String originalFilename = multipartFile.getOriginalFilename();

			if (StringUtils.isBlank(contentType) || !VALID_IMAGE_TYPES.contains(contentType)) {
				LOGGER.error("File validation failed: Invalid content type: {}", contentType);
				throw new RequestValidationFailedException("Invalid file type: " + contentType);
			}

			if (StringUtils.isBlank(originalFilename) || !originalFilename.matches(FILE_NAME_REGEX)) {
				LOGGER.error("File validation failed: Invalid filename: {}", originalFilename);
				throw new RequestValidationFailedException("Invalid file name: " + originalFilename);
			}

			// 2. Validate file extension
			String fileExtension = getFileExtension(originalFilename);
			if (StringUtils.isBlank(fileExtension)
					|| VALID_IMAGE_EXTENSIONS.stream().noneMatch(ext -> ext.equalsIgnoreCase(fileExtension))) {

				LOGGER.error("File validation failed: Invalid file extension: {}", fileExtension);
				throw new RequestValidationFailedException("Invalid file extension: " + fileExtension);
			}

		}
	}

	private String getFileExtension(String filename) {
		if (StringUtils.isBlank(filename) || !filename.contains(".")) {
			return "";
		}
		return filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
	}

	@Override
	@Transactional
	public List<UserResponseDTO> activateUsers(List<Long> userIds) {
		List<User> users = userRepository.findAllById(userIds);
		for (User user : users) {
			user.setActive(true);
			emailService.sendEmail(List.of(user.getEmail()), null, "Your Account is Now Active", "Dear "
					+ user.getFirstName()
					+ ",\n\nYour account has been activated by HR. You can now log in and access all features.\n\nRegards,\nKannect Team");
		}
		users = userRepository.saveAll(users);

		return userMapper.mapToUserResponseDTOs(users);
	}

}
