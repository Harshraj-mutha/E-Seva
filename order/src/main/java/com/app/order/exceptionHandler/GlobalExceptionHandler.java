package com.app.order.exceptionHandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.app.order.dto.ErrorResponseDto;
import com.app.order.exception.InsufficientStockException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponseDto> handleInsufficienStockExceptio(InsufficientStockException ex) {
		ErrorResponseDto body = new ErrorResponseDto(LocalDateTime.now()
				,HttpStatus.BAD_REQUEST.value()
				,"insufficinet Stock"
				,ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
	}

}
