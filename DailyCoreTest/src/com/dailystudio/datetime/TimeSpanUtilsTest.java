package com.dailystudio.datetime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.dailystudio.test.ActivityTestCase;
import com.dailystudio.test.Asserts;

public class TimeSpanUtilsTest extends ActivityTestCase {

	private final static String PARSE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	protected long parseDateTime(String dstr) {
		if (dstr == null) {
			return 0l;
		}
		
		Date date = null;
		
		SimpleDateFormat sdf = new SimpleDateFormat(PARSE_FORMAT);
		
		try {
			date = sdf.parse(dstr);
		} catch (ParseException e) {
			e.printStackTrace();
			
			date = null;
		}
		assertNotNull(date);

		return date.getTime();
	}
	
	public void testCalculateErrorHourDistrib() {
		TimeSpanUtils distributor = new TimeSpanUtils();
		
		final long start = parseDateTime("2012-12-12 00:00:00.000");
		final long end = parseDateTime("2010-12-13 00:00:00.000");
		
		long[] actual = null;
		long[] expected = null;
		
		actual = distributor.calculateHourDistribution(start, end);
		assertNull(actual);
		
		actual = distributor.calculateHourDistribution(null, start, end);
		assertNull(actual);
		
		actual = new long[20]; 
		expected = distributor.calculateHourDistribution(actual, start, end);
		assertEquals(expected, actual);
	}

	public void testCalculateOneDayHourDistrib() {
		TimeSpanUtils distributor = new TimeSpanUtils();
		
		final long start = parseDateTime("2012-12-12 00:00:00.000");
		final long end = parseDateTime("2012-12-13 00:00:00.000");
		
		long[] actual = distributor.calculateHourDistribution(start, end);
		long[] expected = {
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 00 ~ Hour 01 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 01 ~ Hour 02 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 02 ~ Hour 03 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 03 ~ Hour 04 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 04 ~ Hour 05 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 05 ~ Hour 06 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 06 ~ Hour 07 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 07 ~ Hour 08 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 08 ~ Hour 09 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 09 ~ Hour 10 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 10 ~ Hour 11 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 11 ~ Hour 12 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 12 ~ Hour 13 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 13 ~ Hour 14 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 14 ~ Hour 15 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 15 ~ Hour 16 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 16 ~ Hour 17 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 17 ~ Hour 18 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 18 ~ Hour 19 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 19 ~ Hour 20 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 20 ~ Hour 21 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 21 ~ Hour 22 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 22 ~ Hour 23 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 23 ~ Hour 24 */
		};
		
		Asserts.assertEquals(expected, actual);
	}
	
	public void testCalculatePartDay1HourDistrib() {
		TimeSpanUtils distributor = new TimeSpanUtils();
		
		final long start = parseDateTime("2012-01-01 00:00:00.000");
		final long end = parseDateTime("2012-01-01 22:00:00.000");
		
		long[] actual = distributor.calculateHourDistribution(start, end);
		long[] expected = {
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 00 ~ Hour 01 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 01 ~ Hour 02 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 02 ~ Hour 03 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 03 ~ Hour 04 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 04 ~ Hour 05 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 05 ~ Hour 06 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 06 ~ Hour 07 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 07 ~ Hour 08 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 08 ~ Hour 09 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 09 ~ Hour 10 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 10 ~ Hour 11 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 11 ~ Hour 12 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 12 ~ Hour 13 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 13 ~ Hour 14 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 14 ~ Hour 15 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 15 ~ Hour 16 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 16 ~ Hour 17 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 17 ~ Hour 18 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 18 ~ Hour 19 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 19 ~ Hour 20 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 20 ~ Hour 21 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 21 ~ Hour 22 */
				0, /* Hour 22 ~ Hour 23 */
				0, /* Hour 23 ~ Hour 24 */
		};
		
		Asserts.assertEquals(expected, actual);
	}
	
	public void testCalculatePartDay2HourDistrib() {
		TimeSpanUtils distributor = new TimeSpanUtils();
		
		final long start = parseDateTime("2012-01-01 00:21:00.000");
		final long end = parseDateTime("2012-01-01 12:30:00.000");
		
		long[] actual = distributor.calculateHourDistribution(start, end);
		long[] expected = {
				(39 * CalendarUtils.MINUTE_IN_MILLIS), /* Hour 00 ~ Hour 01 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 01 ~ Hour 02 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 02 ~ Hour 03 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 03 ~ Hour 04 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 04 ~ Hour 05 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 05 ~ Hour 06 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 06 ~ Hour 07 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 07 ~ Hour 08 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 08 ~ Hour 09 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 09 ~ Hour 10 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 10 ~ Hour 11 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 11 ~ Hour 12 */
				(30 * CalendarUtils.MINUTE_IN_MILLIS), /* Hour 12 ~ Hour 13 */
				0, /* Hour 13 ~ Hour 14 */
				0, /* Hour 14 ~ Hour 15 */
				0, /* Hour 15 ~ Hour 16 */
				0, /* Hour 16 ~ Hour 17 */
				0, /* Hour 17 ~ Hour 18 */
				0, /* Hour 18 ~ Hour 19 */
				0, /* Hour 19 ~ Hour 20 */
				0, /* Hour 20 ~ Hour 21 */
				0, /* Hour 21 ~ Hour 22 */
				0, /* Hour 22 ~ Hour 23 */
				0, /* Hour 23 ~ Hour 24 */
		};
		
		Asserts.assertEquals(expected, actual);
	}
	
	public void testCalculateOverOneDay1HourDistrib() {
		TimeSpanUtils distributor = new TimeSpanUtils();
		
		final long start = parseDateTime("2010-01-22 00:35:12.123");
		final long end = parseDateTime("2010-01-23 14:35:22.000");
		
		long[] actual = distributor.calculateHourDistribution(start, end);
		long[] expected = {
				(CalendarUtils.HOUR_IN_MILLIS 
						+ 24 * CalendarUtils.MINUTE_IN_MILLIS 
						+ 47 * CalendarUtils.SECOND_IN_MILLIS 
						+ 877), /* Hour 00 ~ Hour 01 */
				(2 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 01 ~ Hour 02 */
				(2 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 02 ~ Hour 03 */
				(2 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 03 ~ Hour 04 */
				(2 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 04 ~ Hour 05 */
				(2 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 05 ~ Hour 06 */
				(2 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 06 ~ Hour 07 */
				(2 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 07 ~ Hour 08 */
				(2 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 08 ~ Hour 09 */
				(2 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 09 ~ Hour 10 */
				(2 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 10 ~ Hour 11 */
				(2 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 11 ~ Hour 12 */
				(2 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 12 ~ Hour 13 */
				(2 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 13 ~ Hour 14 */
				(CalendarUtils.HOUR_IN_MILLIS 
						+ 35 * CalendarUtils.MINUTE_IN_MILLIS
						+ 22 * CalendarUtils.SECOND_IN_MILLIS), /* Hour 14 ~ Hour 15 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 15 ~ Hour 16 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 16 ~ Hour 17 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 17 ~ Hour 18 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 18 ~ Hour 19 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 19 ~ Hour 20 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 20 ~ Hour 21 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 21 ~ Hour 22 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 22 ~ Hour 23 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 23 ~ Hour 24 */
		};
		
		Asserts.assertEquals(expected, actual);
	}
	
	public void testCalculateOverOneDay2HourDistrib() {
		TimeSpanUtils distributor = new TimeSpanUtils();
		
		final long start = parseDateTime("2010-02-14 16:25:25.666");
		final long end = parseDateTime("2010-02-15 10:02:33.999");
		
		long[] actual = distributor.calculateHourDistribution(start, end);
		long[] expected = {
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 00 ~ Hour 01 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 01 ~ Hour 02 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 02 ~ Hour 03 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 03 ~ Hour 04 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 04 ~ Hour 05 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 05 ~ Hour 06 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 06 ~ Hour 07 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 07 ~ Hour 08 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 08 ~ Hour 09 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 09 ~ Hour 10 */
				(2 * CalendarUtils.MINUTE_IN_MILLIS
						+ 33 * CalendarUtils.SECOND_IN_MILLIS
						+ 999), /* Hour 10 ~ Hour 11 */
				0, /* Hour 11 ~ Hour 12 */
				0, /* Hour 12 ~ Hour 13 */
				0, /* Hour 13 ~ Hour 14 */
				0, /* Hour 14 ~ Hour 15 */
				0, /* Hour 15 ~ Hour 16 */
				(34 * CalendarUtils.MINUTE_IN_MILLIS
						+ 34 * CalendarUtils.SECOND_IN_MILLIS
						+ 334), /* Hour 16 ~ Hour 17 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 17 ~ Hour 18 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 18 ~ Hour 19 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 19 ~ Hour 20 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 20 ~ Hour 21 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 21 ~ Hour 22 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 22 ~ Hour 23 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 23 ~ Hour 24 */
		};
		
		Asserts.assertEquals(expected, actual);
	}

	public void testCalculateOverWeekHourDistrib() {
		TimeSpanUtils distributor = new TimeSpanUtils();
		
		final long start = parseDateTime("2012-12-31 17:20:33.555");
		final long end = parseDateTime("2013-01-08 19:36:24.111");
		
		long[] actual = distributor.calculateHourDistribution(start, end);
		long[] expected = {
				(8 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 00 ~ Hour 01 */
				(8 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 01 ~ Hour 02 */
				(8 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 02 ~ Hour 03 */
				(8 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 03 ~ Hour 04 */
				(8 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 04 ~ Hour 05 */
				(8 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 05 ~ Hour 06 */
				(8 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 06 ~ Hour 07 */
				(8 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 07 ~ Hour 08 */
				(8 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 08 ~ Hour 09 */
				(8 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 09 ~ Hour 10 */
				(8 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 10 ~ Hour 11 */
				(8 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 11 ~ Hour 12 */
				(8 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 12 ~ Hour 13 */
				(8 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 13 ~ Hour 14 */
				(8 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 14 ~ Hour 15 */
				(8 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 15 ~ Hour 16 */
				(8 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 16 ~ Hour 17 */
				(8 * CalendarUtils.HOUR_IN_MILLIS
						+ 39 * CalendarUtils.MINUTE_IN_MILLIS
						+ 26 * CalendarUtils.SECOND_IN_MILLIS
						+ 445), /* Hour 17 ~ Hour 18 */
				(9 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 18 ~ Hour 19 */
				(8 * CalendarUtils.HOUR_IN_MILLIS
						+ 36 * CalendarUtils.MINUTE_IN_MILLIS
						+ 24 * CalendarUtils.SECOND_IN_MILLIS
						+ 111), /* Hour 19 ~ Hour 20 */
				(8 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 20 ~ Hour 21 */
				(8 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 21 ~ Hour 22 */
				(8 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 22 ~ Hour 23 */
				(8 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 23 ~ Hour 24 */
		};
		
		Asserts.assertEquals(expected, actual);
	}
	
	public void testCalculateHourDistribOnExistedDistribArray1() {
		TimeSpanUtils distributor = new TimeSpanUtils();
		
		final long start = parseDateTime("2012-12-12 00:00:00.000");
		final long end = parseDateTime("2012-12-13 00:00:00.000");
		
		long[] existed = {
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 00 ~ Hour 01 */
				(2 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 01 ~ Hour 02 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 02 ~ Hour 03 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 03 ~ Hour 04 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 04 ~ Hour 05 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 05 ~ Hour 06 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 06 ~ Hour 07 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 07 ~ Hour 08 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 08 ~ Hour 09 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 09 ~ Hour 10 */
				(2 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 10 ~ Hour 11 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 11 ~ Hour 12 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 12 ~ Hour 13 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 13 ~ Hour 14 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 14 ~ Hour 15 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 15 ~ Hour 16 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 16 ~ Hour 17 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 17 ~ Hour 18 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 18 ~ Hour 19 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 19 ~ Hour 20 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 20 ~ Hour 21 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 21 ~ Hour 22 */
				(2 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 22 ~ Hour 23 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 23 ~ Hour 24 */
		};
		
		long[] actual = distributor.calculateHourDistribution(
				existed, start, end);
		long[] expected = {
				(2 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 00 ~ Hour 01 */
				(3 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 01 ~ Hour 02 */
				(2 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 02 ~ Hour 03 */
				(2 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 03 ~ Hour 04 */
				(2 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 04 ~ Hour 05 */
				(2 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 05 ~ Hour 06 */
				(2 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 06 ~ Hour 07 */
				(2 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 07 ~ Hour 08 */
				(2 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 08 ~ Hour 09 */
				(2 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 09 ~ Hour 10 */
				(3 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 10 ~ Hour 11 */
				(2 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 11 ~ Hour 12 */
				(2 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 12 ~ Hour 13 */
				(2 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 13 ~ Hour 14 */
				(2 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 14 ~ Hour 15 */
				(2 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 15 ~ Hour 16 */
				(2 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 16 ~ Hour 17 */
				(2 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 17 ~ Hour 18 */
				(2 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 18 ~ Hour 19 */
				(2 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 19 ~ Hour 20 */
				(2 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 20 ~ Hour 21 */
				(2 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 21 ~ Hour 22 */
				(3 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 22 ~ Hour 23 */
				(2 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 23 ~ Hour 24 */
		};
		
		Asserts.assertEquals(expected, actual);
	}

	public void testCalculateHourDistribOnExistedDistribArray2() {
		TimeSpanUtils distributor = new TimeSpanUtils();
		
		final long start1 = parseDateTime("2010-01-22 00:35:12.123");
		final long end1 = parseDateTime("2010-01-23 14:35:22.000");
		final long start2 = parseDateTime("2010-02-14 16:25:25.666");
		final long end2 = parseDateTime("2010-02-15 10:02:33.999");
		
		long[] actual = null;
		
		actual = distributor.calculateHourDistribution(
				actual, start1, end1);
		actual = distributor.calculateHourDistribution(
				actual, start2, end2);
		
		long[] expected = {
				(2 * CalendarUtils.HOUR_IN_MILLIS 
						+ 24 * CalendarUtils.MINUTE_IN_MILLIS 
						+ 47 * CalendarUtils.SECOND_IN_MILLIS 
						+ 877), /* Hour 00 ~ Hour 01 */
				(3 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 01 ~ Hour 02 */
				(3 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 02 ~ Hour 03 */
				(3 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 03 ~ Hour 04 */
				(3 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 04 ~ Hour 05 */
				(3 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 05 ~ Hour 06 */
				(3 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 06 ~ Hour 07 */
				(3 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 07 ~ Hour 08 */
				(3 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 08 ~ Hour 09 */
				(3 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 09 ~ Hour 10 */
				(2 * CalendarUtils.HOUR_IN_MILLIS) 
					+ (2 * CalendarUtils.MINUTE_IN_MILLIS
						+ 33 * CalendarUtils.SECOND_IN_MILLIS
						+ 999), /* Hour 10 ~ Hour 11 */
				(2 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 11 ~ Hour 12 */
				(2 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 12 ~ Hour 13 */
				(2 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 13 ~ Hour 14 */
				(CalendarUtils.HOUR_IN_MILLIS 
						+ 35 * CalendarUtils.MINUTE_IN_MILLIS
						+ 22 * CalendarUtils.SECOND_IN_MILLIS), /* Hour 14 ~ Hour 15 */
				CalendarUtils.HOUR_IN_MILLIS, /* Hour 15 ~ Hour 16 */
				CalendarUtils.HOUR_IN_MILLIS 
					+ (34 * CalendarUtils.MINUTE_IN_MILLIS
						+ 34 * CalendarUtils.SECOND_IN_MILLIS
						+ 334), /* Hour 16 ~ Hour 17 */
				(2 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 17 ~ Hour 18 */
				(2 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 18 ~ Hour 19 */
				(2 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 19 ~ Hour 20 */
				(2 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 20 ~ Hour 21 */
				(2 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 21 ~ Hour 22 */
				(2 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 22 ~ Hour 23 */
				(2 * CalendarUtils.HOUR_IN_MILLIS), /* Hour 23 ~ Hour 24 */
		};
		
		Asserts.assertEquals(expected, actual);
	}

}