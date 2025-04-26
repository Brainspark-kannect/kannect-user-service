package com.kannect.user.service.masters.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kannect.user.service.dto.RoleDTO;
import com.kannect.user.service.dto.response.SuccessResponse;
import com.kannect.user.service.masters.interfaces.IRoleController;
import com.kannect.user.service.masters.service.RoleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/masters/role")
@Validated
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class RoleController implements IRoleController {

	private final RoleService roleService;

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<SuccessResponse> getRoleById(@PathVariable Long id) {
		return ResponseEntity.ok(new SuccessResponse(HttpStatus.OK.value(), HttpStatus.OK, "Role fetched successfully",
				roleService.getRoleById(id)));
	}

	@Override
	@GetMapping
	public ResponseEntity<SuccessResponse> getAllRoles() {
		return ResponseEntity.ok(new SuccessResponse(HttpStatus.OK.value(), HttpStatus.OK, "Role fetched successfully",
				roleService.getAllRoles()));
	}

	@Override
	@PostMapping
	public ResponseEntity<SuccessResponse> createRole(@RequestBody RoleDTO roleDTO) {
		return ResponseEntity.ok(new SuccessResponse(HttpStatus.OK.value(), HttpStatus.OK, "Role added successfully",
				roleService.createRole(roleDTO)));
	}

	@Override
	@PutMapping("/{id}")
	public ResponseEntity<SuccessResponse> updateRole(@PathVariable Long id, @RequestBody RoleDTO roleDTO) {
		return ResponseEntity.ok(new SuccessResponse(HttpStatus.OK.value(), HttpStatus.OK, "Role updated successfully",
				roleService.updateRole(id, roleDTO)));
	}

	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<SuccessResponse> deleteRole(@PathVariable Long id) {
		roleService.deleteRole(id);
		return ResponseEntity
				.ok(new SuccessResponse(HttpStatus.OK.value(), HttpStatus.OK, "Role deleted successfully", null));
	}
}
