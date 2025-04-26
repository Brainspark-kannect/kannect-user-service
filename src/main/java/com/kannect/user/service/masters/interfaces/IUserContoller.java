package com.kannect.user.service.masters.interfaces;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.kannect.user.service.dto.response.ErrorResponse;
import com.kannect.user.service.dto.response.SuccessResponse;
import com.kannect.user.service.exception.RequestValidationFailedException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Tag(name = "User", description = "User Api")
public interface IUserContoller {

	@Operation(summary = "Get User By Id", description = "Get User By Id")
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
	ResponseEntity<SuccessResponse> getUserById(@Positive @NotNull @Parameter(description = "User id") Long id);

	@Operation(summary = "Create User ", description = "Create User")
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
	ResponseEntity<SuccessResponse> registerUser(
			@NotNull @Parameter(description = "User details") String dto,@Parameter(description = "Profile photo") MultipartFile profilePicture)
			throws IOException, RequestValidationFailedException;

	@Operation(summary = "Get Users", description = "Get Users")
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
	ResponseEntity<SuccessResponse> getAllUsers();

	@Operation(summary = "Get User By status", description = "Get User By status")
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
	ResponseEntity<SuccessResponse> getUsersByActivationStatus(
			@NotNull @Parameter(description = "User status") Boolean status);

	@Operation(summary = "Update user", description = "HR or ADMIN can update the user details")
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
	ResponseEntity<SuccessResponse> updateUserByAdminOrHR(
			@Positive @NotNull @Parameter(description = "User id") Long id,
			@NotNull @Parameter(description = "User details") String dto,@Parameter(description = "Profile photo") MultipartFile profilePicture) throws IOException, RequestValidationFailedException;

	@Operation(summary = "Upate User", description = "Update the user details")
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
	ResponseEntity<SuccessResponse> updateEmployeeProfile(
			@Positive @NotNull @Parameter(description = "User id") Long id,
			@NotNull @Parameter(description = "User details") String dto,@Parameter(description = "Profile photo") MultipartFile profilePicture)
			throws IOException, RequestValidationFailedException;

	@Operation(summary = "Delete User By Id", description = "Delete User By Id")
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
	ResponseEntity<SuccessResponse> deleteUser(@Positive @NotNull @Parameter(description = "User id") Long id);

	@Operation(summary = "Activate Users", description = "Activate Users")
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
	ResponseEntity<SuccessResponse> activateUsers(@NotNull @Parameter(description = "User ids") List<Long> userIds);

}
