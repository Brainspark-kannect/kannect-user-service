package com.kannect.user.service.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.kannect.user.service.dto.WalletTransactionDTO;
import com.kannect.user.service.masters.entity.WalletTransaction;

@Component
public class WalletTransactionMapper {

	private final ModelMapper modelMapper = new ModelMapper();

	public WalletTransactionDTO mapToWalletTransactionDTO(WalletTransaction walletTransaction) {
		return modelMapper.map(walletTransaction, WalletTransactionDTO.class);
	}

	public List<WalletTransactionDTO> mapToWalletTransactionDTOs(List<WalletTransaction> walletTransactions) {
		List<WalletTransactionDTO> dtos=new ArrayList<>();
		for(WalletTransaction walletTransaction:walletTransactions) {
			dtos.add(mapToWalletTransactionDTO(walletTransaction));
		}
		return null;
	}

	public WalletTransaction map(WalletTransactionDTO dto, WalletTransaction walletTransaction) {
		modelMapper.map(dto, walletTransaction);
		return walletTransaction;		
	}

	public WalletTransaction mapToWalletTransaction(WalletTransactionDTO dto) {
		return modelMapper.map(dto, WalletTransaction.class);
	}
}
