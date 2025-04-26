package com.kannect.user.service.masters.interfaces;

import org.springframework.http.ResponseEntity;

import com.kannect.user.service.dto.WalletTransactionDTO;
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
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


@Tag(name = "Wallet Transaction", description = "Wallet Transaction Api")
public interface IWalletTransactionController {

	@Operation(summary = "Create Wallet Transaction", description = "Create Wallet Transaction")
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
	ResponseEntity<SuccessResponse> createTransaction(@Valid @NotNull @Parameter(description = "Transaction details")WalletTransactionDTO walletTransactionDTO) throws RequestValidationFailedException;

	@Operation(summary = "Get Wallet Transactions", description = "Get Wallet Transaction")
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
	ResponseEntity<SuccessResponse> getTransactions(@Positive @NotNull @Parameter(description = "Receiver user id")Long receiverId);

}
