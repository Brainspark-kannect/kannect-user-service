package com.kannect.user.service.dto.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.kannect.user.service.dto.request.AdminHrUserUpdateDTO;
import com.kannect.user.service.dto.response.UserResponseDTO;
import com.kannect.user.service.masters.entity.Role;
import com.kannect.user.service.masters.entity.User;

@Component
public class UserMapper {

	private final ModelMapper modelMapper = new ModelMapper();

	public UserResponseDTO mapToUserResponseDTO(User user) {
		UserResponseDTO dto = modelMapper.map(user, UserResponseDTO.class);

		Set<String> roleNames = user.getRoles().stream().map(Role::getRoleName).collect(Collectors.toSet());

		dto.setRoleNames(roleNames);

		return dto;
	}

	public List<UserResponseDTO> mapToUserResponseDTOs(List<User> users) {
		List<UserResponseDTO> userResponseDTOs = new ArrayList<>();
		for (User user : users) {
			userResponseDTOs.add(mapToUserResponseDTO(user));
		}
		return userResponseDTOs;
	}

	public User mapAdminHrUserUpdateDTOToUserEntity(AdminHrUserUpdateDTO dto, User user) {
		modelMapper.map(dto, user);
		return user;
	}

}
