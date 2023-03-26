package com.avajlauncher.model.configFileParser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class ConfigTest {
	@Test
	void testSetLine() {
		String test = "test";
		Config testConfig = new Config();
		testConfig.setLine(test);

		assertEquals(test, testConfig.line);
	}

	@Test
	void testTokenizeAirCraftLine() {
		Map<String, String> expectedMap = new HashMap<String, String>();
		expectedMap.put("type", "Helicopter");
		expectedMap.put("name", "H1");
		expectedMap.put("longitude", "1");
		expectedMap.put("latitude", "1");
		expectedMap.put("height", "1");
		String line = "Helicopter H1 1 1 1";
		Config testConfig = new Config();

		testConfig.setLine(line);
		testConfig.tokenizeAirCraftLine();
		assertTrue(expectedMap.equals(testConfig.tokenizedLine));
	}
}
