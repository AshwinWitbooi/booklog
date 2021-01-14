package za.co.ashtech.booklog.util;

public class BookLogApiException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String errorCode;
	private String description;
	
	public BookLogApiException(String errorCode, String description) {
		super();
		this.errorCode = errorCode;
		this.description = description;
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

}
