package com.alarmmap.test.data;

import junit.framework.Test;
import junit.framework.TestSuite;

public class DataTestSuite {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for com.alarmmap.test.data");
		
		//$JUnit-BEGIN$
		suite.addTestSuite(GlobalConfigTest.class);
		suite.addTestSuite(CategoryTest.class);
		suite.addTestSuite(PointOfInterestTest.class);
		//$JUnit-END$
		
		return suite;
	}

}
