package com.dailystudio.app.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarUtils {

	public static final String FORMAT_TEPML_TIME = "HH:mm:ss";
	public static final String FORMAT_TEPML_DATETIME = "yyyy-MM-dd HH:mm:ss:SSS";
	
	public static final long SECOND_IN_MILLIS = 1000;
	
	public static final long MINUTE_IN_MILLIS = SECOND_IN_MILLIS * 60;
	
	public static final long HOUR_IN_MILLIS = MINUTE_IN_MILLIS * 60;
	
	public static final long DAY_IN_MILLIS = HOUR_IN_MILLIS * 24;
	
	public static final long WEEK_IN_MILLIS = DAY_IN_MILLIS * 7;
	
	public static final long YEAR_IN_MILLIS = WEEK_IN_MILLIS * 52;

	private final static Calendar sCalendar;

	static {
		sCalendar = Calendar.getInstance();
		if (sCalendar != null) {
			sCalendar.setFirstDayOfWeek(Calendar.MONDAY);
		}
	}
	
	public static final long getStartOfDay (long mills) {
		if (sCalendar == null) {
			return mills;
		}
		
		sCalendar.setTimeInMillis(mills);
		sCalendar.set(Calendar.HOUR_OF_DAY, 0);
		sCalendar.set(Calendar.MINUTE, 0);
		sCalendar.set(Calendar.SECOND, 0);
		sCalendar.set(Calendar.MILLISECOND, 0);
		
		return sCalendar.getTimeInMillis();
	}
	
	public static final long getEndOfDay (long mills) {
		if (sCalendar == null) {
			return mills;
		}
		
		sCalendar.setTimeInMillis(mills);
		sCalendar.set(Calendar.HOUR_OF_DAY, 23);
		sCalendar.set(Calendar.MINUTE, 59);
		sCalendar.set(Calendar.SECOND, 59);
		sCalendar.set(Calendar.MILLISECOND, 999);
		
		return sCalendar.getTimeInMillis();
	}
	
	public static final long getStartOfWeek (long mills) {
		if (sCalendar == null) {
			return mills;
		}
		
		sCalendar.setFirstDayOfWeek(Calendar.MONDAY);
		sCalendar.setTimeInMillis(mills);
	
		sCalendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		sCalendar.set(Calendar.HOUR_OF_DAY, 0);
		sCalendar.set(Calendar.MINUTE, 0);
		sCalendar.set(Calendar.SECOND, 0);
		sCalendar.set(Calendar.MILLISECOND, 0);
		
		return sCalendar.getTimeInMillis();
	}
	
	public static final long getEndOfWeek (long mills) {
		if (sCalendar == null) {
			return mills;
		}
		
		final long startMillis = getStartOfWeek(mills);
		
		sCalendar.setTimeInMillis(startMillis);
		sCalendar.add(Calendar.DAY_OF_WEEK, 6);
		sCalendar.set(Calendar.HOUR_OF_DAY, 23);
		sCalendar.set(Calendar.MINUTE, 59);
		sCalendar.set(Calendar.SECOND, 59);
		sCalendar.set(Calendar.MILLISECOND, 999);
		
		return sCalendar.getTimeInMillis();
	}
	
	public static final long getStartOfMonth (long mills) {
		if (sCalendar == null) {
			return mills;
		}
		
		sCalendar.setTimeInMillis(mills);

		sCalendar.set(Calendar.DAY_OF_MONTH, 1);
		sCalendar.set(Calendar.HOUR_OF_DAY, 0);
		sCalendar.set(Calendar.MINUTE, 0);
		sCalendar.set(Calendar.SECOND, 0);
		sCalendar.set(Calendar.MILLISECOND, 0);
		
		return sCalendar.getTimeInMillis();
	}
	
	public static final long getEndOfMonth (long mills) {
		if (sCalendar == null) {
			return mills;
		}
		
		sCalendar.setTimeInMillis(mills);

		sCalendar.set(Calendar.DAY_OF_MONTH, 1);
		sCalendar.add(Calendar.MONTH, 1);
		sCalendar.add(Calendar.DAY_OF_MONTH, -1);
		
		sCalendar.set(Calendar.HOUR_OF_DAY, 23);
		sCalendar.set(Calendar.MINUTE, 59);
		sCalendar.set(Calendar.SECOND, 59);
		sCalendar.set(Calendar.MILLISECOND, 999);
		
		return sCalendar.getTimeInMillis();
	}

	public static final long getStartOfYear (long mills) {
		if (sCalendar == null) {
			return mills;
		}
		
		sCalendar.setTimeInMillis(mills);

		sCalendar.set(Calendar.MONTH, Calendar.JANUARY);
		sCalendar.set(Calendar.DAY_OF_MONTH, 1);
		sCalendar.set(Calendar.HOUR_OF_DAY, 0);
		sCalendar.set(Calendar.MINUTE, 0);
		sCalendar.set(Calendar.SECOND, 0);
		sCalendar.set(Calendar.MILLISECOND, 0);
		
		return sCalendar.getTimeInMillis();
	}
	
	public static final long getEndOfYear (long mills) {
		if (sCalendar == null) {
			return mills;
		}
		
		sCalendar.setTimeInMillis(mills);

		sCalendar.set(Calendar.MONTH, Calendar.DECEMBER);
		sCalendar.set(Calendar.DAY_OF_MONTH, 31);
		sCalendar.set(Calendar.HOUR_OF_DAY, 23);
		sCalendar.set(Calendar.MINUTE, 59);
		sCalendar.set(Calendar.SECOND, 59);
		sCalendar.set(Calendar.MILLISECOND, 999);
		
		return sCalendar.getTimeInMillis();
	}

	public static final int getYear (long mills) {
		if (sCalendar == null) {
			return -1;
		}
		
		sCalendar.setTimeInMillis(mills);
		
		return sCalendar.get(Calendar.YEAR);
	}

	public static final int getMonth (long mills) {
		if (sCalendar == null) {
			return -1;
		}
		
		sCalendar.setTimeInMillis(mills);
		
		return sCalendar.get(Calendar.MONTH);
	}

	public static final int getWeek (long mills) {
		if (sCalendar == null) {
			return -1;
		}
		
		sCalendar.setFirstDayOfWeek(Calendar.MONDAY);
		sCalendar.setTimeInMillis(mills);
		
		return sCalendar.get(Calendar.WEEK_OF_YEAR);
	}

	public static final int getDay (long mills) {
		if (sCalendar == null) {
			return -1;
		}
		
		sCalendar.setTimeInMillis(mills);
		return sCalendar.get(Calendar.DAY_OF_MONTH);
	}

	public static final int getWeekDay (long mills) {
		if (sCalendar == null) {
			return -1;
		}
		
		sCalendar.setTimeInMillis(mills);
//		sCalendar.setFirstDayOfWeek(Calendar.MONDAY);
		
		int day = sCalendar.get(Calendar.DAY_OF_WEEK);
		day = (day == 1 ? 7 : day - 1);
		
		return day;
	}

	public static final int getHour (long mills) {
		if (sCalendar == null) {
			return -1;
		}
		
		sCalendar.setTimeInMillis(mills);
		
		return sCalendar.get(Calendar.HOUR_OF_DAY);
	}
	
	public static final int getMinute (long mills) {
		if (sCalendar == null) {
			return -1;
		}
		
		sCalendar.setTimeInMillis(mills);
		
		return sCalendar.get(Calendar.MINUTE);
	}
	
	public static final int getSecond (long mills) {
		if (sCalendar == null) {
			return -1;
		}
		
		sCalendar.setTimeInMillis(mills);
		
		return sCalendar.get(Calendar.SECOND);
	}
	
	public static final boolean isCurrentDay(long time) {
		final long now = System.currentTimeMillis();
		
		return (getStartOfDay(time) == getStartOfDay(now));
	}

	public static final boolean isCurrentWeek(long time) {
		final long now = System.currentTimeMillis();
		
		return (getStartOfWeek(time) == getStartOfWeek(now));
	}

	public static final boolean isCurrentMonth(long time) {
		final long now = System.currentTimeMillis();
		
		return (getStartOfMonth(time) == getStartOfMonth(now));
	}

	public static final boolean isCurrentYear(long time) {
		final long now = System.currentTimeMillis();
		
		return (getStartOfYear(time) == getStartOfYear(now));
	}

	public static final boolean isInRange(long time, long start, long end) {
		return (time >= start && time <= end);
	}
	
	public static String durationToReadableString(long duration) {
		String hourLabel = "h";
		String minLabel = "\"";
		String secLabel = "\'";
		
		long sec = duration / CalendarUtils.SECOND_IN_MILLIS;
		long min = duration / CalendarUtils.MINUTE_IN_MILLIS;
		long hour = duration / CalendarUtils.HOUR_IN_MILLIS;
		
		return String.format("%d%s %02d%s %02d%s %03d",
				hour, hourLabel,
				min % 60, minLabel,
				sec % 60, secLabel,
				duration % 1000);
	}
	
	public static String timeToReadableString(long time, 
			boolean hasDate, boolean hasTime) {
		StringBuilder builder = new StringBuilder();
		
		if (hasDate) {
			builder.append("yyyy/MM/dd ");
		}
		
		if (hasTime) {
			builder.append("hh:mm:ss:SSS aa");
		}

		String format = builder.toString();
		if (format == null) {
			return null;
		}
		
		format = format.trim();
		
		SimpleDateFormat formater = new SimpleDateFormat(format);
		
		return formater.format(time);
	}

	public static String timeToReadableString(long time) {
		return timeToReadableString(time, true, true);
	}

	public static String timeToReadableStringWithoutTime(long time) {
		return timeToReadableString(time, true, false);
	}

	public static String timeToReadableStringWithoutDate(long time) {
		return timeToReadableString(time, false, true);
	}

}
