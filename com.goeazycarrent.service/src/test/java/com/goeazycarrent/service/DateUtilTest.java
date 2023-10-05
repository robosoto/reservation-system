package com.goeazycarrent.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.util.Date;

import org.junit.jupiter.api.Test;

import com.goeazycarrent.service.exception.GoEazyException;


public class DateUtilTest {

	DateUtil util=new DateUtil();
	
	@Test
	public void testDateFormat() {
		try {
			Date convertedDateFromString = util.getConvertedDateFromString("2023-10-04 17:16:38", "America/Chicago");
			String stringFromDate = util.getStringFromDate(convertedDateFromString, "America/Chicago");
		
			
			assertEquals("2023-10-04 17:16:38", stringFromDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testConvertDate() {
		try {
			Date convertedDateFromString = util.convertDateByTimezone("Cancun","2023-10-04 17:16:38");
			String stringFromDate = util.getStringFromDate(convertedDateFromString, "America/Chicago");
			assertEquals("2023-10-04 17:16:38", stringFromDate);
		}  catch (GoEazyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
