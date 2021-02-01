package za.co.ashtech.booklog.util;

import java.util.regex.Pattern;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import ch.qos.logback.classic.Logger;

public class BookLogUtil {

	private static final Logger logger =  (Logger) LoggerFactory.getLogger(BookLogUtil.class); 
	
	public static void validateJsonField(String pattern, String val,String field) throws BookLogApiException{
		
		if(Pattern.matches(pattern, val)) {
			logger.info(field+" value "+val+" is valid");
	      }else {
	    	  throw new BookLogApiException(CONSTANTS.ERC005, field+" value "+val+" is invalid.", HttpStatus.BAD_REQUEST);
	      }
		
	}
}
