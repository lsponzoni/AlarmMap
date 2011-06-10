package com.alarmmap.test;

import junit.framework.TestCase;

import com.alarmmap.data.Category;
import com.alarmmap.data.CategoryDBManager;
import com.alarmmap.data.PointOfInterest;
import com.alarmmap.data.ReversedTimesException;

public class PointOfInterestTest extends TestCase {
	
	/*
	 * Some values used on the tests
	 */
	private static final int id0 = 0;
	private static final double lat0 = 0, lon0 = 0;
	private static final String name0 = "center!", categ0 = "aCategory!";

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
	
	private class MockCategory extends Category {	
		public MockCategory() {
			setRange(catRange);
			setRingtoneUri(catRingtone);
			setVibration(catVibrate);
			setMessage(catMessage);
			
			setBeginTime(catBeginH, catBeginM);
			setEndTime(catEndH, catEndM);
			
			setOnSunday(catOnDayOfWeek);
			setOnMonday(catOnDayOfWeek);
			setOnTuesday(catOnDayOfWeek);
			setOnWednesday(catOnDayOfWeek);
			setOnThursday(catOnDayOfWeek);
			setOnFriday(catOnDayOfWeek);
			setOnSaturday(catOnDayOfWeek);
		}
	}

	static MockCategory category = null;
	
	private class MockCategoryDB extends CategoryDBManager {
		@Override
		public Category findByName(String name) {
			return category == null ? category = new MockCategory() : category;
		}
	}
	
	private PointOfInterest POI;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		POI = new PointOfInterest(id0, lat0, lon0, name0, categ0, new MockCategoryDB());
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	/**
	 * Test for the essential property getters of
	 * {@link com.alarmmap.data.PointOfInterest}
	 */
	public void testEssentialProperties() {
		assertEquals("The retrieved ID was wrong.", id0, POI.getId());
		assertEquals("The retrieved lat was wrong.", lat0, POI.getLat());
		assertEquals("The retrieved lat was wrong.", lon0, POI.getLon());
		assertEquals("The retrieved name was wrong.", name0, POI.getName());
		assertEquals("The retrieved category name was wrong.", categ0, POI.getCategoryName());
		
	}
	
	/**
	 * Test for the range property.
	 * 
	 * Must look at the category by default;
	 * Must properly set the value;
	 * Must properly unset the value;
	 * Must not set an invalid value.
	 */
	public void testRange() {
		
		assertEquals("Must obtain correct initial range from the category.", catRange, POI.getRange());

		try {
			POI.setRange(range);
			assertEquals("Must retrieve the exact range just set.", range, POI.getRange());
		} catch (IllegalArgumentException e) {
			fail("Must accept non-negative ranges.");
		}
		
		POI.unsetRange();
		assertEquals("Must retrieve the range from category after unsetting it.", catRange, POI.getRange());
		
		try {
			POI.setRange(-1);
			fail("Must not accept a negative range.");
		} catch (IllegalArgumentException e) { }
	}
	
	/**
	 * Test for the vibrate property.
	 * 
	 * Must look at the category by default;
	 * Must properly set the value;
	 * Must properly unset the value.
	 */
	public void testVibration() {
		
		assertEquals("Must obtain correct initial vibration from the category.",
				catVibrate, POI.willVibrate());

		POI.setVibration(vibrate);
		assertEquals("Must retrieve the exact vibration just set.", 
				vibrate, POI.willVibrate());
		
		POI.unsetVibration();
		assertEquals("Must retrieve the vibration from category after unsetting it.", 
				catVibrate, POI.willVibrate());
	}
	
	/**
	 * Test for the ringtoneUri property.
	 * 
	 * Must look at the category by default;
	 * Must properly set the value;
	 * Must properly unset the value;
	 * Must not set an invalid value.
	 */
	public void testRingtoneUri() {
		
		assertEquals("Must obtain correct initial ringtone from the category.",
				catRingtone, POI.getRingtoneUri());

		try {
			POI.setRingtoneUri(ringtone);
			assertEquals("Must retrieve the exact ringtone just set.", 
					ringtone, POI.getRingtoneUri());
		} catch (IllegalArgumentException e) {
			fail("Must accept all non-null ringtone URIs.");
		}
		
		POI.unsetRingtoneUri();
		assertEquals("Must retrieve the ringtone from category after unsetting it.", 
				catRingtone, POI.getRingtoneUri());
		
		try {
			POI.setRingtoneUri(null);
			fail("Must reject a null ringtone URI.");
		} catch (IllegalArgumentException e) { }
	}

	/**
	 * Test for the message property.
	 * 
	 * Must look at the category by default;
	 * Must properly set the value;
	 * Must properly unset the value;
	 * Must not set an invalid value.
	 */
	public void testMessage() {
		
		assertEquals("Must obtain correct initial message from the category.",
				catMessage, POI.getMessage());

		try {
			POI.setMessage(message);
			assertEquals("Must retrieve the exact message just set.", 
					message, POI.getMessage());
		} catch (IllegalArgumentException e) {
			fail("Must accept all non-null messages.");
		}
		
		POI.unsetMessage();
		assertEquals("Must retrieve the message from category after unsetting it.", 
				catMessage, POI.getMessage());
		
		try {
			POI.setMessage(null);
			fail("Must reject a null message.");
		} catch (IllegalArgumentException e) { }
	}
	

	/**
	 * Test for the begin/end time.
	 * 
	 * Must look at the category by default;
	 * Must properly set the value;
	 * Must properly unset the value;
	 * Must not set an invalid value;
	 * Must work with boundary times.
	 */
	public void testTime() {
		// TODO: implement the test for begin and end time.
		
		// Correct initial values
		assertEquals("Must obtain correct initial beginH from the category.",
				catBeginH, POI.getBeginHour());
		assertEquals("Must obtain correct initial beginM from the category.",
				catBeginM, POI.getBeginMinute());

		assertEquals("Must obtain correct initial endH from the category.",
				catEndH, POI.getEndHour());
		assertEquals("Must obtain correct initial endM from the category.",
				catEndM, POI.getEndMinute());
		
		// Set proper values
		POI.setBeginTime(beginH, beginM);
		assertEquals("Must retrieve the beginH just set.",
				beginH, POI.getBeginHour());
		assertEquals("Must retrieve the beginM just set.",
				beginM, POI.getBeginMinute());

		POI.setEndTime(endH, endM);
		assertEquals("Must retrieve the endH just set.",
				endH, POI.getEndHour());
		assertEquals("Must retrieve the endM just set.",
				endM, POI.getEndMinute());
		
		// Correct reset values
		POI.unsetBeginTime();
		assertEquals("Must obtain correct beginH after resetting.",
				catBeginH, POI.getBeginHour());
		assertEquals("Must obtain correct beginM after resetting.",
				catBeginM, POI.getBeginMinute());

		POI.unsetEndTime();
		assertEquals("Must obtain correct endH after resetting.",
				catEndH, POI.getEndHour());
		assertEquals("Must obtain correct endM after resetting.",
				catEndM, POI.getEndMinute());
		
		// Reject negative values
		int safeH = (beginH + endH) / 2;
		int safeM = endM - 1;
		
		try {
			POI.setBeginTime(safeH, -1);
			fail("Must reject negative times.");
		} catch (IllegalArgumentException e) { 
			// If had exception: worked!
		}
		
		try {
			POI.setBeginTime(-1, safeM);
			fail("Must reject negative times.");
		} catch (IllegalArgumentException e) { 
			// If had exception: worked!
		}
		
		try {
			POI.setBeginTime(-1, -1);
			fail("Must reject negative times.");
		} catch (IllegalArgumentException e) { 
			// If had exception: worked!
		}
		
		try {
			POI.setEndTime(safeH, -1);
			fail("Must reject negative times.");
		} catch (IllegalArgumentException e) { 
			// If had exception: worked!
		}
		
		try {
			POI.setEndTime(-1, safeM);
			fail("Must reject negative times.");
		} catch (IllegalArgumentException e) { 
			// If had exception: worked!
		}
		
		try {
			POI.setEndTime(-1, -1);
			fail("Must reject negative times.");
		} catch (IllegalArgumentException e) { 
			// If had exception: worked!
		}
		
		
		// Reject reversed begin/end times
		try {
			POI.setBeginTime(POI.getEndHour()+1, POI.getEndMinute());
			fail("Must reject reversed begin/end times.");
		} catch (ReversedTimesException e) {
			// If had exception: worked!
		}

		try {
			POI.setBeginTime(POI.getEndHour(), POI.getEndMinute()+1);
			fail("Must reject reversed begin/end times.");
		} catch (ReversedTimesException e) {
			// If had exception: worked!
		}
		try {
			POI.setBeginTime(POI.getEndHour(), POI.getEndMinute());
			fail("Must reject equal begin/end times.");
		} catch (ReversedTimesException e) {
			// If had exception: worked!
		}

		try {
			POI.setEndTime(POI.getBeginHour()-1, POI.getBeginMinute());
			fail("Must reject reversed begin/end times.");
		} catch (ReversedTimesException e) {
			// If had exception: worked!
		}

		try {
			POI.setEndTime(POI.getBeginHour(), POI.getBeginMinute()-1);
			fail("Must reject reversed begin/end times.");
		} catch (ReversedTimesException e) {
			// If had exception: worked!
		}
		
		try {
			POI.setEndTime(POI.getBeginHour(), POI.getBeginMinute());
			fail("Must reject equal begin/end times.");
		} catch (ReversedTimesException e) {
			// If had exception: worked!
		}
	}
	
	/**
	 * Test for the 'day of week' properties.
	 * 
	 * Must look at the category by default;
	 * Must properly set the values;
	 * Must properly unset the values.
	 */
	public void testDaysOfWeek() {
		
		// Correct initial values
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

		// Correct default values on 'reset'
		POI.useOwnSchedule(true);
		assertEquals("Must obtain default 'sunday' after resetting.",
				PointOfInterest.defaultOnDayOfWeek, POI.isOnSunday());
		assertEquals("Must obtain default 'monday' after resetting.",
				PointOfInterest.defaultOnDayOfWeek, POI.isOnMonday());
		assertEquals("Must obtain default 'tuesday' after resetting.",
				PointOfInterest.defaultOnDayOfWeek, POI.isOnTuesday());
		assertEquals("Must obtain default 'wednesday' after resetting.",
				PointOfInterest.defaultOnDayOfWeek, POI.isOnWednesday());
		assertEquals("Must obtain default 'thursday' after resetting.",
				PointOfInterest.defaultOnDayOfWeek, POI.isOnThursday());
		assertEquals("Must obtain default 'friday' after resetting.",
				PointOfInterest.defaultOnDayOfWeek, POI.isOnFriday());
		assertEquals("Must obtain default 'saturday' after resetting.",
				PointOfInterest.defaultOnDayOfWeek, POI.isOnSaturday());
		
		// Correct setting of values (which activates useOnSchedule)
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
		assertEquals("Must obtain correct 'sunday' after resetting.",
				onDayOfWeek, POI.isOnSunday());

		POI.useOwnSchedule(false);
		POI.setOnMonday(onDayOfWeek);
		assertEquals("Must obtain correct 'monday' after resetting.",
				onDayOfWeek, POI.isOnMonday());

		POI.useOwnSchedule(false);
		POI.setOnTuesday(onDayOfWeek);
		assertEquals("Must obtain correct 'tuesday' after resetting.",
				onDayOfWeek, POI.isOnTuesday());

		POI.useOwnSchedule(false);
		POI.setOnWednesday(onDayOfWeek);
		assertEquals("Must obtain correct 'wednesday' after resetting.",
				onDayOfWeek, POI.isOnWednesday());

		POI.useOwnSchedule(false);
		POI.setOnThursday(onDayOfWeek);
		assertEquals("Must obtain correct 'thursday' after resetting.",
				onDayOfWeek, POI.isOnThursday());

		POI.useOwnSchedule(false);
		POI.setOnFriday(onDayOfWeek);
		assertEquals("Must obtain correct 'friday' after resetting.",
				onDayOfWeek, POI.isOnFriday());

		POI.useOwnSchedule(false);
		POI.setOnSaturday(onDayOfWeek);
		assertEquals("Must obtain correct 'saturday' after resetting.",
				onDayOfWeek, POI.isOnSaturday());
	}
}
