package com.zpf.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * HttpRequestUtil 工具
 * 
 */
@SuppressWarnings("unchecked")
public class ObjectCoverUtil {


	

	
	/**
	 * 类型转换
	 * 
	 * @param v
	 *            值
	 * @param type
	 *            要转换的类型
	 * @return type类型的对象,值为v
	 */
	public static Object covert(String v, Class type) {
		if (v == null || type == null)
			return null;
			// String
			if (type.equals(String.class))
				return v;

			// Number...
			String trimmed = v.trim().replaceAll(",", "");
			trimmed = trimmed.replaceAll("\\+", "");
			if (type.equals(int.class) || type.equals(Integer.class))
				return Integer.decode(trimmed);
			if (type.equals(short.class) || type.equals(Short.class))
				return Short.decode(trimmed);
			if (type.equals(long.class) || type.equals(Long.class))
				return Long.decode(trimmed);
			if (type.equals(float.class) || type.equals(Float.class))
				return Float.valueOf(trimmed);
			if (type.equals(double.class) || type.equals(Double.class))
				return Double.valueOf(trimmed);

			// Date
			if (type.equals(Date.class)) {
				// 按顺序试图转换的格式
				String[] patterns = new String[] { "yyyy-MM-dd HH:mm:ss",
						"yyyy-MM-dd HH:mm", "yyyy-MM-dd", "yyyyMMdd" };
				for (int i = 0; i < patterns.length; i++) {
					try {
						// 如果解析不了,抛错误,继续下一个格式解析,知道有错误的或者全部出错.
						return new SimpleDateFormat(patterns[i]).parse(v);
					} catch (ParseException e) {
					}
				}
			}

			if (type.equals(boolean.class) || type.equals(Boolean.class))
				return Boolean.valueOf(v);
		return null;
	}

	
}
