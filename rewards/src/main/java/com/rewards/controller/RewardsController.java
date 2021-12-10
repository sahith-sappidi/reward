package com.rewards.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rewards.domain.Transaction;
import com.rewards.service.RewardsService;

@RestController
@Validated
@RequestMapping("/rewards")
public class RewardsController {

	@Autowired
	private RewardsService rewardsService;

	@PostMapping("/transactions")
	public ResponseEntity<Void> saveTransactions(
			@RequestBody @NotEmpty(message = "Input Transaction list cannot be empty.") List<@Valid Transaction> transactionsData) {

		rewardsService.saveTransactions(transactionsData);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping("/rewardPoints")
	public ResponseEntity<Map<String, Map<String, Integer>>> calculateRewards() {

		Map<String, Map<String, Integer>> response = rewardsService.getRewardPoints();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
