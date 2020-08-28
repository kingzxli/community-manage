package com.community.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class DataUtils {
	private static final String INTEGER_REX = "^[-\\+]?[\\d]*$";
	private static final String INTEGER_DECIMAL_REX = "^[0-9]+(.[0-9]{1,2})?$";
	private static String REGEX = "^(\\-{0,1})\\d{1,11}(\\.\\d{1,4})?$";
    private static Pattern PATTERN = Pattern.compile(REGEX);
	
	public static boolean isAmount(String amount) {
		if(amount == null) {
			return false;
		}
		return PATTERN.matcher(amount).matches();
	}

	/**
	 * 验证是否数字
	 * 
	 * @author Rex.Tan
	 * @date 2020年6月2日 下午3:18:34
	 * @param str
	 * @return
	 */
	public static boolean isInteger(String str) {
		if (StringUtils.isBlank(str)) {
			return false;
		}
		Pattern pattern = Pattern.compile(INTEGER_REX);

		return pattern.matcher(str).matches();
	}
	
	/**
	 * 验证是否数字,最多2位小数
	 * 
	 * @author Rex.Tan
	 * @date 2020年6月2日 下午3:18:34
	 * @param str
	 * @return
	 */
	public static boolean isIntegerAndDecimal(String str) {
		if (StringUtils.isBlank(str)) {
			return false;
		}
		Pattern pattern = Pattern.compile(INTEGER_DECIMAL_REX);

		return pattern.matcher(str).matches();
	}

	/**
	 * 验证是否为数字1～6
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isInteger2(String str) {
		if (StringUtils.isBlank(str)) {
			return false;
		}
		Pattern pattern = Pattern.compile("^[1-6]$");

		return pattern.matcher(str).matches();
	}

	public static boolean isValidDate(String date,String dateFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		// 输入对象不为空
		if (StringUtils.isNotBlank(date)) {
			try {
				sdf.format(sdf.parse(date));
				return true;
			} catch (ParseException e) {
				return false;
			}
		} else {
			return false;
		}
	}
	
	/**
	    * 判断两个日期间是否超过的年数
	    * @param time1
	    * @param time2
	    * @param numYear
	    * @return
	    */
	    public static Boolean DateCompare(Date time1,Date time2,int numYear) {
	    	Date time3 = add(time1, Calendar.YEAR,numYear);
	    	if(time3.getTime()<= time2.getTime()){
	    		return true;
	    	}
			return false;
	    }
	   /**
	    * 时间加减
	    * @param date
	    * @param calendarField ：Calendar.YEAR/ Calendar.MONTH /Calendar.DAY
	    * @param amount
	    * @return
	    */
	    public static Date add(final Date date, final int calendarField, final int amount) {
	        if (date == null) {
	            return null;
	        }
	        final Calendar c = Calendar.getInstance();
	        c.setTime(date);
	        c.add(calendarField, amount);
	        return c.getTime();
	    }
}
