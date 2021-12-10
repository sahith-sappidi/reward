package com.rewards.service;

import java.util.List;
import java.util.Map;

import com.rewards.domain.Transaction;

public interface RewardsService {

	Map<String, Map<String, Integer>> getRewardPoints();

	void saveTransactions(List<Transaction> transactionsData);

}
