package com.kannect.user.service.masters.service;

import java.util.List;

import com.kannect.user.service.dto.RoleDTO;

public interface RoleService {

	void deleteRole(Long id);

	RoleDTO updateRole(Long id, RoleDTO dto);

	RoleDTO createRole(RoleDTO dto);

	List<RoleDTO> getAllRoles();

	RoleDTO getRoleById(Long id);

}
