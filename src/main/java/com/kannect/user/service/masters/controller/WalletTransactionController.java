package com.kannect.user.service.masters.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kannect.user.service.dto.WalletTransactionDTO;
import com.kannect.user.service.dto.response.SuccessResponse;
import com.kannect.user.service.exception.RequestValidationFailedException;
import com.kannect.user.service.masters.interfaces.IWalletTransactionController;
import com.kannect.user.service.masters.service.WalletTransactionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/masters/tranctions")
@Validated
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class WalletTransactionController implements IWalletTransactionController {

	private final WalletTransactionService walletTransactionService;

	@Override
	@PostMapping
	public
	ResponseEntity<SuccessResponse> createTransaction(@RequestBody WalletTransactionDTO walletTransactionDTO) throws RequestValidationFailedException {
		return ResponseEntity.ok(new SuccessResponse(HttpStatus.OK.value(), HttpStatus.OK,
				"Transaction created successfully", walletTransactionService.createTransaction(walletTransactionDTO)));

	}
	
	@Override
	@GetMapping("/{receiver_id}")
	public
	ResponseEntity<SuccessResponse> getTransactions(@PathVariable Long receiverId) {
		return ResponseEntity.ok(new SuccessResponse(HttpStatus.OK.value(), HttpStatus.OK,
				"Transaction logs fetched successfully", walletTransactionService.getTransactionsByReceiver(receiverId)));

	}

}
