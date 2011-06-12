package com.alarmmap.data.test;

import java.util.ArrayList;

import junit.framework.TestCase;

import com.alarmmap.data.Category;
import com.alarmmap.data.GlobalConfig;
import com.alarmmap.data.PointOfInterest;
import com.alarmmap.data.ReversedTimesException;

public class CategoryTest extends TestCase {
	
	/*
	 * Some values used on the tests
	 */
	private static final int id0 = 0;
	private static final String name0 = "center!";

	private static final double range = 42.0;
	private static final String ringtone = "just://a.ringtone";
	private static final boolean vibrate = true;
	private static final String message = "Lorem ipsum dolor sit amet.";
	private static final boolean onDayOfWeek = !PointOfInterest.defaultOnDayOfWeek;
	private static final int beginH = 2, beginM = 20;
	private static final int endH = 3, endM = 30;

	private static final double globRange = 6.02e+23;
	private static final String globRingtone = "just://another.ringtone";
	private static final boolean globVibrate = false;
	private static final String globMessage = "Vivamus dignissim mollis justo a.";
	private static final boolean globOnDayOfWeek = !PointOfInterest.defaultOnDayOfWeek;
	private static final int globBeginH = 1, globBeginM = 10;
	private static final int globEndH = 4, globEndM = 40;

	private Category categ;
	private GlobalConfig conf;
	
	/**
	 * A Mock class for using a test configuration for the category.
	 * Assumes the class 'Category' works. 
	 * @author ggazzi
	 */
	private class MockGlobalConfig extends GlobalConfig {	
		public MockGlobalConfig() {
			
			setRange(globRange);
			setRingtoneUri(globRingtone);
			setVibration(globVibrate);
			setMessage(globMessage);
			
			setBeginTime(globBeginH, globBeginM);
			setEndTime(globEndH, globEndM);
			
			setOnSunday(globOnDayOfWeek);
			setOnMonday(globOnDayOfWeek);
			setOnTuesday(globOnDayOfWeek);
			setOnWednesday(globOnDayOfWeek);
			setOnThursday(globOnDayOfWeek);
			setOnFriday(globOnDayOfWeek);
			setOnSaturday(globOnDayOfWeek);
		}
	}
	
	/*
	 * Test Case methods
	 */
	@Override
	protected void setUp() throws Exception {
		
		super.setUp();
		conf = new MockGlobalConfig();
		categ = new Category(id0, name0, new ArrayList<Integer>(), conf);
		
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	/*
	 * Actual tests
	 */
	
	/**
	 * Test for the essential property getters of
	 * {@link com.alarmmap.data.Config}
	 */
	public void testEssentialProperties() {
		
		assertEquals("Must get the correct ID", id0, categ.getId());
		assertEquals("Must get the correct name", name0, categ.getName());
		
		// TODO test the list of point ids
		
	}
	
	/*
	 * Tests for the range property.
	 * 
	 * Must look at the global by default;
	 * Must properly set the value;
	 * Must properly unset the value;
	 * Must not set an invalid value.
	 */
	public void testRangeInitial() {
		assertEquals("Must obtain correct initial range from the global settings.", globRange, categ.getRange());
	}
	public void testRangeSet() {
		try {
			categ.setRange(range);
			assertEquals("Must retrieve the exact range just set.", range, categ.getRange());
		} catch (IllegalArgumentException e) {
			fail("Must accept non-negative ranges.");
		}
	}
	public void testRangeReset() {
		
		categ.setRange(range);
		categ.resetRange();
		assertEquals("Must retrieve the range from global config after unsetting it.", globRange, categ.getRange());
	}
	public void testRangeRejectNegative() {
		
		try {
			categ.setRange(-1);
			fail("Must not accept a negative range.");
		} catch (IllegalArgumentException e) { }
		
	}

	/*
	 * Tests for the vibration property.
	 * 
	 * Must look at the global by default;
	 * Must properly set the value;
	 * Must properly unset the value.
	 */
	public void testVibrationInitial() {
		
		assertEquals("Must obtain correct initial vibration from the global config.",
				globVibrate, categ.willVibrate());
		
	}
	public void testVibrationSet() {
		
		categ.setVibration(vibrate);
		assertEquals("Must retrieve the exact vibration just set.", 
				vibrate, categ.willVibrate());
		
	}
	public void testVibrationReset() {
		
		categ.setVibration(vibrate);
		categ.resetVibration();
		
		assertEquals("Must retrieve the vibration from category after unsetting it.", 
				globVibrate, categ.willVibrate());
		
	}
	
	/*
	 * Tests for the ringtoneUri property.
	 * 
	 * Must look at the global by default;
	 * Must properly set the value;
	 * Must properly unset the value;
	 * Must not set an invalid value.
	 */
	public void testRingtoneUriInitial() {
		
		assertEquals("Must obtain correct initial ringtone from the global config.",
				globRingtone, categ.getRingtoneUri());
		
	}
	public void testRingtoneUriSet() {
		
		try {
			categ.setRingtoneUri(ringtone);
			assertEquals("Must retrieve the exact ringtone just set.", 
					ringtone, categ.getRingtoneUri());
		} 
		catch (IllegalArgumentException e) {
			fail("Must accept all non-null ringtone URIs.");
		}
		
	}
	public void testRingtoneUriReset() {
		
		categ.setRingtoneUri(ringtone);
		categ.resetRingtoneUri();
		
		assertEquals("Must retrieve the ringtone from category after unsetting it.", 
				globRingtone, categ.getRingtoneUri());
		
	}
	public void testRingtoneUriRejectNull() {
		
		try {
			categ.setRingtoneUri(null);
			fail("Must reject a null ringtone URI.");
		} catch (IllegalArgumentException e) { }
		
	}

	/*
	 * Tests for the message property.
	 * 
	 * Must look at the global by default;
	 * Must properly set the value;
	 * Must properly unset the value;
	 * Must not set an invalid value.
	 */
	public void testMessageInitial() {
		
		assertEquals("Must obtain correct initial message from the global config.",
				globMessage, categ.getMessage());
		
	}
	public void testMessageSet() {
		
		try {
			categ.setMessage(message);
			assertEquals("Must retrieve the exact message just set.", 
					message, categ.getMessage());
		} 
		catch (IllegalArgumentException e) {
			fail("Must accept all non-null messages.");
		}
		
	}
	public void testMessageReset() {
		
		categ.setMessage(message);
		categ.resetMessage();
		
		assertEquals("Must retrieve the message from category after unsetting it.", 
				globMessage, categ.getMessage());
		
	}
	public void testMessageRejectNull() {
		
		try {
			categ.setMessage(null);
			fail("Must reject a null message.");
		} catch (IllegalArgumentException e) { }
		
	}
	
	/*
	 * Tests for the begin/end time.
	 * 
	 * Must look at the global by default;
	 * Must properly set the value;
	 * Must properly unset the value;
	 * Must not set an invalid value;
	 * Must work with boundary times.
	 */
	public void testBeginTimeInitial() {
		
		assertEquals("Must obtain correct initial beginH from the global config.",
				globBeginH, categ.getBeginHour());
		assertEquals("Must obtain correct initial beginM from the global config.",
				globBeginM, categ.getBeginMinute());
		
	}
	public void testEndTimeInitial() {
		
		assertEquals("Must obtain correct initial endH from the global config.",
				globEndH, categ.getEndHour());
		assertEquals("Must obtain correct initial endM from the global config.",
				globEndM, categ.getEndMinute());
		
	}
	
	public void testBeginTimeSet() {
		
		categ.setBeginTime(beginH, beginM);
		
		assertEquals("Must retrieve the beginH just set.",
				beginH, categ.getBeginHour());
		assertEquals("Must retrieve the beginM just set.",
				beginM, categ.getBeginMinute());
		
	}
	public void testEndTimeSet() {
		
		categ.setEndTime(endH, endM);
		
		assertEquals("Must retrieve the endH just set.",
				endH, categ.getEndHour());
		assertEquals("Must retrieve the endM just set.",
				endM, categ.getEndMinute());
		
	}
		
	public void testBeginTimeReset() {
		
		categ.setBeginTime(beginH, beginM);
		categ.resetBeginTime();
		
		assertEquals("Must obtain correct beginH after resetting.",
				globBeginH, categ.getBeginHour());
		assertEquals("Must obtain correct beginM after resetting.",
				globBeginM, categ.getBeginMinute());
		
	}
	public void testEndTimeReset() {
		
		categ.setEndTime(endH, endM);
		categ.resetEndTime();
		
		assertEquals("Must obtain correct endH after resetting.",
				globEndH, categ.getEndHour());
		assertEquals("Must obtain correct endM after resetting.",
				globEndM, categ.getEndMinute());
		
	}
		
	private static final int safeH = (beginH + endH) / 2;
	private static final int safeM = endM - 1;
	public void testBeginTimeRejectNegative() {
		
		try {
			categ.setBeginTime(safeH, -1);
			fail("Must reject negative times.");
		} catch (IllegalArgumentException e) { }
		
		try {
			categ.setBeginTime(-1, safeM);
			fail("Must reject negative times.");
		} catch (IllegalArgumentException e) { }
		
		try {
			categ.setBeginTime(-1, -1);
			fail("Must reject negative times.");
		} catch (IllegalArgumentException e) { }
		
	}
	public void testEndTimeRejectNegative() {
		
		try {
			categ.setEndTime(safeH, -1);
			fail("Must reject negative times.");
		} catch (IllegalArgumentException e) { }
		
		try {
			categ.setEndTime(-1, safeM);
			fail("Must reject negative times.");
		} catch (IllegalArgumentException e) { }
		
		try {
			categ.setEndTime(-1, -1);
			fail("Must reject negative times.");
		} catch (IllegalArgumentException e) { }
		
	}
	
	public void testBeginTimeRejectReversed() {
		
		try {
			categ.setBeginTime(categ.getEndHour()+1, categ.getEndMinute());
			fail("Must reject reversed begin/end times.");
		} catch (ReversedTimesException e) { }

		try {
			categ.setBeginTime(categ.getEndHour(), categ.getEndMinute()+1);
			fail("Must reject reversed begin/end times.");
		} catch (ReversedTimesException e) { }
		
		try {
			categ.setBeginTime(categ.getEndHour(), categ.getEndMinute());
			fail("Must reject equal begin/end times.");
		} catch (ReversedTimesException e) { }
		
	}
	public void testEndTimeRejectReversed() {
		
		try {
			categ.setEndTime(categ.getBeginHour()-1, categ.getBeginMinute());
			fail("Must reject reversed begin/end times.");
		} catch (ReversedTimesException e) { }

		try {
			categ.setEndTime(categ.getBeginHour(), categ.getBeginMinute()-1);
			fail("Must reject reversed begin/end times.");
		} catch (ReversedTimesException e) { }
		
		try {
			categ.setEndTime(categ.getBeginHour(), categ.getBeginMinute());
			fail("Must reject equal begin/end times.");
		} catch (ReversedTimesException e) { }
		
	}
	
	public void testBeginTimeBoundaryCases() {

		// 24:00 will never be valid since it would 
		// be greater than or equal to the end time.
		
		// Must not accept hours greater than 24
		try {
			categ.setBeginTime(25, 00);
			fail("Must not accept hours greater than 24.");
		} catch (IllegalArgumentException e) { }
		
		// Must not accept minutes grater than 59
		try {
			categ.setBeginTime(12, 60);
			fail("Must not accept minutes greater than 59.");
		} catch (IllegalArgumentException e) { }
		
	}
	public void testEndTimeBoundaryCases() {
		
		// Must accept 24:00
		try {
			categ.setEndTime(24, 00);
		} 
		catch (IllegalArgumentException e) {
			fail("Must accept 24:00 for an end time.");
		}
		
		// Must not accept 24:x, with x > 0
		try {
			categ.setEndTime(24, 01);
			fail("Must not accept times later than 24:00.");
		} catch (IllegalArgumentException e) { }
		
		// Must not accept hours greater than 24
		try {
			categ.setEndTime(25, 30);
			fail("Must not accept hours greater than 24.");
		} catch (IllegalArgumentException e) { }
		
		// Must not accept minutes greater than 59
		try {
			categ.setEndTime(12, 60);
			fail("Must not accept minutes greater than 59.");
		} catch (IllegalArgumentException e) { }
	}
	
	/*
	 * Tests for the 'day of week' properties.
	 * 
	 * Must look at the global by default;
	 * Must have correct default values when using our own schedule;
	 * Must properly set the values;
	 * Must not reset the values when not using our own schedule;
	 * Resetting must mean not using our own schedule.
	 */
	public void testDaysOfWeekInitial() {
		
		assertEquals("Must obtain correct initial 'sunday' from the global config.",
				globOnDayOfWeek, categ.isOnSunday());
		assertEquals("Must obtain correct initial 'monday' from the global config.",
				globOnDayOfWeek, categ.isOnMonday());
		assertEquals("Must obtain correct initial 'tuesday' from the global config.",
				globOnDayOfWeek, categ.isOnTuesday());
		assertEquals("Must obtain correct initial 'wednesday' from the global config.",
				globOnDayOfWeek, categ.isOnWednesday());
		assertEquals("Must obtain correct initial 'thursday' from the global config.",
				globOnDayOfWeek, categ.isOnThursday());
		assertEquals("Must obtain correct initial 'friday' from the global config.",
				globOnDayOfWeek, categ.isOnFriday());
		assertEquals("Must obtain correct initial 'saturday' from the global config.",
				globOnDayOfWeek, categ.isOnSaturday());
		
	}
	public void testDaysOfWeekDefaultValues() {
		
		categ.useOwnSchedule(true);
		
		assertEquals("Must obtain default 'sunday' after adopting own schedule.",
				PointOfInterest.defaultOnDayOfWeek, categ.isOnSunday());
		assertEquals("Must obtain default 'monday' after adopting own schedule.",
				PointOfInterest.defaultOnDayOfWeek, categ.isOnMonday());
		assertEquals("Must obtain default 'tuesday' after adopting own schedule.",
				PointOfInterest.defaultOnDayOfWeek, categ.isOnTuesday());
		assertEquals("Must obtain default 'wednesday' after adopting own schedule.",
				PointOfInterest.defaultOnDayOfWeek, categ.isOnWednesday());
		assertEquals("Must obtain default 'thursday' after adopting own schedule.",
				PointOfInterest.defaultOnDayOfWeek, categ.isOnThursday());
		assertEquals("Must obtain default 'friday' after adopting own schedule.",
				PointOfInterest.defaultOnDayOfWeek, categ.isOnFriday());
		assertEquals("Must obtain default 'saturday' after adopting own schedule.",
				PointOfInterest.defaultOnDayOfWeek, categ.isOnSaturday());
		
	}
	public void testDaysOfWeekSet() {
		
		// Make sure the global config is different from the category
		conf.setOnSunday(!onDayOfWeek);
		conf.setOnMonday(!onDayOfWeek);
		conf.setOnTuesday(!onDayOfWeek);
		conf.setOnWednesday(!onDayOfWeek);
		conf.setOnThursday(!onDayOfWeek);
		conf.setOnFriday(!onDayOfWeek);
		conf.setOnSaturday(!onDayOfWeek);
		
		categ.useOwnSchedule(false);
		categ.setOnSunday(onDayOfWeek);
		assertEquals("Must obtain correct 'sunday' after setting.",
				onDayOfWeek, categ.isOnSunday());

		categ.useOwnSchedule(false);
		categ.setOnMonday(onDayOfWeek);
		assertEquals("Must obtain correct 'monday' after setting.",
				onDayOfWeek, categ.isOnMonday());

		categ.useOwnSchedule(false);
		categ.setOnTuesday(onDayOfWeek);
		assertEquals("Must obtain correct 'tuesday' after setting.",
				onDayOfWeek, categ.isOnTuesday());

		categ.useOwnSchedule(false);
		categ.setOnWednesday(onDayOfWeek);
		assertEquals("Must obtain correct 'wednesday' after setting.",
				onDayOfWeek, categ.isOnWednesday());

		categ.useOwnSchedule(false);
		categ.setOnThursday(onDayOfWeek);
		assertEquals("Must obtain correct 'thursday' after setting.",
				onDayOfWeek, categ.isOnThursday());

		categ.useOwnSchedule(false);
		categ.setOnFriday(onDayOfWeek);
		assertEquals("Must obtain correct 'friday' after setting.",
				onDayOfWeek, categ.isOnFriday());

		categ.useOwnSchedule(false);
		categ.setOnSaturday(onDayOfWeek);
		assertEquals("Must obtain correct 'saturday' after setting.",
				onDayOfWeek, categ.isOnSaturday());
		
	}
	public void testDaysOfWeekNoReset() {

		// Make sure the global config is different from the category
		conf.setOnSunday(!onDayOfWeek);
		conf.setOnMonday(!onDayOfWeek);
		conf.setOnTuesday(!onDayOfWeek);
		conf.setOnWednesday(!onDayOfWeek);
		conf.setOnThursday(!onDayOfWeek);
		conf.setOnFriday(!onDayOfWeek);
		conf.setOnSaturday(!onDayOfWeek);
		
		categ.setOnSunday(onDayOfWeek);
		categ.setOnMonday(onDayOfWeek);
		categ.setOnTuesday(onDayOfWeek);
		categ.setOnWednesday(onDayOfWeek);
		categ.setOnThursday(onDayOfWeek);
		categ.setOnFriday(onDayOfWeek);
		categ.setOnSaturday(onDayOfWeek);
		
		categ.useOwnSchedule(false);
		categ.useOwnSchedule(true);
		
		assertEquals("Must obtain correct 'sunday' after resetting 'useOwnSchedule'.",
				onDayOfWeek, categ.isOnSunday());
		assertEquals("Must obtain correct 'monday' after resetting 'useOwnSchedule'.",
				onDayOfWeek, categ.isOnMonday());
		assertEquals("Must obtain correct 'tuesday' after resetting 'useOwnSchedule'.",
				onDayOfWeek, categ.isOnTuesday());
		assertEquals("Must obtain correct 'wednesday' after resetting 'useOwnSchedule'.",
				onDayOfWeek, categ.isOnWednesday());
		assertEquals("Must obtain correct 'thursday' after resetting 'useOwnSchedule'.",
				onDayOfWeek, categ.isOnThursday());
		assertEquals("Must obtain correct 'friday' after resetting 'useOwnSchedule'.",
				onDayOfWeek, categ.isOnFriday());
		assertEquals("Must obtain correct 'saturday' after resetting 'useOwnSchedule'.",
				onDayOfWeek, categ.isOnSaturday());
	}
	public void testDaysOfWeekResetToCategory() {
		
		// Make sure the global config is different from the category
		conf.setOnSunday(!onDayOfWeek);
		conf.setOnMonday(!onDayOfWeek);
		conf.setOnTuesday(!onDayOfWeek);
		conf.setOnWednesday(!onDayOfWeek);
		conf.setOnThursday(!onDayOfWeek);
		conf.setOnFriday(!onDayOfWeek);
		conf.setOnSaturday(!onDayOfWeek);
		
		categ.setOnSunday(onDayOfWeek);
		categ.setOnMonday(onDayOfWeek);
		categ.setOnTuesday(onDayOfWeek);
		categ.setOnWednesday(onDayOfWeek);
		categ.setOnThursday(onDayOfWeek);
		categ.setOnFriday(onDayOfWeek);
		categ.setOnSaturday(onDayOfWeek);
		
		categ.useOwnSchedule(false);
		
		assertEquals("Must obtain correct 'sunday' after setting 'useOwnSchedule' to false.",
				conf.isOnSunday(), categ.isOnSunday());
		assertEquals("Must obtain correct 'monday' after setting 'useOwnSchedule' to false.",
				conf.isOnMonday(), categ.isOnMonday());
		assertEquals("Must obtain correct 'tuesday' after setting 'useOwnSchedule' to false.",
				conf.isOnTuesday(), categ.isOnTuesday());
		assertEquals("Must obtain correct 'wednesday' after setting 'useOwnSchedule' to false.",
				conf.isOnWednesday(), categ.isOnWednesday());
		assertEquals("Must obtain correct 'thursday' after setting 'useOwnSchedule' to false.",
				conf.isOnThursday(), categ.isOnThursday());
		assertEquals("Must obtain correct 'friday' after setting 'useOwnSchedule' to false.",
				conf.isOnFriday(), categ.isOnFriday());
		assertEquals("Must obtain correct 'saturday' after setting 'useOwnSchedule' to false.",
				conf.isOnSaturday(), categ.isOnSaturday());
		
	}
	public void testDaysOfWeekResetValues() {
		
		// Make sure the global config is different from the category
		conf.setOnSunday(!onDayOfWeek);
		conf.setOnMonday(!onDayOfWeek);
		conf.setOnTuesday(!onDayOfWeek);
		conf.setOnWednesday(!onDayOfWeek);
		conf.setOnThursday(!onDayOfWeek);
		conf.setOnFriday(!onDayOfWeek);
		conf.setOnSaturday(!onDayOfWeek);
		
		categ.setOnSunday(onDayOfWeek);
		categ.setOnMonday(onDayOfWeek);
		categ.setOnTuesday(onDayOfWeek);
		categ.setOnWednesday(onDayOfWeek);
		categ.setOnThursday(onDayOfWeek);
		categ.setOnFriday(onDayOfWeek);
		categ.setOnSaturday(onDayOfWeek);
		
		categ.resetDaysOfWeek();
		
		assertEquals("Must obtain correct 'sunday' after resetting days of week.",
				conf.isOnSunday(), categ.isOnSunday());
		assertEquals("Must obtain correct 'monday' after resetting days of week.",
				conf.isOnMonday(), categ.isOnMonday());
		assertEquals("Must obtain correct 'tuesday' after resetting days of week.",
				conf.isOnTuesday(), categ.isOnTuesday());
		assertEquals("Must obtain correct 'wednesday' after resetting days of week.",
				conf.isOnWednesday(), categ.isOnWednesday());
		assertEquals("Must obtain correct 'thursday' after resetting days of week.",
				conf.isOnThursday(), categ.isOnThursday());
		assertEquals("Must obtain correct 'friday' after resetting days of week.",
				conf.isOnFriday(), categ.isOnFriday());
		assertEquals("Must obtain correct 'saturday' after resetting days of week.",
				conf.isOnSaturday(), categ.isOnSaturday());
		
	}
}
