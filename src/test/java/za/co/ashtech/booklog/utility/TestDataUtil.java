package za.co.ashtech.booklog.utility;

import org.apache.commons.lang3.RandomStringUtils;

public class TestDataUtil {
	
	public static String getIsbn() {
		
		return  RandomStringUtils.randomNumeric(3)+"-"+
				RandomStringUtils.randomNumeric(2)+"-"+	
				RandomStringUtils.randomNumeric(6)+"-"+
				RandomStringUtils.randomNumeric(1);
	}

}
