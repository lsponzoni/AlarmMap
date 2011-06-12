package com.alarmmap.data.test;

import junit.framework.TestCase;

import com.alarmmap.data.Category;
import com.alarmmap.data.CategoryDBManager;
import com.alarmmap.data.PointOfInterest;
import com.alarmmap.data.ReversedTimesException;

public class PointOfInterestTest extends TestCase {
	
	/*
	 * Some values used on the tests
	 */
	private static final int id0 = 0, categ0 = 1;
	private static final double lat0 = 0, lon0 = 0;
	private static final String name0 = "center!";

	private static final double range = 42.0;
	private static final String ringtone = "just://a.ringtone";
	private static final boolean vibrate = true;
	private static final String message = "Lorem ipsum dolor sit amet.";
	private static final boolean onDayOfWeek = !PointOfInterest.defaultOnDayOfWeek;
	private static final int beginH = 2, beginM = 20;
	private static final int endH = 3, endM = 30;

	private static final double catRange = 6.02e+23;
	private static final String catRingtone = "just://another.ringtone";
	private static final boolean catVibrate = false;
	private static final String catMessage = "Vivamus dignissim mollis justo a.";
	private static final boolean catOnDayOfWeek = !PointOfInterest.defaultOnDayOfWeek;
	private static final int catBeginH = 1, catBeginM = 10;
	private static final int catEndH = 4, catEndM = 40;

	private PointOfInterest POI;
	
	
	/**
	 * A Mock class for using a test configuration for the category.
	 * Assumes the class 'Category' works. 
	 * @author ggazzi
	 */
	private class MockCategory extends Category {	
		public MockCategory() {
			super(categ0, "aCategory");
			
			setRange(catRange);
			setRingtoneUri(catRingtone);
			setVibration(catVibrate);
			setMessage(catMessage);

			setEndTime(catEndH, catEndM);
			setBeginTime(catBeginH, catBeginM);
			
			setOnSunday(catOnDayOfWeek);
			setOnMonday(catOnDayOfWeek);
			setOnTuesday(catOnDayOfWeek);
			setOnWednesday(catOnDayOfWeek);
			setOnThursday(catOnDayOfWeek);
			setOnFriday(catOnDayOfWeek);
			setOnSaturday(catOnDayOfWeek);
		}
	}
	
	/**
	 * A mock class for the CategoryDB, which simply
	 * returns the same MockCategory (instantiated on construction).
	 * @author ggazzi
	 */
	private class MockCategoryDB extends CategoryDBManager {
		
		private Category categ;

		public MockCategoryDB() {
			categ = new MockCategory(); 
		}
		
		@Override
		public Category findById(int id) {
			return categ;
		}
		
	}
	
	
	/*
	 * Test Case methods
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		POI = new PointOfInterest(id0, lat0, lon0, name0, categ0, new MockCategoryDB());
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
	 * {@link com.alarmmap.data.PointOfInterest}
	 */
	public void testEssentialProperties() {
		assertEquals("The retrieved ID was wrong.", id0, POI.getId());
		assertEquals("The retrieved lat was wrong.", lat0, POI.getLat());
		assertEquals("The retrieved lat was wrong.", lon0, POI.getLon());
		assertEquals("The retrieved name was wrong.", name0, POI.getName());
		assertEquals("The retrieved category name was wrong.", categ0, POI.getCategoryId());
		
	}
	
	/*
	 * Tests for the range property.
	 * 
	 * Must look at the category by default;
	 * Must properly set the value;
	 * Must properly unset the value;
	 * Must not set an invalid value.
	 */
	public void testRangeInitial() {
		
		assertEquals("Must obtain correct initial range from the category.",
				catRange, POI.getRange());
		
	}
	public void testRangeSet() {
		
		try {
			POI.setRange(range);
			assertEquals("Must retrieve the exact range just set.", range, POI.getRange());
		}
		catch (IllegalArgumentException e) {
			fail("Must accept non-negative ranges.");
		}
		
	}
	public void testRangeReset() {
		
		POI.setRange(range);
		POI.resetRange();
		
		assertEquals("Must retrieve the range from category after unsetting it.", 
				catRange, POI.getRange());
		
	}
	public void testRangeRejectNegative() {
		
		try {
			POI.setRange(-1);
			fail("Must not accept a negative range.");
		} catch (IllegalArgumentException e) { }
		
	}

	/*
	 * Tests for the vibration property.
	 * 
	 * Must look at the category by default;
	 * Must properly set the value;
	 * Must properly unset the value.
	 */
	public void testVibrationInitial() {
		
		assertEquals("Must obtain correct initial vibration from the category.",
				catVibrate, POI.willVibrate());
	}
	public void testVibrationSet() {
		
		POI.setVibration(vibrate);
		
		assertEquals("Must retrieve the exact vibration just set.", 
				vibrate, POI.willVibrate());
		
	}
	public void testVibrationReset() {
		
		POI.setVibration(vibrate);
		POI.resetVibration();
		
		assertEquals("Must retrieve the vibration from category after unsetting it.", 
				catVibrate, POI.willVibrate());
		
	}
	
	/*
	 * Tests for the ringtoneUri property.
	 * 
	 * Must look at the category by default;
	 * Must properly set the value;
	 * Must properly unset the value;
	 * Must not set an invalid value.
	 */
	public void testRingtoneUriInitial() {
		
		assertEquals("Must obtain correct initial ringtone from the category.",
				catRingtone, POI.getRingtoneUri());
		
	}
	public void testRingtoneUriSet() {
		
		try {
			POI.setRingtoneUri(ringtone);
			assertEquals("Must retrieve the exact ringtone just set.", 
					ringtone, POI.getRingtoneUri());
		}
		catch (IllegalArgumentException e) {
			fail("Must accept all non-null ringtone URIs.");
		}
		
	}
	public void testRingtoneUriReset() {
		
		POI.setRingtoneUri(ringtone);
		POI.resetRingtoneUri();
		
		assertEquals("Must retrieve the ringtone from category after unsetting it.", 
				catRingtone, POI.getRingtoneUri());
		
	}
	public void testRingtoneUriRejectNull() {
		
		try {
			POI.setRingtoneUri(null);
			fail("Must reject a null ringtone URI.");
		} catch (IllegalArgumentException e) { }
		
	}

	/*
	 * Tests for the message property.
	 * 
	 * Must look at the category by default;
	 * Must properly set the value;
	 * Must properly unset the value;
	 * Must not set an invalid value.
	 */
	public void testMessageInitial() {
		
		assertEquals("Must obtain correct initial message from the category.",
				catMessage, POI.getMessage());
		
	}
	public void testMessageSet() {
		
		try {
			POI.setMessage(message);
			assertEquals("Must retrieve the exact message just set.", 
					message, POI.getMessage());
		}
		catch (IllegalArgumentException e) {
			fail("Must accept all non-null messages.");
		}
		
	}
	public void testMessageReset() {
		
		POI.setMessage(message);
		POI.resetMessage();
		
		assertEquals("Must retrieve the message from category after unsetting it.", 
				catMessage, POI.getMessage());
		
	}
	public void testMessageRejectNull() {
		
		try {
			POI.setMessage(null);
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
	public void testBeginTimeInitial() {
		
		assertEquals("Must obtain correct initial beginH from the category.",
				catBeginH, POI.getBeginHour());
		assertEquals("Must obtain correct initial beginM from the category.",
				catBeginM, POI.getBeginMinute());
		
	}
	public void testEndTimeInitial() {
		
		assertEquals("Must obtain correct initial endH from the category.",
				catEndH, POI.getEndHour());
		assertEquals("Must obtain correct initial endM from the category.",
				catEndM, POI.getEndMinute());
		
	}
	
	public void testBeginTimeSet() {
		
		POI.setBeginTime(beginH, beginM);
		
		assertEquals("Must retrieve the beginH just set.",
				beginH, POI.getBeginHour());
		assertEquals("Must retrieve the beginM just set.",
				beginM, POI.getBeginMinute());
		
	}
	public void testEndTimeSet() {
		
		POI.setEndTime(endH, endM);
		
		assertEquals("Must retrieve the endH just set.",
				endH, POI.getEndHour());
		assertEquals("Must retrieve the endM just set.",
				endM, POI.getEndMinute());
		
	}
		
	public void testBeginTimeReset() {
		
		POI.setBeginTime(beginH, beginM);
		POI.resetBeginTime();
		
		assertEquals("Must obtain correct beginH after resetting.",
				catBeginH, POI.getBeginHour());
		assertEquals("Must obtain correct beginM after resetting.",
				catBeginM, POI.getBeginMinute());
		
	}
	public void testEndTimeReset() {
		
		POI.setEndTime(endH, endM);
		POI.resetEndTime();
		
		assertEquals("Must obtain correct endH after resetting.",
				catEndH, POI.getEndHour());
		assertEquals("Must obtain correct endM after resetting.",
				catEndM, POI.getEndMinute());
		
	}
		
	private static final int safeH = (beginH + endH) / 2;
	private static final int safeM = endM - 1;
	public void testBeginTimeRejectNegative() {
		
		try {
			POI.setBeginTime(safeH, -1);
			fail("Must reject negative times.");
		} catch (IllegalArgumentException e) { }
		
		try {
			POI.setBeginTime(-1, safeM);
			fail("Must reject negative times.");
		} catch (IllegalArgumentException e) { }
		
		try {
			POI.setBeginTime(-1, -1);
			fail("Must reject negative times.");
		} catch (IllegalArgumentException e) { }
		
	}
	public void testEndTimeRejectNegative() {
		
		try {
			POI.setEndTime(safeH, -1);
			fail("Must reject negative times.");
		} catch (IllegalArgumentException e) { }
		
		try {
			POI.setEndTime(-1, safeM);
			fail("Must reject negative times.");
		} catch (IllegalArgumentException e) { }
		
		try {
			POI.setEndTime(-1, -1);
			fail("Must reject negative times.");
		} catch (IllegalArgumentException e) { }
		
	}
	
	public void testBeginTimeRejectReversed() {
		try {
			POI.setBeginTime(POI.getEndHour()+1, POI.getEndMinute());
			fail("Must reject reversed begin/end times.");
		} catch (ReversedTimesException e) { }

		try {
			POI.setBeginTime(POI.getEndHour(), POI.getEndMinute()+1);
			fail("Must reject reversed begin/end times.");
		} catch (ReversedTimesException e) { }
		
		try {
			POI.setBeginTime(POI.getEndHour(), POI.getEndMinute());
			fail("Must reject equal begin/end times.");
		} catch (ReversedTimesException e) { }
		
	}
	public void testEndTimeRejectReversed() {
		
		try {
			POI.setEndTime(POI.getBeginHour()-1, POI.getBeginMinute());
			fail("Must reject reversed begin/end times.");
		} catch (ReversedTimesException e) { }

		try {
			POI.setEndTime(POI.getBeginHour(), POI.getBeginMinute()-1);
			fail("Must reject reversed begin/end times.");
		} catch (ReversedTimesException e) { }
		
		try {
			POI.setEndTime(POI.getBeginHour(), POI.getBeginMinute());
			fail("Must reject equal begin/end times.");
		} catch (ReversedTimesException e) { }
		
	}
	
	public void testBeginTimeBoundaryCases() {
		// TODO make tests
	}
	public void testEndTimeBoundaryCases() {
		// TODO make tests
	}
	
	/*
	 * Tests for the 'day of week' properties.
	 * 
	 * Must look at the category by default;
	 * Must have correct default values when using our own schedule;
	 * Must properly set the values;
	 * Must not reset the values when not using our own schedule;
	 * Resetting must mean not using own schedule.
	 */
	public void testDaysOfWeekInitial() {
		
		assertEquals("Must obtain correct initial 'sunday' from the category.",
				catOnDayOfWeek, POI.isOnSunday());
		assertEquals("Must obtain correct initial 'monday' from the category.",
				catOnDayOfWeek, POI.isOnMonday());
		assertEquals("Must obtain correct initial 'tuesday' from the category.",
				catOnDayOfWeek, POI.isOnTuesday());
		assertEquals("Must obtain correct initial 'wednesday' from the category.",
				catOnDayOfWeek, POI.isOnWednesday());
		assertEquals("Must obtain correct initial 'thursday' from the category.",
				catOnDayOfWeek, POI.isOnThursday());
		assertEquals("Must obtain correct initial 'friday' from the category.",
				catOnDayOfWeek, POI.isOnFriday());
		assertEquals("Must obtain correct initial 'saturday' from the category.",
				catOnDayOfWeek, POI.isOnSaturday());
		
	}
	public void testDaysOfWeekDefaultValues() {
		
		POI.useOwnSchedule(true);
		
		assertEquals("Must obtain default 'sunday' after adopting own schedule.",
				PointOfInterest.defaultOnDayOfWeek, POI.isOnSunday());
		assertEquals("Must obtain default 'monday' after adopting own schedule.",
				PointOfInterest.defaultOnDayOfWeek, POI.isOnMonday());
		assertEquals("Must obtain default 'tuesday' after adopting own schedule.",
				PointOfInterest.defaultOnDayOfWeek, POI.isOnTuesday());
		assertEquals("Must obtain default 'wednesday' after adopting own schedule.",
				PointOfInterest.defaultOnDayOfWeek, POI.isOnWednesday());
		assertEquals("Must obtain default 'thursday' after adopting own schedule.",
				PointOfInterest.defaultOnDayOfWeek, POI.isOnThursday());
		assertEquals("Must obtain default 'friday' after adopting own schedule.",
				PointOfInterest.defaultOnDayOfWeek, POI.isOnFriday());
		assertEquals("Must obtain default 'saturday' after adopting own schedule.",
				PointOfInterest.defaultOnDayOfWeek, POI.isOnSaturday());
		
	}
	public void testDaysOfWeekSet() {
		
		// Make sure the category is different from the POI
		Category categ = POI.getCategory();
		categ.setOnSunday(!onDayOfWeek);
		categ.setOnMonday(!onDayOfWeek);
		categ.setOnTuesday(!onDayOfWeek);
		categ.setOnWednesday(!onDayOfWeek);
		categ.setOnThursday(!onDayOfWeek);
		categ.setOnFriday(!onDayOfWeek);
		categ.setOnSaturday(!onDayOfWeek);
		
		POI.useOwnSchedule(false);
		POI.setOnSunday(onDayOfWeek);
		assertEquals("Must obtain correct 'sunday' after setting.",
				onDayOfWeek, POI.isOnSunday());

		POI.useOwnSchedule(false);
		POI.setOnMonday(onDayOfWeek);
		assertEquals("Must obtain correct 'monday' after setting.",
				onDayOfWeek, POI.isOnMonday());

		POI.useOwnSchedule(false);
		POI.setOnTuesday(onDayOfWeek);
		assertEquals("Must obtain correct 'tuesday' after setting.",
				onDayOfWeek, POI.isOnTuesday());

		POI.useOwnSchedule(false);
		POI.setOnWednesday(onDayOfWeek);
		assertEquals("Must obtain correct 'wednesday' after setting.",
				onDayOfWeek, POI.isOnWednesday());

		POI.useOwnSchedule(false);
		POI.setOnThursday(onDayOfWeek);
		assertEquals("Must obtain correct 'thursday' after setting.",
				onDayOfWeek, POI.isOnThursday());

		POI.useOwnSchedule(false);
		POI.setOnFriday(onDayOfWeek);
		assertEquals("Must obtain correct 'friday' after setting.",
				onDayOfWeek, POI.isOnFriday());

		POI.useOwnSchedule(false);
		POI.setOnSaturday(onDayOfWeek);
		assertEquals("Must obtain correct 'saturday' after setting.",
				onDayOfWeek, POI.isOnSaturday());
		
	}
	public void testDaysOfWeekNoReset() {	
		Category categ = POI.getCategory();
		categ.setOnSunday(!onDayOfWeek);
		categ.setOnMonday(!onDayOfWeek);
		categ.setOnTuesday(!onDayOfWeek);
		categ.setOnWednesday(!onDayOfWeek);
		categ.setOnThursday(!onDayOfWeek);
		categ.setOnFriday(!onDayOfWeek);
		categ.setOnSaturday(!onDayOfWeek);
		
		POI.setOnSunday(onDayOfWeek);
		POI.setOnMonday(onDayOfWeek);
		POI.setOnTuesday(onDayOfWeek);
		POI.setOnWednesday(onDayOfWeek);
		POI.setOnThursday(onDayOfWeek);
		POI.setOnFriday(onDayOfWeek);
		POI.setOnSaturday(onDayOfWeek);
		
		POI.useOwnSchedule(false);
		POI.useOwnSchedule(true);
		
		assertEquals("Must obtain correct 'sunday' after resetting 'useOwnSchedule'.",
				onDayOfWeek, POI.isOnSunday());
		assertEquals("Must obtain correct 'monday' after resetting 'useOwnSchedule'.",
				onDayOfWeek, POI.isOnMonday());
		assertEquals("Must obtain correct 'tuesday' after resetting 'useOwnSchedule'.",
				onDayOfWeek, POI.isOnTuesday());
		assertEquals("Must obtain correct 'wednesday' after resetting 'useOwnSchedule'.",
				onDayOfWeek, POI.isOnWednesday());
		assertEquals("Must obtain correct 'thursday' after resetting 'useOwnSchedule'.",
				onDayOfWeek, POI.isOnThursday());
		assertEquals("Must obtain correct 'friday' after resetting 'useOwnSchedule'.",
				onDayOfWeek, POI.isOnFriday());
		assertEquals("Must obtain correct 'saturday' after resetting 'useOwnSchedule'.",
				onDayOfWeek, POI.isOnSaturday());
	}
	public void testDaysOfWeekResetToCategory() {
		
		// Makes sure category is different from the POI
		Category categ = POI.getCategory();
		categ.setOnSunday(!onDayOfWeek);
		categ.setOnMonday(!onDayOfWeek);
		categ.setOnTuesday(!onDayOfWeek);
		categ.setOnWednesday(!onDayOfWeek);
		categ.setOnThursday(!onDayOfWeek);
		categ.setOnFriday(!onDayOfWeek);
		categ.setOnSaturday(!onDayOfWeek);
		
		POI.setOnSunday(onDayOfWeek);
		POI.setOnMonday(onDayOfWeek);
		POI.setOnTuesday(onDayOfWeek);
		POI.setOnWednesday(onDayOfWeek);
		POI.setOnThursday(onDayOfWeek);
		POI.setOnFriday(onDayOfWeek);
		POI.setOnSaturday(onDayOfWeek);
		
		POI.useOwnSchedule(false);
		
		assertEquals("Must obtain correct 'sunday' after setting 'useOwnSchedule' to false.",
				categ.isOnSunday(), POI.isOnSunday());
		assertEquals("Must obtain correct 'monday' after setting 'useOwnSchedule' to false.",
				categ.isOnMonday(), POI.isOnMonday());
		assertEquals("Must obtain correct 'tuesday' after setting 'useOwnSchedule' to false.",
				categ.isOnTuesday(), POI.isOnTuesday());
		assertEquals("Must obtain correct 'wednesday' after setting 'useOwnSchedule' to false.",
				categ.isOnWednesday(), POI.isOnWednesday());
		assertEquals("Must obtain correct 'thursday' after setting 'useOwnSchedule' to false.",
				categ.isOnThursday(), POI.isOnThursday());
		assertEquals("Must obtain correct 'friday' after setting 'useOwnSchedule' to false.",
				categ.isOnFriday(), POI.isOnFriday());
		assertEquals("Must obtain correct 'saturday' after setting 'useOwnSchedule' to false.",
				categ.isOnSaturday(), POI.isOnSaturday());
	}
	public void testDaysOfWeekResetValues() {
		
		// Makes sure category is different from the POI
		Category categ = POI.getCategory();
		categ.setOnSunday(!onDayOfWeek);
		categ.setOnMonday(!onDayOfWeek);
		categ.setOnTuesday(!onDayOfWeek);
		categ.setOnWednesday(!onDayOfWeek);
		categ.setOnThursday(!onDayOfWeek);
		categ.setOnFriday(!onDayOfWeek);
		categ.setOnSaturday(!onDayOfWeek);
		
		POI.setOnSunday(onDayOfWeek);
		POI.setOnMonday(onDayOfWeek);
		POI.setOnTuesday(onDayOfWeek);
		POI.setOnWednesday(onDayOfWeek);
		POI.setOnThursday(onDayOfWeek);
		POI.setOnFriday(onDayOfWeek);
		POI.setOnSaturday(onDayOfWeek);
		
		POI.resetDaysOfWeek();
		
		assertEquals("Must obtain correct 'sunday' after resetting days of week.",
				categ.isOnSunday(), POI.isOnSunday());
		assertEquals("Must obtain correct 'monday' after resetting days of week.",
				categ.isOnMonday(), POI.isOnMonday());
		assertEquals("Must obtain correct 'tuesday' after resetting days of week.",
				categ.isOnTuesday(), POI.isOnTuesday());
		assertEquals("Must obtain correct 'wednesday' after resetting days of week.",
				categ.isOnWednesday(), POI.isOnWednesday());
		assertEquals("Must obtain correct 'thursday' after resetting days of week.",
				categ.isOnThursday(), POI.isOnThursday());
		assertEquals("Must obtain correct 'friday' after resetting days of week.",
				categ.isOnFriday(), POI.isOnFriday());
		assertEquals("Must obtain correct 'saturday' after resetting days of week.",
				categ.isOnSaturday(), POI.isOnSaturday());
	}
}
