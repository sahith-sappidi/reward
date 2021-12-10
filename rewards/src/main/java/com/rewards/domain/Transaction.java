package com.rewards.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.rewards.constants.RewardsConstants;

import lombok.Data;

@Entity
@Table(name = "TRANSACTION")
@Data
public class Transaction {

	@Id
	@Column(name = "trnsctn_id")
	@NotNull(message = RewardsConstants.ERROR_MESSAGE_TRANSACTION_ID)
	private Integer transactionId;
	
	@Column(name = "cust_name")
	@NotEmpty(message = RewardsConstants.ERROR_MESSAGE_CUSTOMER_NAME)
	private String customerName;
	
	@Column(name = "amount")
	@NotNull(message = RewardsConstants.ERROR_MESSAGE_AMOUNT)
	private Integer amount;
	
	@Column(name = "trnsctn_date")
	@NotEmpty(message = RewardsConstants.ERROR_MESSAGE_DATE)
	private String transactionDate;
	
}
