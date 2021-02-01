package za.co.ashtech.booklog.util;

import org.springframework.http.HttpStatus;

import za.co.ashtech.booklog.model.BookLogApiError;

public class BookLogApiException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String errorCode;
	private String description;
	private BookLogApiError error;
	private HttpStatus httpStatus;
	
	public BookLogApiException(String errorCode, String description,HttpStatus httpStatus) {
		super(description);
		this.errorCode = errorCode;
		this.description = description;
		this.httpStatus = httpStatus;		
		this.error = new BookLogApiError(errorCode, description);
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BookLogApiError getError() {
		return error;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}
