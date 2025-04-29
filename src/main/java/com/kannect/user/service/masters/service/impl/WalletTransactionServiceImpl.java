package com.kannect.user.service.masters.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kannect.user.service.auth.entity.User;
import com.kannect.user.service.auth.repository.UserRepository;
import com.kannect.user.service.dto.WalletTransactionDTO;
import com.kannect.user.service.dto.mapper.WalletTransactionMapper;
import com.kannect.user.service.exception.RequestValidationFailedException;
import com.kannect.user.service.exception.ResourceNotFoundException;
import com.kannect.user.service.masters.entity.WalletTransaction;
import com.kannect.user.service.masters.repository.WalletTransactionRepository;
import com.kannect.user.service.masters.service.WalletTransactionService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WalletTransactionServiceImpl implements WalletTransactionService{

	private final WalletTransactionRepository walletTransactionRepository;
	
	private final UserRepository userRepository;
	
	private final WalletTransactionMapper walletTransactionMapper;
	
	@Transactional
	@Override
	public WalletTransactionDTO createTransaction(WalletTransactionDTO dto) throws RequestValidationFailedException {
	    // Validate input
	    if (dto.getReceiverId() == null || dto.getAmount() == null || dto.getAmount() == 0) {
	        throw new RequestValidationFailedException("Receiver ID and  amount must be provided.");
	    }

	    // Fetch Receiver
	    User receiver = userRepository.findById(dto.getReceiverId())
	            .orElseThrow(() -> new ResourceNotFoundException("Receiver not found"));

	    // Save Transaction
	    WalletTransaction transaction = WalletTransaction.builder()
	            .senderId(dto.getSenderId())
	            .receiverId(dto.getReceiverId())
	            .amount(dto.getAmount())
	            .moduleId(dto.getModuleId())
	            .type(dto.getType())
	            .description(dto.getDescription())
	            .date(dto.getDate() != null ? dto.getDate() : LocalDateTime.now())
	            .specificId(dto.getSpecificId())
	            .build();
	    
	    transaction =walletTransactionRepository.save(transaction);

	    // Update Receiver's Wallet Balance
	    Integer currentBalance = receiver.getWalletBalance() != null ? receiver.getWalletBalance() : 0;
	    Integer newBalance = currentBalance + dto.getAmount().intValue();  
	    receiver.setWalletBalance(newBalance);
	    userRepository.save(receiver);
	    return walletTransactionMapper.mapToWalletTransactionDTO(transaction);
	    
	}
	
	@Override
	public List<WalletTransactionDTO> getTransactionsByReceiver(Long receiverId) {
	    if (receiverId == null) {
	        throw new IllegalArgumentException("Receiver ID must be provided.");
	    }

	    List<WalletTransaction> transactions = walletTransactionRepository.findByReceiverId(receiverId);

	    return walletTransactionMapper.mapToWalletTransactionDTOs(transactions);
	}
}
