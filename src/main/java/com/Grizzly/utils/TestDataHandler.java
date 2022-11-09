package com.Grizzly.utils;

import java.util.HashMap;
import java.util.Map;

public class TestDataHandler {
	static Map<String, String> testDataInMap = new HashMap<String, String>();

	public static void setTestDataInMap(Map<String, String> testDataInMap) {
		TestDataHandler.testDataInMap = testDataInMap;
	}

	public static Map<String, String> getTestDataInMap() {
		return testDataInMap;
	}

}
