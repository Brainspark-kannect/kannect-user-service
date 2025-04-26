package com.kannect.user.service.masters.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kannect.user.service.dto.RoleDTO;
import com.kannect.user.service.dto.mapper.RoleMapper;
import com.kannect.user.service.exception.ResourceNotFoundException;
import com.kannect.user.service.masters.entity.Role;
import com.kannect.user.service.masters.repository.RoleRepository;
import com.kannect.user.service.masters.service.RoleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

	private RoleRepository roleRepository;

	private RoleMapper roleMapper;

	@Override
	public RoleDTO getRoleById(Long id) {
		Role role = roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Role not found"));
		return roleMapper.mapToRoleDTO(role);
	}

	@Override
	public List<RoleDTO> getAllRoles() {
		List<Role> roles = roleRepository.findAll();
		return roleMapper.mapToRoleDTOs(roles);
	}

	@Override
	public RoleDTO createRole(RoleDTO dto) {

		Role role = roleRepository.save(roleMapper.mapToRole(dto));

		return roleMapper.mapToRoleDTO(role);
	}

	@Override
	public RoleDTO updateRole(Long id, RoleDTO dto) {
		Role role = roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Role not found"));
		role = roleMapper.map(dto, role);
		role = roleRepository.save(role);
		return roleMapper.mapToRoleDTO(role);
	}

	@Override
	public void deleteRole(Long id) {
		roleRepository.deleteById(id);
	}

}
