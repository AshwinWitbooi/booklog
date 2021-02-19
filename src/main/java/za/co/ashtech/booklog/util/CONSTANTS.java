package za.co.ashtech.booklog.util;

import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

public class CONSTANTS {
	
	
	/* private constructor class shoould never be initialized */
	private CONSTANTS() {}

	/* Error code and descriptions */
	public static final  String ERC001 = "ERC001";
	public static final String ERC001_DESC = "Error creating book.";
	public static final String ERC002 = "ERC002";
	public static final String ERC003 = "ERC003";
	public static final String ERC004 = "ERC004";
	public static final String ERC004_DESC = "ISBN number already exist.";
	public static final String ERC005 = "ERC005";
	public static final String ERC006 = "ERC006";
	public static final String ERC006_DESC = "Invalid action.";
	public static final String ERC007 = "ERC007";
	public static final String INVALID_ISBN =  "ISBN invalid";
	public static final String APIEC = "APIEC";
	public static final Marker APPINFOMARKER = MarkerFactory.getMarker("APPINFO");
	
	/* Field value format pattern */
	public static final String ISBN_PATTERN = "^\\d{3}\\-\\d{2}\\-\\d{6}\\-\\d{1}$";
}
