/**
 * @title DateUtil.java
 * @package com.baizhu.util
 * @projectName VolunteerAPI
 * @date 2014年5月13日
 * @Copyright: 2014 www.byzhu.com Inc. All rights reserved.
 */
package com.springboot.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 日期辅助类.
 * 
 * @author xiachengyun xiachengyun@baizhu.cc
 * @version V1.0
 */
public class DateUtil {
	/**
	 * 当前日期转换为字符串.格式:yyyy-MM-dd HH:mm:ss
	 *
	 * @param date 日期.
	 * @return 日期的字符串.
	 */
	public static String formatDate(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}

	/**
	 * 返回特定格式的日期字符串.eg:yyyy-MM-dd HH:mm:ss
	 *
	 * @param date
	 * @param patten
	 * @return
	 */
	public static String formatDate(Date date, String patten) {
		SimpleDateFormat format = new SimpleDateFormat(patten);
		return format.format(date);
	}

	/**
	 * 当前日期转换为字符串.格式:yyyy-MM-dd
	 *
	 * @param date 日期.
	 * @return 日期的字符串.
	 */
	public static String formatDateWithoutSecond(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
	}

	/**
	 * 当前日期.
	 *
	 * @return
	 */
	public static Timestamp currrentDate() {
		return new Timestamp(System.currentTimeMillis());
	}

	/**
	 * 相隔天数
	 *
	 * @return
	 */
	public static boolean getTimeOut(Date createdDate, Date endDate) {
		Calendar createdCalendar = Calendar.getInstance();
		Calendar endCalendar = Calendar.getInstance();
		createdCalendar.setTime(createdDate);
		endCalendar.setTime(endDate);
		createdCalendar.add(Calendar.YEAR, 1);
		return endCalendar.before(createdCalendar);
	}

	/**
	 * 判断白天黑夜
	 *
	 * @return
	 */

	public static String getDayOrNight(String beginTime, String dateBeginTime, String dateEndTime) {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		try {
			Date startDate = format.parse(beginTime.split(" ")[1]);
			format.applyPattern("HH:mm");
			Date sDate = format.parse(dateBeginTime);
			Date eDate = format.parse(dateEndTime);
			if (sDate.compareTo(startDate) <= 0 && eDate.compareTo(startDate) > 0) {
				return "HOUR";
			} else {
				return "ALL";
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 当前日期转换为字符串.格式:yyyy年MM月dd日 HH时mm分ss秒
	 *
	 * @param date 日期.
	 * @return 日期的字符串.
	 * @throws ParseException
	 */
	public static String formatTimeCH(Date date) {
		DateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
		return format.format(date);
	}

	/**
	 * 将字符串转换为Date类型
	 */
	public static Date formatToDate(String time) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = null;
		try {
			date = format.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static String getDayString(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}

	public static String getHourString(Date date) {
		return new SimpleDateFormat("HH").format(date);
	}

	public static Date HfStrToDate(String str) {
		Calendar current = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = null;
		try {
			date = sdf.parse(str);
			current.setTime(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date;
	}

	/**
	 * 时间段比大小
	 *
	 * @param firstTime
	 * @param secondTime
	 * @return
	 */
	public static int compareTime(String firstTime, String secondTime) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date fDate = format.parse(firstTime);
			Date sDate = format.parse(secondTime);
			if (sDate.compareTo(fDate) < 0) {
				return 1;
			} else {
				return 2;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 判断是否混合收费
	 */
	public static int judgeNd(String beginTime, String endTime, String beginTimee, String endTimee) {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		SimpleDateFormat format3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar ca1 = Calendar.getInstance();
		Calendar ca2 = Calendar.getInstance();
		Calendar calendar = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		Calendar calendar3 = Calendar.getInstance();
		Calendar calendar4 = Calendar.getInstance();
		long timeMinutes = 15 * 60 * 60 * 1000;
		long timeTwoMinutes = 9 * 60 * 60 * 1000;
		try {
			ca1.setTime(format3.parse(beginTime));
			ca2.setTime(format3.parse(endTime));
			calendar.setTime(format.parse(beginTime.split(" ")[1]));
			calendar2.setTime(format.parse(endTime.split(" ")[1]));
			calendar3.setTime(format.parse(beginTimee));
			calendar4.setTime(format.parse(endTimee));
			System.err.println(ca2.getTimeInMillis());
			System.err.println(ca1.getTimeInMillis());
			System.err.println(ca2.getTimeInMillis() - ca1.getTimeInMillis());
			if (calendar.compareTo(calendar3) > 0 && calendar2.compareTo(calendar4) < 0 && ca2.getTimeInMillis() - ca1.getTimeInMillis() <= timeTwoMinutes) {
				return 0;
			}
			if (calendar.compareTo(calendar4) > 0 && ca2.getTimeInMillis() - ca1.getTimeInMillis() <= timeMinutes) {
				return 1;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 3;
	}

	/**
	 * 将字符串转换为Date
	 * format:yyyy-MM-dd HH:mm:ss
	 * @param time
	 * @return
	 */
	public static Date formatToDateM(String time) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = format.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 相隔天数判断
	 *
	 * @param bDate
	 * @param eDate
	 * @return
	 */
	public static boolean xjday(Date bDate, Date eDate, int minDay, int maxDay) {
		return ((eDate.getTime() - bDate.getTime()) / 1000 >= (minDay * 24 * 60 * 60) && (eDate.getTime() - bDate.getTime()) / 1000 <= (maxDay * 24 * 60 * 60));
	}


	/**
	 * 相隔小时判断
	 *
	 * @param bDate
	 * @param eDate
	 * @return
	 */
	public static boolean xjhour(Date bDate, Date eDate, int minHour, int maxHour) {
		return ((eDate.getTime() - bDate.getTime()) / 1000 >= (minHour * 60 * 60) && (eDate.getTime() - bDate.getTime()) / 1000 <= (maxHour * 60 * 60));
	}

	/**
	 * 判断是否能整除小时
	 *
	 * @param bDate
	 * @param eDate
	 * @return
	 */
	public static boolean divideHour(Date bDate, Date eDate) {
		return ((eDate.getTime() - bDate.getTime()) / 1000) % (60 * 60) != 0;
	}

	/**
	 * 判断是否能整除天
	 *
	 * @param bDate
	 * @param eDate
	 * @return
	 */
	public static boolean divideDay(Date bDate, Date eDate) {
		return ((eDate.getTime() - bDate.getTime()) / 1000) % (24 * 60 * 60) != 0;
	}

	/**
	 * 相差几个小时
	 *
	 * @param bDate
	 * @param eDate
	 * @return
	 */
	public static int computerDivideHour(Date bDate, Date eDate) {
		return (int) (((eDate.getTime() - bDate.getTime()) / 1000) / (60 * 60));
	}

	/**
	 * 相差几天
	 *
	 * @param bDate
	 * @param eDate
	 * @return
	 */
	public static int computerDivideDay(Date bDate, Date eDate) {
		return (int) (((eDate.getTime() - bDate.getTime()) / 1000) / (24 * 60 * 60));
	}

	public static Map<String, Integer> timea(Date bDate, Date eDate) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		Integer day = (int) (((bDate.getTime() - eDate.getTime()) / 1000) / (24 * 60 * 60));
		Integer hour = (int) (((bDate.getTime() - eDate.getTime()) / 1000) / (60 * 60)) - (day * 24);
		Integer min = (int) (((bDate.getTime() - eDate.getTime()) / 1000) / (60) - (day * 24 * 60) - (hour * 60));
		Integer sec = (int) (((bDate.getTime() - eDate.getTime()) / 1000) / (60) - (day * 24 * 60 * 60) - (hour * 60 * 60) - (min * 60));
		map.put("day", day);
		map.put("hour", hour);
		map.put("min", min);
		map.put("sec", sec);
		System.err.println(day + "天");
		System.err.println(hour + "小时");
		System.err.println(min + "分钟");
		System.err.println(sec + "秒");
		return map;
	}

	public static String formatDateWithoutDay(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		return format.format(date);
	}
}