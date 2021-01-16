package za.co.ashtech.booklog.aspect;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import za.co.ashtech.booklog.model.BookLogApiError;
import za.co.ashtech.booklog.util.BookLogApiException;
import za.co.ashtech.booklog.util.CONSTANTS;

@ControllerAdvice
public class BookLogControllerAdvice {
	
	@ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
	public ResponseEntity<BookLogApiError> handleHttpMediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException e) {
		
	    return new ResponseEntity<>(new BookLogApiError(CONSTANTS.ERC002, e.getMessage()), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}

	@ExceptionHandler(BookLogApiException.class)
	public ResponseEntity<BookLogApiError> handleBookLogApiException(BookLogApiException e) {
	    return new ResponseEntity<>(e.getError(),e.getHttpStatus());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<BookLogApiError> handleException(Exception e) {
		
		return new ResponseEntity<>(new BookLogApiError(CONSTANTS.ERC003, e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
