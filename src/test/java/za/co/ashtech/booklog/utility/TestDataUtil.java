package za.co.ashtech.booklog.utility;

import org.apache.commons.lang3.RandomStringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestDataUtil {
	
	public static String getIsbn() {
		
		return  RandomStringUtils.randomNumeric(3)+"-"+
				RandomStringUtils.randomNumeric(2)+"-"+	
				RandomStringUtils.randomNumeric(6)+"-"+
				RandomStringUtils.randomNumeric(1);
	}
	
	public static String getUsername() {
		return RandomStringUtils.randomAlphabetic(8)+"@test.co.za";
	}
	
	public static String getJSONString(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();		
		
		return objectMapper.writeValueAsString(obj);
	}

}
