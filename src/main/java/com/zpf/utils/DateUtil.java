package com.zpf.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

public class DateUtil {
	public final static String dateFormat = "yyyy.MM.dd HH:mm:ss";// "yyyy-MM-dd";
	public static final long oneHour = 3600000;//60*60*1000

	public static String getMachingCurrentTime() {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(now);
	}

	public static String getMachingCurrentDate() {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(now);

	}

	public static String getFormatDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return date == null ? null : sdf.format(date);
	}

	public static Date getDate(String dateStr) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		if (dateStr == null) {
			return new Date();
		}
		try {
			date = format.parse(dateStr);
		} catch (ParseException e) {
		}
		return date;
	}

	public static Date getShortDate(String dateStr) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Date date = null;
		try {
			date = format.parse(dateStr);
		} catch (ParseException e) {
		}
		return date;
	}

	/**
	 * 交易日期转换为有效日期
	 * 
	 * @param transDate
	 * @return
	 * @throws ParseException
	 */
	public static String transDateToValidDate(String transDate) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		Date d = df.parse(transDate);
		df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(d);
	}

	/**
	 * �����ַ�ת����Ϊ���� simple date parser
	 */
	public static Date parseDate(String yyyymmddhhmmss, String format) {
		if (null == format) {
			throw new IllegalArgumentException("格式化参数为空！");
		}
		SimpleDateFormat formatter = new SimpleDateFormat(format);

		try {
			ParsePosition pos = new ParsePosition(0);
			return formatter.parse(yyyymmddhhmmss, pos);
		} catch (Exception e) {
			throw new IllegalArgumentException("日期格式化错误:" + yyyymmddhhmmss + " " + e);
		}
	}

	public Timestamp parseTimestamp(String yyyymmddhhmmss) {
		try {
			Timestamp stamp = new Timestamp(this.parseDate(yyyymmddhhmmss).getTime());
			return stamp;
		} catch (NullPointerException e) {
			throw new IllegalArgumentException("日期格式化错误!");
		}
	}

	/**
	 * �����ַ�"yyyy.MM.dd HH:mm:ss"ת����Ϊ���� simple date parser
	 * 
	 * @param yyyymmddhhmmss
	 *            date string
	 * @return null if yyyymmddhhmmssΪ�ջ�NULL
	 */
	public static Date parseDate(String yyyymmddhhmmss) {
		if (yyyymmddhhmmss == null || yyyymmddhhmmss.length() == 0)
			return null;
		if (yyyymmddhhmmss.indexOf("-") < 0 && yyyymmddhhmmss.indexOf(".") < 0) {
			throw new IllegalArgumentException("日期格式必须为yyyy\"-\"MM\".\"ddhhmmss");
		}

		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

		try {
			// ��׼���ڸ�ʽ: yyyy.mm.dd hh:mm:ss
			yyyymmddhhmmss = yyyymmddhhmmss.replace('-', '.');
			if (yyyymmddhhmmss.indexOf(":") < 0)
				yyyymmddhhmmss += " 00:00:00";

			ParsePosition pos = new ParsePosition(0);
			Date tempDat = formatter.parse(yyyymmddhhmmss, pos);
			if (tempDat == null) {
				throw new IllegalArgumentException("日期格式化错误!");
			} else
				return tempDat;
		} catch (Exception e) {
			throw new IllegalArgumentException("日期格式化错误!:" + yyyymmddhhmmss + " " + e);
		}
	}

	public static String dateToString(Timestamp date, String format) {
		return dateToString((Date) date, format);
	}

	// modified by fengjs for avoid param being null 03/12/2003
	public static String dateToString(Date date, String format) {
		if (date == null)
			return null;

		SimpleDateFormat formatter = new SimpleDateFormat(format);

		try {
			return formatter.format(date);
		} catch (Exception e) {
			throw new IllegalArgumentException("����ת�����ַ�ʧ��:" + e);
		}

	}

	public static String getORACLEdatestring(Date d) {
		return "to_date('" + dateToString(d, "yyyy.mm.dd hh24:mi:ss") + "','yyyy.mm.dd hh24:mi:ss')";
	}

	public static Timestamp nowTime() {
		return new Timestamp(System.currentTimeMillis());
	}

	/**
	 * ȡ�õ�ǰ���·ݣ�������λ�����ֵ��·�ǰ�油0,��һ�·�01
	 */
	public static String getCurrMonth() {
		GregorianCalendar d = new GregorianCalendar();
		int m = d.get(Calendar.MONTH) + 1;
		if (m < 10) {
			return "0" + m;
		} else {
			return "" + m;
		}
	}

	/**
	 * ��õ�ǰ���꣬ȡ��ĺ���λ��
	 * 
	 * @return
	 */
	public static String getCurrYear() {
		GregorianCalendar d = new GregorianCalendar();
		int y = d.get(Calendar.YEAR) % 100;
		if (y < 10) {
			return "0" + y;
		} else {
			return "" + y;
		}

	}

	public static String moveDay(String forDate, int forMoveDays) {
		if (!chkYMD(forDate)) {
			throw new IllegalArgumentException("���ڸ�ʽ����:" + forDate);
		} else {
			Calendar cal = toCalendar(forDate);
			cal.add(5, forMoveDays);
			return dateToString(cal.getTime(), "yyyy-MM-dd");
		}
	}

	public static String moveMonth(String forDate, int forMoveMonths) {
		if (!chkYMD(forDate)) {
			throw new IllegalArgumentException("���ڸ�ʽ����:" + forDate);
		} else {
			Calendar cal = toCalendar(forDate);
			cal.add(2, forMoveMonths);
			return dateToString(cal.getTime(), "yyyy-MM-dd");
		}
	}

	public static String moveSecond(String forDate, int forMoveSeconds) {
		if (forDate.length() != 19) {
			forDate = DateUtil.toFormat_YYYY_MM_DD_HH_MM_SS(forDate);
		}
		long tt = Timestamp.valueOf(forDate).getTime();
		tt += forMoveSeconds * 1000;
		return DateUtil.dateToString(new Timestamp(tt), "yyyyMMddHHmmss");
	}

	public static boolean chkYMD(String forDate) {
		if (forDate.length() != 10)
			return false;
		if (!forDate.substring(4, 5).equals("-"))
			return false;
		return forDate.substring(7, 8).equals("-");
	}

	public static String getFirstDay(String forDate) {
		String year = String.valueOf(getYear(forDate));
		int m = getMonth(forDate);
		String month = m >= 10 ? "" + m : "0" + m;
		return year + "-" + month + "-01";
	}

	public static String getLastDay(String forDate) {
		String newDate = getNextDay(forDate);
		newDate = moveDay(newDate, -1);
		return newDate;
	}

	public static String getNextDay(String forDate) {
		int year = getYear(forDate);
		int month = getMonth(forDate);
		String newDay = "01";
		if (month == 12) {
			year++;
			month = 1;
		} else {
			month++;
		}
		String newMonth;
		if (String.valueOf(month).length() == 1)
			newMonth = "0" + String.valueOf(month);
		else
			newMonth = String.valueOf(month);
		String newYear = String.valueOf(year);
		return newYear + "-" + newMonth + "-" + newDay;
	}

	public static int getYear(String forDate) {
		return Integer.parseInt(forDate.substring(0, 4));
	}

	public static int getMonth(String forDate) {
		return Integer.parseInt(forDate.substring(5, 7));
	}

	public static int getDay(String forDate) {
		return Integer.parseInt(forDate.substring(8, 10));
	}

	public static int getDayOfWeek(String forDate) {
		Calendar cal = toCalendar(forDate);
		return cal.get(7);
	}

	public static Calendar toCalendar(String forDate) {
		Calendar cal = Calendar.getInstance();
		int year = getYear(forDate);
		int month = getMonth(forDate);
		int day = getDay(forDate);
		cal.set(year, month - 1, day);
		return cal;
	}

	public static Date toDate(String forDate) {
		return toCalendar(forDate).getTime();
	}

	public static Date toDateTime(String forDateTime) {
		try {
			if (forDateTime != null)
				forDateTime = forDateTime.replace('-', '.');
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
			ParsePosition pos = new ParsePosition(0);
			Date d = formatter.parse(forDateTime, pos);
			return d;
		} catch (Exception exception) {
			throw new IllegalArgumentException("���ڸ�ʽ����:" + forDateTime);
		}
	}

	public static long getInterval(String forBeginDate, String forEndDate) {
		long i = Timestamp.valueOf(forBeginDate + " 00:00:00").getTime();
		long j = Timestamp.valueOf(forEndDate + " 00:00:00").getTime();
		return (j - i) / 0x5265c00L;
	}

	public static String moveYear(String forDate, int forMoveYears) {
		forDate = forDate.substring(0, 10);
		if (!chkYMD(forDate)) {
			throw new IllegalArgumentException("���ڸ�ʽ����:" + forDate);
		} else {
			Calendar cal = toCalendar(forDate);
			cal.add(1, forMoveYears);
			return dateToString(cal.getTime(), "yyyy-MM-dd");
		}
	}

	/**
	 * �Ƚ�����
	 * 
	 * @param forStrDate1
	 * @param forStrDate2
	 * @return 0:> 2:= 1:<
	 */
	public static int compDate(String forStrDate1, String forStrDate2) {
		int intYear1 = getYear(forStrDate1);
		int intYear2 = getYear(forStrDate2);
		int intMonth1 = getMonth(forStrDate1);
		int intMonth2 = getMonth(forStrDate2);
		int intDay1 = getDay(forStrDate1);
		int intDay2 = getDay(forStrDate2);
		if (intYear1 > intYear2)
			return 0;
		if (intYear1 < intYear2)
			return 1;
		if (intYear1 == intYear2) {
			if (intMonth1 > intMonth2)
				return 0;
			if (intMonth1 < intMonth2)
				return 1;
			if (intMonth1 == intMonth2) {
				if (intDay1 > intDay2)
					return 0;
				return intDay1 == intDay2 ? 2 : 1;
			}
		}
		return -1;
	}

	public static Date relativeDate(Date forDate, long forDays) {
		if (forDate == null)
			return null;
		if (forDays == 0L)
			return forDate;
		Date dd = (Date) forDate.clone();
		if (forDays > 0L) {
			for (long i = 1L; i <= forDays; i++)
				dd = tomorrow(dd);

			return dd;
		}
		forDays = -1L * forDays;
		for (long i = 1L; i <= forDays; i++)
			dd = yesterday(dd);

		return dd;
	}

	public static Date tomorrow(Date forToday) {
		long start = forToday.getTime() + 0x5265c00L;
		return new Date(start);
	}

	public static Date yesterday(Date forToday) {
		long start = forToday.getTime() - 0x5265c00L;
		return new Date(start);
	}

	public static String dateToChineseExpr(String forDate) {
		if (forDate == null || forDate.length() == 0)
			return "";
		if (forDate.length() < 10)
			throw new IllegalArgumentException("���ڸ�ʽ����:" + forDate);
		String year = forDate.substring(0, 4);
		int mm;
		int dd;
		try {
			mm = (new Integer("1" + forDate.substring(5, 7))).intValue() - 100;
			dd = (new Integer("1" + forDate.substring(8, 10))).intValue() - 100;
		} catch (Exception e) {
			throw new IllegalArgumentException("���ڸ�ʽ����", e);
		}
		StringBuilder out = new StringBuilder(year);
		out.append("��" + mm);
		out.append("��" + dd);
		out.append("��");
		return out.toString();
	}

	/**
	 * ���ڸ�ʽת������ ��Oracle��׼���ڸ�ʽת����14λ��ʽ(YYYYMMDDHHMMSS)
	 * 日期格式化错误!����ڳ���Ӧ������19λ����ʽ��("2004-11-16 20:44:53.x")
	 * 
	 * @param dateObj
	 *            ������Timestamp��String����
	 * @return
	 * @author yangdg
	 */
	public static String toFormat_YYYYMMDDHHMMSS(Object dateObj) {
		if (!(dateObj instanceof Timestamp || dateObj instanceof String)) {
			return null;
		}
		String date = null;
		if (dateObj instanceof Timestamp) {
			date = ((Timestamp) dateObj).toString();
		} else {
			date = (String) dateObj;
		}

		StringBuilder newFormatDate = new StringBuilder();
		if (date == null) {
			return "";
		}
		if (date.length() == 14) {
			return date;
		}
		if (date.length() < 10) {
			return "";
		}
		if (date.length() < 19 && date.length() >= 10) {
			while (date.length() < 19) {
				date = date + "0";
			}
		}

		newFormatDate.append(date.substring(0, 4)); // YYYY
		newFormatDate.append(date.substring(5, 7)); // MM
		newFormatDate.append(date.substring(8, 10)); // DD
		newFormatDate.append(date.substring(11, 13)); // HH
		newFormatDate.append(date.substring(14, 16)); // MM
		newFormatDate.append(date.substring(17, 19)); // SS

		return newFormatDate.toString();
	}

	public static String toFormat_YYYY_MM_DD_HH_MM_SS(Object dateObj) {
		StringBuilder newFormatDate = new StringBuilder();

		String date = toFormat_YYYYMMDDHHMMSS(dateObj);

		newFormatDate.append(date.substring(0, 4)); // YYYY
		newFormatDate.append("-");
		newFormatDate.append(date.substring(4, 6)); // MM
		newFormatDate.append("-");
		newFormatDate.append(date.substring(6, 8)); // DD
		newFormatDate.append(" ");
		newFormatDate.append(date.substring(8, 10)); // HH
		newFormatDate.append(":");
		newFormatDate.append(date.substring(10, 12)); // MM
		newFormatDate.append(":");
		newFormatDate.append(date.substring(12, 14)); // SS

		return newFormatDate.toString();
	}

	/**
	 * ���ڸ�ʽת������ ��Oracle��׼���ڸ�ʽת����6λ��ʽ(YYYYMM)
	 * 日期格式化错误!����ڳ���Ӧ������19λ����ʽ��("2004-11-16 20:44:53.x")
	 * 
	 * @param dateObj
	 * @return
	 * @author yangdg
	 */
	public static String toFormat_YYYYMM(Object dateObj) {
		if (!(dateObj instanceof Timestamp)) {
			return null;
		}
		String date = ((Timestamp) dateObj).toString();
		StringBuilder newFormatDate = new StringBuilder();
		if (date == null) {
			return "";
		}
		if (date.length() < 6) {
			return "";
		}

		newFormatDate.append(date.substring(0, 4)); // YYYY
		newFormatDate.append(date.substring(5, 7)); // MM

		return newFormatDate.toString();
	}

	/**
	 * ��֤�������ڸ�ʽ��"yyyy-MM-dd hh-mm-ss" 19λ
	 * 
	 * @param inputDate
	 * @return
	 * @author YuanHefeng
	 */
	public static boolean verifyDateFormat(String inputDate) {
		int[] small = { 4, 6, 9, 11 };
		int[] big = { 1, 3, 5, 7, 8, 10, 12 };
		if (inputDate.length() != 19) {
			return false;
		}
		try {
			// �����ж�
			int yyyy = Integer.parseInt(inputDate.substring(0, 4));
			int MM = Integer.parseInt(inputDate.substring(5, 7));
			int dd = Integer.parseInt(inputDate.substring(8, 10));
			int hh = Integer.parseInt(inputDate.substring(11, 13));
			int mm = Integer.parseInt(inputDate.substring(14, 16));
			int ss = Integer.parseInt(inputDate.substring(17, 19));
			if (MM < 0 || dd < 0 || hh < 0 || mm < 0 || ss < 0) {
				return false;
			}
			if (yyyy < 1000 || MM > 12 || dd > 31 || hh > 24 || mm > 59 || ss > 59) {
				return false;
			}
			// С��30��
			for (int i = 0; i < small.length; i++) {
				if (small[i] == MM && dd == 31) {
					return false;
				}
			}
			// ��2�µ�����
			GregorianCalendar cal = new GregorianCalendar();
			int dayLimit = 0;
			if (cal.isLeapYear(yyyy)) {
				dayLimit = 29;
			} else {
				dayLimit = 28;
			}
			if (MM == 2) {
				if (dd > dayLimit) {
					return false;
				}
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * ��֤��ʼʱ���Ƿ����ڽ�ֹʱ��
	 * 
	 * @param begin
	 *            yyyy-MM-dd hh-mm-ss
	 * @param end
	 *            yyyy-MM-dd hh-mm-ss
	 * @return
	 * @author YuanHefeng
	 */
	public static boolean begBeforeEnd(String begin, String end) {
		// ������֤���ڸ�ʽ�Ƿ�Ϸ�
		if (!verifyDateFormat(begin) || !verifyDateFormat(end)) {
			return false;
		}
		// �Ϸ�������¿�֮����Date�ķ�������֤
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
		try {
			Date begDate = formatter.parse(begin);
			Date endDate = formatter.parse(end);
			if (begDate.before(endDate)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * ���ڸ�ʽת������ ��Oracle��׼���ڸ�ʽת����8λ��ʽ(YYYYMMDD)
	 * 日期格式化错误!����ڳ���Ӧ������19λ����ʽ��("2004-11-16 20:44:53.x")
	 * 
	 * @param dateObj
	 * @return
	 * @author yangdg
	 */
	public static String toFormat_YYYYMMDD(Object dateObj) {
		if (!(dateObj instanceof Timestamp)) {
			return null;
		}
		String date = ((Timestamp) dateObj).toString();
		StringBuilder newFormatDate = new StringBuilder();
		if (date == null) {
			return "";
		}
		if (date.length() < 10) {
			return "";
		}

		newFormatDate.append(date.substring(0, 4)); // YYYY
		newFormatDate.append(date.substring(5, 7)); // MM
		newFormatDate.append(date.substring(8, 10)); // DD

		return newFormatDate.toString();
	}

	/**
	 * ����date1��date2���ĺ�����
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long getDiffMilliSecond(Date date1, Date date2) {
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(date1);
		long t1 = c.getTimeInMillis();
		c.setTime(date2);
		long t2 = c.getTimeInMillis();
		if (date1.before(date2))
			return t2 - t1;
		else
			return t1 - t2;
	}

	public static Date getDate(String year, String month, String date, String hour, String minute, String second) {
		GregorianCalendar c = new GregorianCalendar();
		c.set(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(date), Integer.parseInt(hour),
				Integer.parseInt(minute), Integer.parseInt(second));
		return c.getTime();
	}

	public static Date getDate(int year, int month, int date, int hour, int minute, int second) {
		GregorianCalendar c = new GregorianCalendar();
		c.set(year, month, date, hour, minute, second);
		return c.getTime();
	}

	public static String getDateString(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
		String datestr = formatter.format(date).trim();
		return datestr;
	}

	public static String getMonthLastDay(String year, String month) {
		try {
			if (month.length() == 1) {
				month = "0" + month;
			}
			String str = year + "-" + month + "-" + "01";
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			cal.setTime(df.parse(str));

			String day = String.valueOf(cal.getActualMaximum(Calendar.DAY_OF_MONTH));
			if (day.length() == 1) {
				day = "0" + day;
			}
			return year + "-" + month + "-" + day;
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}

	public static String toFormatFormDate_YYYYMMDD(String date) {
		StringBuilder newFormatDate = new StringBuilder();
		if (date == null) {
			return "";
		}
		if (date.length() < 10) {
			return "";
		}

		newFormatDate.append(date.substring(0, 4)); // YYYY
		newFormatDate.append(date.substring(5, 7)); // MM
		newFormatDate.append(date.substring(8, 10)); // DD

		return newFormatDate.toString();
	}

	public static String toFormatFormDate(String date) {
		StringBuilder newFormatDate = new StringBuilder();
		if (date == null) {
			return "";
		}
		if (date.length() < 10) {
			return "";
		}

		newFormatDate.append(date.substring(0, 4)); // YYYY
		newFormatDate.append("-");
		newFormatDate.append(date.substring(5, 7)); // MM
		newFormatDate.append("-");
		newFormatDate.append(date.substring(8, 10)); // DD

		return newFormatDate.toString();
	}

	// ***********start add method by fudandan 2014-01-02 14:40****************

	public static boolean isSameDay(Calendar cal1, Calendar cal2) {
		if (cal1 == null || cal2 == null)
			return false;
		return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
				&& cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
	}

	public static boolean isSameDay(Date date1, Date date2) {
		if (date1 == null || date2 == null)
			return false;
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		return isSameDay(cal1, cal2);
	}

	public static boolean isSameInstant(Date date1, Date date2) {
		if (date1 == null || date2 == null)
			return false;
		return date1.getTime() == date2.getTime();
	}

	public static boolean isSameInstant(Calendar cal1, Calendar cal2) {
		if (cal1 == null || cal2 == null)
			return false;
		return cal1.getTime().getTime() == cal2.getTime().getTime();
	}

	public static Date addYears(Date date, int amount) {
		return add(date, Calendar.YEAR, amount);
	}

	public static Date addMonths(Date date, int amount) {
		return add(date, Calendar.MONTH, amount);
	}

	public static Date addWeeks(Date date, int amount) {
		return add(date, Calendar.WEEK_OF_YEAR, amount);
	}

	public static Date addDays(Date date, int amount) {
		return add(date, Calendar.DAY_OF_MONTH, amount);
	}

	public static Date addHours(Date date, int amount) {
		return add(date, Calendar.HOUR_OF_DAY, amount);
	}

	public static Date addMinutes(Date date, int amount) {
		return add(date, Calendar.MINUTE, amount);
	}

	public static Date addSeconds(Date date, int amount) {
		return add(date, Calendar.SECOND, amount);
	}

	public static Date addMilliseconds(Date date, int amount) {
		return add(date, Calendar.MILLISECOND, amount);
	}

	public static Date add(Date date, int calendarField, int amount) {
		if (date == null)
			throw new IllegalArgumentException("日期不能为空");

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(calendarField, amount);
		return c.getTime();
	}

	public static String today() {
		return today("yyyy-MM-dd");
	}

	public static String now() {
		return today("yyyy-MM-dd HH:mm:ss SSS");
	}

	public static String today(String pattern) {
		if (pattern == null)
			throw new IllegalArgumentException("日期格式不能为空");
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String dt = sdf.format(new Date());
		return dt;
	}

	public static Date parseDate2(String src) {
		return parse(src, "yyyy-MM-dd");
	}

	public static Date parseDatetime(String src) {
		return parse(src, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 最大限度的解析日期:FIXME:完善
	 * 
	 * @param src
	 * @return
	 */
	public static Date parse(String src) {
		if (!StringUtils.hasText(src))
			return null;
		try {
			String tmp = StringUtils.delete(src, "-");
			tmp = StringUtils.delete(tmp, ".");
			tmp = StringUtils.delete(tmp, "/");
			tmp = StringUtils.delete(tmp, " ");
			String pattern = "yyyyMMdd";
			if (tmp.length() != 8)
				pattern = "yyyyMd";
			return new SimpleDateFormat(pattern).parse(tmp);
		} catch (ParseException ex) {
		}
		try {
			return parseDatetime(src);
		} catch (Exception ex) {
		}
		throw new IllegalArgumentException("实在猜不出的日期格式,请指定格式掩码");
	}

	public static Date parse(String src, String pattern) {
		if (src == null || "".equals(src))
			return null;
		if (!StringUtils.hasText(pattern))
			return parse(src);
		try {
			return new SimpleDateFormat(pattern).parse(src);
		} catch (ParseException ex) {
			throw new IllegalArgumentException("日期格式转换出错,src=" + src + ",pattern=" + pattern);
		}
	}

	public static String formatDate(Date src) {
		return format(src, "yyyy-MM-dd");
	}

	public static String formatDatetime(Date src) {
		return format(src, "yyyy-MM-dd HH:mm:ss");
	}

	public static String format(Date src, String pattern) {
		if (pattern == null)
			throw new IllegalArgumentException("日期格式不能为空");
		if (src == null)
			return null;
		return new SimpleDateFormat(pattern).format(src);
	}

	public static int getLastDayOfMonth(int y, int m) {
		boolean IsLeapYear = (y % 4 == 0) && (y % 100 != 0) || (y % 400 == 0);
		int days = 0;
		switch (m) {
		case 2:
			if (IsLeapYear) {
				days = 29;
			} else
				days = 28;
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			days = 30;
			break;
		default:
			days = 31;
			break;
		}
		return days;
	}

	public static Date lastMonthEnd(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Date re = getMonthEnd(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) - 1);
		return re;
	}

	public static Date lastMonthEnd() {
		return lastMonthEnd(new Date());
	}

	public static Date getMonthEnd(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		// month 从0开始。
		cal.set(Calendar.DAY_OF_MONTH, getLastDayOfMonth(year, month + 1));
		return cal.getTime();
	}

	public static Date toMonthEnd(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		// month 从0开始。
		cal.set(Calendar.DAY_OF_MONTH, getLastDayOfMonth(year, month + 1));
		return cal.getTime();
	}

	/**
	 * 中文星期几
	 * 
	 * @return
	 */
	public static String getCnWeekDay() {
		return getCnWeekDay(new Date());
	}

	/**
	 * 中文星期几
	 * 
	 * @return
	 */
	public static String getCnWeekDay(Date date) {
		String[] weekDays = { "日", "一", "二", "三", "四", "五", "六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		return "星期" + weekDays[w];
	}

	/**
	 * 取最大时间（到秒）
	 */
	public static Date getMaxDateTime() {
		return parseDatetime("9999-12-31 23:59:59");
	}

	/**
	 * 取最大日期
	 */
	public static Date getMaxDate() {
		return parseDate2("9999-12-31");
	}

	public static List monthIn(Date min, Date max) {
		List months = new Vector();
		Date d = min;
		while (d.getTime() < max.getTime()) {
			months.add(d);
			d = DateUtil.addMonths(d, 1);
		}
		months.add(max);
		return months;
	}

	// ***********end add method by fudandan 2014-01-02 14:40****************
	/**
	 * 校验日期格式
	 * 
	 * @Author adtec-maohuafeng
	 * @since 2014-2-11 下午4:58:28
	 * @version 1.0
	 * @param date
	 *            目标日期字符
	 * @param regular
	 *            正则表达式
	 * @return boolean
	 */
	public static boolean checkDateFormat(String date, String regular) {
		// 正则表达式
		Pattern patternDate = Pattern.compile(regular);
		Matcher matcher = patternDate.matcher(date);
		if (!matcher.matches()) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * string 转换成date
	 * 
	 * @param date
	 * @param format
	 * @return
	 * @throws ParseException 
	 */
	public static Date stringToDate(String date, String format) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date dateResult = null;

		try {
			dateResult = sdf.parse(date);
		} catch (ParseException e) {
			throw e;
		}
		return dateResult;
	}

	/**
	 * 8位时间进行10位转换
	 * 
	 * @param data
	 * @return
	 * @throws ParseException 
	 */
	public static Date stringToDate(String data) throws ParseException {
		StringBuffer str = new StringBuffer();
		if (data.length() == 8) {
			str.append(data.substring(0, 4));
			str.append("-");
			str.append(data.substring(4, 6));
			str.append("-");
			str.append(data.substring(6, 8));
			return stringToDate(str.toString(), "yyyy-mm-dd");
		} else if (data.length() == 10) {
			return stringToDate(data, "yyyy-mm-dd");
		}
		return null;
	}

	/**
	 * 获取年月的最后一天,返回天
	 * 
	 * @Author adtec-maohuafeng
	 * @since 2015-1-17 下午5:54:59
	 * @version 1.0
	 * @param year
	 * @param month
	 * @return
	 */
	public static int getLastDay(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		return lastDay;
	}

	/**
	 * 获取年月的最后一天,返回日期
	 * 
	 * @Author adtec-maohuafeng
	 * @since 2015-1-17 下午5:54:59
	 * @version 1.0
	 * @param year
	 * @param month
	 * @return
	 */
	public static String getLastDay(int year, int month, String format) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

		return new SimpleDateFormat(format).format(calendar.getTime());
	}

	/**
	 * 根据当前时间和周期，计算下次或上次开始时间
	 * 
	 * @author zhangpengfei
	 * @since 2016-02-17
	 * @version 1.0
	 * 
	 * @param startDate
	 *            开始日期，格式为:yyyy-MM-dd HH:mm:ss
	 * @param cyclePriod
	 *            重复间隔,重复间隔>0时，计算的结果为下次开始时间；重复间隔<0时，计算的结果为上次开始时间
	 * @param priodType
	 *            周期类型，y=年,M=月,d=日,w=周,h=时,m=分,s=秒
	 */
	public static String nextOrBeforPriodTime(String startDate, int cyclePriod, String priodType) {

		String returnValue = "";
		Date date = DateUtil.parseDate(startDate);
		if (null == date) {
			return returnValue;
		}

		Date returnDate = null;
		if ("y".equals(priodType)) { // 年
			returnDate = DateUtil.addYears(date, cyclePriod);
		} else if ("M".equals(priodType)) { // 月
			returnDate = DateUtil.addMonths(date, cyclePriod);
		} else if ("d".equals(priodType)) { // 日
			returnDate = DateUtil.addDays(date, cyclePriod);
		} else if ("w".equals(priodType)) { // 周
			returnDate = DateUtil.addWeeks(date, cyclePriod);
		} else if ("h".equals(priodType)) { // 时
			returnDate = DateUtil.addHours(date, cyclePriod);
		} else if ("m".equals(priodType)) { // 分
			returnDate = DateUtil.addMinutes(date, cyclePriod);
		} else if ("s".equals(priodType)) { // 秒
			returnDate = DateUtil.addSeconds(date, cyclePriod);
		}

		returnValue = DateUtil.getDateString(returnDate);
		return returnValue;
	}
	
	public static long getDiffHours(Date start, Date end) {
	       
        long diff = end.getTime() - start.getTime();
        long dffHours = diff/DateUtil.oneHour;

        return dffHours;
    }
}
