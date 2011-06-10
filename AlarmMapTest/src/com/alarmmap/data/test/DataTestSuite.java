package com.alarmmap.data.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class DataTestSuite {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for com.alarmmap.data.test");
		
		//$JUnit-BEGIN$
		suite.addTestSuite(GlobalConfigTest.class);
		suite.addTestSuite(PointOfInterestTest.class);
		//$JUnit-END$
		
		return suite;
	}

}
