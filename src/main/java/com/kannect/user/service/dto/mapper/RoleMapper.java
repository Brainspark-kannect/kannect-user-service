package com.kannect.user.service.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.kannect.user.service.dto.RoleDTO;
import com.kannect.user.service.masters.entity.Role;

@Component
public class RoleMapper {
	
	private final ModelMapper modelMapper = new ModelMapper();

	public RoleDTO mapToRoleDTO(Role role) {
		return modelMapper.map(role, RoleDTO.class);
	}

	public List<RoleDTO> mapToRoleDTOs(List<Role> roles) {
		List<RoleDTO> dtos=new ArrayList<>();
		for(Role role:roles) {
			dtos.add(mapToRoleDTO(role));
		}
		return null;
	}

	public Role map(RoleDTO dto, Role role) {
		modelMapper.map(dto, role);
		return role;		
	}

	public Role mapToRole(RoleDTO dto) {
		return modelMapper.map(dto, Role.class);
	}

}
