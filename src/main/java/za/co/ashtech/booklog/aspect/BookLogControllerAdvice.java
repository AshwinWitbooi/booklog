package za.co.ashtech.booklog.aspect;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.qos.logback.classic.Logger;
import za.co.ashtech.booklog.model.BookLogApiError;
import za.co.ashtech.booklog.util.BookLogApiException;
import za.co.ashtech.booklog.util.CONSTANTS;

@ControllerAdvice
public class BookLogControllerAdvice {
	
	private static final Logger logger =  (Logger) LoggerFactory.getLogger(BookLogControllerAdvice.class);
	
	@ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
	public ResponseEntity<BookLogApiError> handleHttpMediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException e) {
		
		BookLogApiError error = new BookLogApiError(CONSTANTS.ERC002, e.getMessage());
		
		this.logErrorResponse(error);
		
	    return new ResponseEntity<>(error, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}

	@ExceptionHandler(BookLogApiException.class)
	public ResponseEntity<BookLogApiError> handleBookLogApiException(BookLogApiException e) {
		
		this.logErrorResponse(e.getError());
		
	    return new ResponseEntity<>(e.getError(),e.getHttpStatus());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<BookLogApiError> handleException(Exception e) {
		
		BookLogApiError error = new BookLogApiError(CONSTANTS.ERC003, e.getMessage());
		
		this.logErrorResponse(error);
		
		return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	private void logErrorResponse(BookLogApiError error) {
		ObjectMapper mapper  = new ObjectMapper();
		
		try {
			logger.info(CONSTANTS.APPINFOMARKER,"RESPONSE: "+mapper.writerWithDefaultPrettyPrinter().writeValueAsString(error));
		} catch (JsonProcessingException e) {
			logger.error(CONSTANTS.APPINFOMARKER,e.getMessage(), e);
		}
	}
}
