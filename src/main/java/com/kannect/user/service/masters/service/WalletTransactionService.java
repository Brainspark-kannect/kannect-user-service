package com.kannect.user.service.masters.service;

import java.util.List;

import com.kannect.user.service.dto.WalletTransactionDTO;
import com.kannect.user.service.exception.RequestValidationFailedException;

public interface WalletTransactionService {

	List<WalletTransactionDTO> getTransactionsByReceiver(Long receiverId);

	WalletTransactionDTO createTransaction(WalletTransactionDTO dto) throws RequestValidationFailedException;

}
