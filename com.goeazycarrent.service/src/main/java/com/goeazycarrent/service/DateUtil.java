package com.goeazycarrent.service;

import java.text.ParseException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.goeazycarrent.service.exception.GoEazyException;

public class DateUtil {
	private static final String CST_TIMEZONE = "America/Chicago";
	private static final String CANCUN = "Cancun";
	private static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static String DEFAULT_TIMEZONE = "America/New_York";
	/**
	 * Convert from String to Date using timezone
	 * @param date
	 * @param timezone
	 * @return
	 * @throws ParseException
	 */
	public static Date getConvertedDateFromString(String date,String timezone) throws ParseException {
    	ZoneId myTimeZone = ZoneId.of(timezone);
        ZonedDateTime zdt = ZonedDateTime.parse(date, dateFormat.withZone(myTimeZone));
        return Date.from(zdt.toInstant());
    }

	/**
	 * Convert from Date to String using timezone
	 * @param date
	 * @param timezone
	 * @return
	 */
    public static String getStringFromDate(Date date, String timezone) {
    	ZonedDateTime zoneDateTime = ZonedDateTime.ofInstant(date.toInstant(),ZoneId.of(timezone));
        return dateFormat.format(zoneDateTime);
    }
    
    public static Date convertDateByTimezone(String location,String date) throws GoEazyException {
		String timezone= DEFAULT_TIMEZONE;
		if(CANCUN.equalsIgnoreCase(location) || "Cancún".equalsIgnoreCase(location)){
			timezone = CST_TIMEZONE;
		}
		try {
			return getConvertedDateFromString(date, timezone);
			
		} catch (ParseException e) {
			throw new GoEazyException("Failed to Reserve");
		}

	}
}
