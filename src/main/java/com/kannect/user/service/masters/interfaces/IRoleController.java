package com.kannect.user.service.masters.interfaces;

import org.springframework.http.ResponseEntity;

import com.kannect.user.service.dto.RoleDTO;
import com.kannect.user.service.dto.response.ErrorResponse;
import com.kannect.user.service.dto.response.SuccessResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Tag(name = "Role", description = "Role Api")
public interface IRoleController {

	@Operation(summary = "Get role by id", description = "Get role by id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Success", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = SuccessResponse.class)) }),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }),
			@ApiResponse(responseCode = "403", description = "Forbidden", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }),
			@ApiResponse(responseCode = "404", description = "Transaction not found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }),
			@ApiResponse(responseCode = "409", description = "Conflict", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }) })
	ResponseEntity<SuccessResponse> getRoleById(@Positive @NotNull @Parameter(description = "Role id") Long id);

	@Operation(summary = "Get all roles", description = "Get all roles")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Success", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = SuccessResponse.class)) }),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }),
			@ApiResponse(responseCode = "403", description = "Forbidden", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }),
			@ApiResponse(responseCode = "404", description = "Transaction not found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }),
			@ApiResponse(responseCode = "409", description = "Conflict", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }) })
	ResponseEntity<SuccessResponse> getAllRoles();

	@Operation(summary = "Add role", description = "Add role")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Success", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = SuccessResponse.class)) }),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }),
			@ApiResponse(responseCode = "403", description = "Forbidden", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }),
			@ApiResponse(responseCode = "404", description = "Transaction not found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }),
			@ApiResponse(responseCode = "409", description = "Conflict", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }) })
	ResponseEntity<SuccessResponse> createRole(
			@Valid @NotNull @Parameter(description = "Role details") RoleDTO roleDTO);

	@Operation(summary = "Update role", description = "Update role")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Success", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = SuccessResponse.class)) }),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }),
			@ApiResponse(responseCode = "403", description = "Forbidden", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }),
			@ApiResponse(responseCode = "404", description = "Transaction not found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }),
			@ApiResponse(responseCode = "409", description = "Conflict", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }) })
	ResponseEntity<SuccessResponse> updateRole(@Positive @NotNull @Parameter(description = "Role id") Long id,
			@Valid @NotNull @Parameter(description = "Role details") RoleDTO roleDTO);

	@Operation(summary = "Delete role by id", description = "Delete role by id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Success", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = SuccessResponse.class)) }),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }),
			@ApiResponse(responseCode = "403", description = "Forbidden", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }),
			@ApiResponse(responseCode = "404", description = "Transaction not found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }),
			@ApiResponse(responseCode = "409", description = "Conflict", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }) })
	ResponseEntity<SuccessResponse> deleteRole(@Positive @NotNull @Parameter(description = "Role id") Long id);
}
