package com.company.Personalmgmt.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionController extends ResponseEntityExceptionHandler {
	
	private static final org.slf4j.Logger log = LoggerFactory.getLogger(GlobalExceptionController.class);

	@ExceptionHandler(InvalidDataException.class)
	public ResponseEntity<?> invaliddata(InvalidDataException ex, WebRequest req) {

		ErrorMessage ErrorMessage = new ErrorMessage(ex.getStatus(), ex.getStatusDesc());
		
		log.error("Exception occured");

		return new ResponseEntity<>(ErrorMessage, HttpStatus.ACCEPTED);
	}
	
	 @ExceptionHandler(NoDataFoundException.class)
	    public ResponseEntity<Object> handleNodataFoundException(
	        NoDataFoundException ex, WebRequest request) {

	        Map<String, Object> body = new LinkedHashMap<>();
	        body.put("timestamp", LocalDateTime.now());
	        body.put("message", "No Data found");

	        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	    }
	
}
