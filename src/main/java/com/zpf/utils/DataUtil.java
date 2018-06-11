package com.zpf.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.lang.StringUtils;

/**
 * @author zhangpengfei e-mail:pengfei19890227@163.com
 * @version 创建时间：2017年8月27日 下午7:21:27 类说明
 */
public class DataUtil {
	private static final SimpleDateFormat sdf_timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final SimpleDateFormat sdf_date = new SimpleDateFormat("yyyy-MM-dd");
	private static final SimpleDateFormat sdf_time = new SimpleDateFormat("HH:mm:ss");

	public static String convertSqlDataToString(Object o) {
		String result = null;
		if (null == o) {
			return null;
		}
		if (o instanceof String || o instanceof Byte || o instanceof Short || o instanceof Integer || o instanceof Long
				|| o instanceof Character || o instanceof Boolean) {
			result = String.valueOf(o);
		} else if (o instanceof Float || o instanceof Double) {
			String value = String.valueOf(o + "00");
			value = value.substring(0, value.lastIndexOf(".") + 3);
			result = value;
		} else if (o instanceof BigDecimal) {
			BigDecimal data = (BigDecimal) o;
			int scale = data.scale();
			String ptn = "#0";
			if (scale > 0) {
				ptn = StringUtils.rightPad(ptn + ".", scale + 3, '0');
			}
			result = new DecimalFormat(ptn).format(data);
		} else if (o instanceof java.sql.Date) {
			result = sdf_date.format((java.sql.Date) o);
		} else if (o instanceof java.sql.Time) {
			result = sdf_time.format((java.sql.Time) o);
		} else if (o instanceof java.sql.Timestamp) {
			result = sdf_timestamp.format((java.sql.Timestamp) o);
		} else {
			result = o.toString();
		}
		return result;
	}
}
