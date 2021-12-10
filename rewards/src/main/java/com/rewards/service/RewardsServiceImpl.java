package com.rewards.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rewards.constants.RewardsConstants;
import com.rewards.domain.Transaction;
import com.rewards.repository.TransactionRepository;

@Service
public class RewardsServiceImpl implements RewardsService {

	@Autowired
	private TransactionRepository transactionRepository;

	public Map<String, Map<String, Integer>> getRewardPoints() {
		List<Transaction> transactionsData = transactionRepository.findAll();
		Map<String, Map<String, Integer>> rewardsMap = new HashMap<>();

		transactionsData.forEach(transaction -> {
			if (rewardsMap.containsKey(transaction.getCustomerName())) {
				addRewardPoints(rewardsMap, transaction);
			} else {
				addCustomer(rewardsMap, transaction);
			}
		});
		return rewardsMap;
	}

	public void saveTransactions(List<Transaction> transactionsData) {
		transactionsData.forEach(transaction -> transactionRepository.save(transaction));
	}

	private void addRewardPoints(Map<String, Map<String, Integer>> rewardsMap, Transaction transaction) {
		Map<String, Integer> customerMap = rewardsMap.get(transaction.getCustomerName());
		String transactionMonth = getTransactionMonth(transaction.getTransactionDate());

		if (customerMap.containsKey(transactionMonth)) {
			Integer rewards = customerMap.get(transactionMonth);
			customerMap.put(transactionMonth, rewards + calculatedPoints(transaction.getAmount()));
		} else {
			customerMap.put(transactionMonth, calculatedPoints(transaction.getAmount()));
		}

		customerMap.put(RewardsConstants.TOTAL, calculateTotalPoints(customerMap));
		rewardsMap.put(transaction.getCustomerName(), customerMap);
	}

	private void addCustomer(Map<String, Map<String, Integer>> rewardsMap, Transaction transaction) {
		Map<String, Integer> customerMap = new HashMap<>();
		customerMap.put(getTransactionMonth(transaction.getTransactionDate()),
				calculatedPoints(transaction.getAmount()));
		customerMap.put(RewardsConstants.TOTAL, calculatedPoints(transaction.getAmount()));
		rewardsMap.put(transaction.getCustomerName(), customerMap);
	}

	private Integer calculatedPoints(Integer amount) {
		if (amount > RewardsConstants.VALUE_100) {
			return RewardsConstants.VALUE_50 + (amount - RewardsConstants.VALUE_100) * RewardsConstants.VALUE_2;
		} else if (amount > RewardsConstants.VALUE_50) {
			return amount - RewardsConstants.VALUE_50;
		}
		return 0;
	}

	private String getTransactionMonth(String transactionDate) {
		DateFormat format = new SimpleDateFormat(RewardsConstants.DATE_FORMAT);
		try {
			Date date = format.parse(transactionDate);
			LocalDate localdDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			return localdDate.getMonth().toString();
		} catch (ParseException e) {
		}
		return null;
	}

	private Integer calculateTotalPoints(Map<String, Integer> customerMap) {
		Integer totalPoints = 0;
		for (Map.Entry<String, Integer> entry : customerMap.entrySet())
			if (!entry.getKey().equals(RewardsConstants.TOTAL))
				totalPoints = totalPoints + entry.getValue();
		return totalPoints;
	}

}
