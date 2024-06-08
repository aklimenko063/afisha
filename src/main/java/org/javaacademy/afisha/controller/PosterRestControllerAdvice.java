package org.javaacademy.afisha.controller;

import org.javaacademy.afisha.exeption.TicketNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;

@RestControllerAdvice
public class PosterRestControllerAdvice {

	@ExceptionHandler(TicketNotFoundException.class)
	public ResponseEntity<String> handleTickedNotFoundException(TicketNotFoundException e) {
		return ResponseEntity.status(NOT_ACCEPTABLE)
				.body(e.getMessage());
	}

}
