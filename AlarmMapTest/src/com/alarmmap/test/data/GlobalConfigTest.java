package com.alarmmap.test.data;

import junit.framework.TestCase;

import com.alarmmap.data.GlobalConfig;
import com.alarmmap.data.ReversedTimesException;

public class GlobalConfigTest extends TestCase {
	
	/*
	 * Some values used on the tests
	 */
	private static final double range = 42.0;
	private static final String ringtone = "just://a.ringtone";
	private static final boolean vibrate = !GlobalConfig.defaultVibration;
	private static final String message = "Lorem ipsum dolor sit amet.";
	private static final boolean onDayOfWeek = !GlobalConfig.defaultOnDayOfWeek;
	private static final int beginH = 2, beginM = 20;
	private static final int endH = 3, endM = 30;
	
	private GlobalConfig conf;

	
	/*
	 * TestCase methods
	 */
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		conf = GlobalConfig.Instance();
		conf.resetFullConfig();
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	
	/*
	 * Actual tests
	 */
	
	/*
	 * Tests for the range property.
	 * 
	 * Must properly set the value;
	 * Must properly reset the value;
	 * Must not set an invalid value.
	 */
	public void testRangeSet() {
		try {
			conf.setRange(range);
			assertEquals("Must retrieve the exact range just set.", range, conf.getRange());
		} catch (IllegalArgumentException e) {
			fail("Must accept non-negative ranges.");
		}
	}
	public void testRangeReset() {	
		conf.setRange(range);
		conf.resetRange();
		assertEquals("Must retrieve the range from category after unsetting it.", 
				GlobalConfig.defaultRange, conf.getRange());
	}
	public void testRangeRejectNegative() {
		try {
			conf.setRange(-1);
			fail("Must not accept a negative range.");
		} catch (IllegalArgumentException e) { }
	}
	
	/*
	 * Tests for the vibration property.
	 * 
	 * Must properly set the value;
	 * Must properly reset the value.
	 */
	public void testVibrationSet() {
		conf.setVibration(vibrate);
		assertEquals("Must retrieve the exact vibration just set.", 
				vibrate, conf.willVibrate());
	}
	public void testVibrationReset() {
		conf.setVibration(vibrate);
		conf.resetVibration();
		assertEquals("Must retrieve the vibration from category after unsetting it.", 
				GlobalConfig.defaultVibration, conf.willVibrate());
	}
	
	/*
	 * Tests for the ringtoneUri property.
	 * 
	 * Must properly set the value;
	 * Must properly reset the value;
	 * Must not set an invalid value.
	 */
	public void testRingtoneUriSet() {
		try {
			conf.setRingtoneUri(ringtone);
			assertEquals("Must retrieve the exact ringtone just set.", 
					ringtone, conf.getRingtoneUri());
		} catch (IllegalArgumentException e) {
			fail("Must accept all non-null ringtone URIs.");
		}
	}
	public void testRingtoneUriReset() {	
		conf.setRingtoneUri(ringtone);
		conf.resetRingtoneUri();
		assertEquals("Must retrieve the ringtone from category after unsetting it.", 
				GlobalConfig.defaultRingtoneUri, conf.getRingtoneUri());
	}
	public void testRingtoneUriRejectNull() {	
		try {
			conf.setRingtoneUri(null);
			fail("Must reject a null ringtone URI.");
		} catch (IllegalArgumentException e) { }
	}

	/*
	 * Tests for the message property.
	 * 
	 * Must properly set the value;
	 * Must properly reset the value;
	 * Must not set an invalid value.
	 */
	public void testMessage() {
		
		try {
			conf.setMessage(message);
			assertEquals("Must retrieve the exact message just set.", 
					message, conf.getMessage());
		} catch (IllegalArgumentException e) {
			fail("Must accept all non-null messages.");
		}
		
		conf.resetMessage();
		assertEquals("Must retrieve the message from category after unsetting it.", 
				GlobalConfig.defaultMessage, conf.getMessage());
		
		try {
			conf.setMessage(null);
			fail("Must reject a null message.");
		} catch (IllegalArgumentException e) { }
	}
	
	/*
	 * Tests for the begin/end time.
	 * 
	 * Must look at the category by default;
	 * Must properly set the value;
	 * Must properly unset the value;
	 * Must not set an invalid value;
	 * Must work with boundary times.
	 */
	public void testBeginTimeSet() {
		
		conf.setBeginTime(beginH, beginM);
		
		assertEquals("Must retrieve the beginH just set.",
				beginH, conf.getBeginHour());
		assertEquals("Must retrieve the beginM just set.",
				beginM, conf.getBeginMinute());
		
	}
	public void testEndTimeSet() {
		
		conf.setEndTime(endH, endM);
		
		assertEquals("Must retrieve the endH just set.",
				endH, conf.getEndHour());
		assertEquals("Must retrieve the endM just set.",
				endM, conf.getEndMinute());
		
	}
		
	public void testBeginTimeReset() {
		
		conf.setBeginTime(beginH, beginM);
		conf.resetBeginTime();
		
		assertEquals("Must obtain correct beginH after resetting.",
				GlobalConfig.defaultBeginH, conf.getBeginHour());
		assertEquals("Must obtain correct beginM after resetting.",
				GlobalConfig.defaultBeginM, conf.getBeginMinute());
		
	}
	public void testEndTimeReset() {
		
		conf.setEndTime(endH, endM);
		conf.resetEndTime();
		
		assertEquals("Must obtain correct endH after resetting.",
				GlobalConfig.defaultEndH, conf.getEndHour());
		assertEquals("Must obtain correct endM after resetting.",
				GlobalConfig.defaultEndM, conf.getEndMinute());
		
	}
		
	private static final int safeH = (beginH + endH) / 2;
	private static final int safeM = endM - 1;
	public void testBeginTimeRejectNegative() {
		
		try {
			conf.setBeginTime(safeH, -1);
			fail("Must reject negative times.");
		} catch (IllegalArgumentException e) { 
			// If had exception: worked!
		}
		
		try {
			conf.setBeginTime(-1, safeM);
			fail("Must reject negative times.");
		} catch (IllegalArgumentException e) { 
			// If had exception: worked!
		}
		
		try {
			conf.setBeginTime(-1, -1);
			fail("Must reject negative times.");
		} catch (IllegalArgumentException e) { 
			// If had exception: worked!
		}
		
	}
	public void testEndTimeRejectNegative() {
		
		try {
			conf.setEndTime(safeH, -1);
			fail("Must reject negative times.");
		} catch (IllegalArgumentException e) { 
			// If had exception: worked!
		}
		
		try {
			conf.setEndTime(-1, safeM);
			fail("Must reject negative times.");
		} catch (IllegalArgumentException e) { 
			// If had exception: worked!
		}
		
		try {
			conf.setEndTime(-1, -1);
			fail("Must reject negative times.");
		} catch (IllegalArgumentException e) { 
			// If had exception: worked!
		}
		
	}
	
	public void testBeginTimeRejectReversed() {
		
		// Set the end time to a safe value
		conf.setEndTime(12, 30);
		
		try {
			conf.setBeginTime(conf.getEndHour()+1, conf.getEndMinute());
			fail("Must reject reversed begin/end times.");
		} catch (ReversedTimesException e) {
			// If had exception: worked!
		}

		try {
			conf.setBeginTime(conf.getEndHour(), conf.getEndMinute()+1);
			fail("Must reject reversed begin/end times.");
		} catch (ReversedTimesException e) {
			// If had exception: worked!
		}
		try {
			conf.setBeginTime(conf.getEndHour(), conf.getEndMinute());
			fail("Must reject equal begin/end times.");
		} catch (ReversedTimesException e) {
			// If had exception: worked!
		}
		
	}
	public void testEndTimeRejectReversed() {
		
		// Sets the begin time to a safe value
		conf.setBeginTime(12, 30);
		
		try {
			conf.setEndTime(conf.getBeginHour()-1, conf.getBeginMinute());
			fail("Must reject reversed begin/end times.");
		} catch (ReversedTimesException e) {
			// If had exception: worked!
		}

		try {
			conf.setEndTime(conf.getBeginHour(), conf.getBeginMinute()-1);
			fail("Must reject reversed begin/end times.");
		} catch (ReversedTimesException e) {
			// If had exception: worked!
		}
		
		try {
			conf.setEndTime(conf.getBeginHour(), conf.getBeginMinute());
			fail("Must reject equal begin/end times.");
		} catch (ReversedTimesException e) {
			// If had exception: worked!
		}
	}
	
	public void testBeginTimeBoundaryCases() {

		// 24:00 will never be valid since it would 
		// be greater than or equal to the end time.
		
		// Must not accept hours greater than 24
		try {
			conf.setBeginTime(25, 00);
			fail("Must not accept hours greater than 24.");
		} catch (IllegalArgumentException e) { }
		
		// Must not accept minutes grater than 59
		try {
			conf.setBeginTime(12, 60);
			fail("Must not accept minutes greater than 59.");
		} catch (IllegalArgumentException e) { }
		
	}
	public void testEndTimeBoundaryCases() {
		
		// Must accept 24:00
		try {
			conf.setEndTime(24, 00);
		} 
		catch (IllegalArgumentException e) {
			fail("Must accept 24:00 for an end time.");
		}
		
		// Must not accept 24:x, with x > 0
		try {
			conf.setEndTime(24, 01);
			fail("Must not accept times later than 24:00.");
		} catch (IllegalArgumentException e) { }
		
		// Must not accept hours greater than 24
		try {
			conf.setEndTime(25, 30);
			fail("Must not accept hours greater than 24.");
		} catch (IllegalArgumentException e) { }
		
		// Must not accept minutes greater than 59
		try {
			conf.setEndTime(12, 60);
			fail("Must not accept minutes greater than 59.");
		} catch (IllegalArgumentException e) { }
	}
	
	/*
	 * Tests for the 'day of week' properties.
	 * 
	 * Must properly set the values;
	 * Must properly reset the values.
	 */
	private void changeOnDaysOfWeek() {
		
		conf.setOnSunday(onDayOfWeek);
		conf.setOnMonday(onDayOfWeek);
		conf.setOnTuesday(onDayOfWeek);
		conf.setOnWednesday(onDayOfWeek);
		conf.setOnThursday(onDayOfWeek);
		conf.setOnFriday(onDayOfWeek);
		conf.setOnSaturday(onDayOfWeek);
		
	}
	public void testDaysOfWeekSet() {
		
		// Sets new configuration for the days of week
		changeOnDaysOfWeek();
		
		assertEquals("Must obtain correct 'sunday' after setting.",
				onDayOfWeek, conf.isOnSunday());
		assertEquals("Must obtain correct 'monday' after setting.",
				onDayOfWeek, conf.isOnMonday());
		assertEquals("Must obtain correct 'tuesday' after setting.",
				onDayOfWeek, conf.isOnTuesday());
		assertEquals("Must obtain correct 'wednesday' after setting.",
				onDayOfWeek, conf.isOnWednesday());
		assertEquals("Must obtain correct 'thursday' after setting.",
				onDayOfWeek, conf.isOnThursday());
		assertEquals("Must obtain correct 'friday' after setting.",
				onDayOfWeek, conf.isOnFriday());
		assertEquals("Must obtain correct 'saturday' after setting.",
				onDayOfWeek, conf.isOnSaturday());
		
	}
	public void testDaysOfWeekResetValues() {
		
		// Sets and then resets the days of week configuration
		changeOnDaysOfWeek();
		conf.resetDaysOfWeek();
		
		assertEquals("Must obtain correct 'sunday' after resetting.",
				GlobalConfig.defaultOnDayOfWeek, conf.isOnSunday());
		assertEquals("Must obtain correct 'monday' after resetting.",
				GlobalConfig.defaultOnDayOfWeek, conf.isOnMonday());
		assertEquals("Must obtain correct 'tuesday' after resetting.",
				GlobalConfig.defaultOnDayOfWeek, conf.isOnTuesday());
		assertEquals("Must obtain correct 'wednesday' after resetting.",
				GlobalConfig.defaultOnDayOfWeek, conf.isOnWednesday());
		assertEquals("Must obtain correct 'thursday' after resetting.",
				GlobalConfig.defaultOnDayOfWeek, conf.isOnThursday());
		assertEquals("Must obtain correct 'friday' after resetting.",
				GlobalConfig.defaultOnDayOfWeek, conf.isOnFriday());
		assertEquals("Must obtain correct 'saturday' after resetting.",
				GlobalConfig.defaultOnDayOfWeek, conf.isOnSaturday());
		
	}

}
