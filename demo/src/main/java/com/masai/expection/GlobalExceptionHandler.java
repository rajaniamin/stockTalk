package com.masai.expection;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

public class GlobalExceptionHandler {
	@ExceptionHandler(CandleClassException.class)
	public ResponseEntity<MyErrorDetails> CandleClassExceptionHandler(CandleClassException cx, WebRequest req) {
		
		MyErrorDetails error = new MyErrorDetails();
		error.setMessage(cx.getMessage());
		error.setDescription(req.getDescription(false));
		error.setTimestamp(LocalDateTime.now());
		ResponseEntity<MyErrorDetails> rs = new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		return rs;
	}
	@ExceptionHandler(IOException.class)
	public ResponseEntity<MyErrorDetails> IOExceptionHandler(IOException ex, WebRequest req){
		
		MyErrorDetails error = new MyErrorDetails();
		error.setMessage(ex.getMessage());
		error.setDescription(req.getDescription(false));
		error.setTimestamp(LocalDateTime.now());
		
		return new ResponseEntity<>(error, HttpStatus.BAD_GATEWAY);
		
	}
	@ExceptionHandler(InputNotFound.class)
	public ResponseEntity<MyErrorDetails> InputNotFoundHandler(InputNotFound ex, WebRequest req){
		
		MyErrorDetails error = new MyErrorDetails();
		error.setMessage(ex.getMessage());
		error.setDescription(req.getDescription(false));
		error.setTimestamp(LocalDateTime.now());
		
		return new ResponseEntity<>(error, HttpStatus.BAD_GATEWAY);
		
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> ExceptionHandler(Exception ex, WebRequest req) {
		
		MyErrorDetails error = new MyErrorDetails();
		error.setMessage(ex.getMessage());
		error.setDescription(req.getDescription(false));
		error.setTimestamp(LocalDateTime.now());
		ResponseEntity<MyErrorDetails> rs = new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		return rs;
	}
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<MyErrorDetails> NoHandlerFoundExceptionHandler(NoHandlerFoundException nf, WebRequest req) {

		MyErrorDetails error = new MyErrorDetails();
		error.setMessage(nf.getMessage());
		error.setDescription(req.getDescription(false));
		error.setTimestamp(LocalDateTime.now());
		ResponseEntity<MyErrorDetails> rs = new ResponseEntity<MyErrorDetails>(error, HttpStatus.BAD_REQUEST);

		return rs;

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorDetails> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException methdArg) {

		MyErrorDetails error = new MyErrorDetails();
		error.setMessage("Validation Error");
		error.setDescription (methdArg.getBindingResult().getFieldError().getDefaultMessage());
		error.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

	}
}
