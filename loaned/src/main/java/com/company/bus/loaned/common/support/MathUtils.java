package com.company.bus.loaned.common.support;

import java.math.BigDecimal;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;

public class MathUtils {

	public static String getRandomString(int length)
	{
	String str = "0123456789";
	Random random = new Random();
	StringBuffer sb = new StringBuffer();

	for (int i = 0; i < length; ++i)
	{
	int number = random.nextInt(10);

	sb.append(str.charAt(number));
	}

	return sb.toString();
	}
	
	public static String substractDecimal(String minuend, String subtractor) {
		try {
			if(StringUtils.isBlank(minuend) || StringUtils.isBlank(subtractor))
				return "";
			BigDecimal m = new BigDecimal(minuend);
			BigDecimal s = new BigDecimal(subtractor);
			return String.valueOf(m.subtract(s));
		} catch (Exception e) {
			return "";
		}
	}

}
