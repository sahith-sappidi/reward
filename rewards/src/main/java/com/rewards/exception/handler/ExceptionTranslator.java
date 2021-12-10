package com.rewards.exception.handler;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.rewards.constants.RewardsConstants;
import com.rewards.exception.model.ErrorResponse;

@ControllerAdvice
public class ExceptionTranslator {

	@ExceptionHandler(value = { ConstraintViolationException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	protected ErrorResponse handleConstraintViolation(ConstraintViolationException exception) {
		List<String> messages = exception.getConstraintViolations().stream().map(ConstraintViolation::getMessage)
				.collect(Collectors.toList());
		return new ErrorResponse(RewardsConstants.INVALID_REQUEST, String.join(",", messages));
	}

	@ExceptionHandler(value = { Exception.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	protected ErrorResponse handleUnexpectedException(Exception exception) {
		String errorId = UUID.randomUUID().toString();
		return new ErrorResponse(RewardsConstants.INTERNAL_ERROR, "Error logged with ID " + errorId);
	}

}
