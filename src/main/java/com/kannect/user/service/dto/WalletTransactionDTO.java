package com.kannect.user.service.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WalletTransactionDTO {

	private Long id;
	private Long senderId;
	@NotNull(message = "Receiver ID must not be null.")
	private Long receiverId;
	@NotNull(message = "Amount must not be null.")
	private Integer amount;
	private Long moduleId;
	private String type;
	private String description;
	private LocalDateTime date;
	private Long specificId;
}
