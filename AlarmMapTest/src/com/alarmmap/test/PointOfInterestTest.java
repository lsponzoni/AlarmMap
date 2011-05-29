package com.alarmmap.test;

import com.alarmmap.PointOfInterest;

import junit.framework.TestCase;

public class PointOfInterestTest extends TestCase {
	
	/*
	 * Some values used on the tests
	 */
	private static final int id0 = 0;
	private static final double lat0 = 0, lon0 = 0;
	private static final String name0 = "center!", categ0 = "aCategory!";
	
	private PointOfInterest POI;

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		POI = new PointOfInterest(id0, lat0, lon0, name0, categ0);
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	/**
	 * Test for the essential property getters of
	 * {@link com.alarmmap.PointOfInterest}
	 */
	public void testEssentialProperties() {
		assertEquals("The given ID was wrong.", id0, POI.getId());
		assertEquals("The given lat was wrong.", lat0, POI.getLat());
		assertEquals("The given lat was wrong.", lon0, POI.getLon());
		assertEquals("The given name was wrong.", name0, POI.getName());
		assertEquals("The given category was wrong.", categ0, POI.getCategoryName());
		
	}

}
