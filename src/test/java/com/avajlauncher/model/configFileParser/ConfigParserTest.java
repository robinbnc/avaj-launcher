package com.avajlauncher.model.configFileParser;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.avajlauncher.model.coordinates.Coordinates;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConfigParserTest {

	@ParameterizedTest(name = "{0} should equal to {1}")
	@CsvSource({ "1,1", "25,25", "      42,42", "	254,254" })
	void testSuccessGetSimulationTime(String arg1, int arg2) {
		ConfigParser parserTest = new ConfigParser();

		parserTest.setLine(arg1);
		assertEquals(arg2, parserTest.getSimulationTime());
	}

	@ParameterizedTest(name = "{0} should equal to {1}")
	@CsvSource({
		"Baloon B1 2 3 20,Baloon",
		"Helicopter H2 22 33 44,Helicopter",
		"      JetPlane    	 J2     11     99      768,JetPlane",
		"	Helicopter    H4     223     23			 54  ,Helicopter",
		"	Helicopter    H4     223     23			   ,Helicopter"
	})
	void testSuccessGetType(String arg1, String arg2) {
		ConfigParser parserTest = new ConfigParser();

		parserTest.setLine(arg1);
		parserTest.tokenizeAirCraftLine();
		assertEquals(arg2, parserTest.getAircraftType());
	}

	@ParameterizedTest(name = "{0} should equal to {1}")
	@CsvSource({
		"Baloon B1 2 3 20,B1",
		"Helicopter H2 22 33 44,H2",
		"      JetPlane    	 J2     11     99      768,J2",
		"	Helicopter    H4     223     23			 54  ,H4"
	})
	void testSuccessGetName(String arg1, String arg2) {
		ConfigParser parserTest = new ConfigParser();

		parserTest.setLine(arg1);
		parserTest.tokenizeAirCraftLine();
		assertEquals(arg2, parserTest.getAircraftName());
	}

	@ParameterizedTest(name = "{0} should equal to {1}")
	@CsvSource({
		"Baloon B1 2 3 20,2,3,20",
		"Helicopter H2 22 33 44,22,33,44",
		"	Helicopter    H4     223     23			 54  ,223,23,54",
		"      JetPlane    	 J2     11     99      769,11,99,100",
	})
	void testSuccessGetCoordinates(String arg1, int expectedLong, int expectedLat, int expectedHeight) {
		ConfigParser parserTest = new ConfigParser();
		parserTest.setLine(arg1);
		parserTest.tokenizeAirCraftLine();
		Coordinates coordinatesTest = parserTest.getAircraftCoordinates();

		assertTrue(coordinatesTest.getLatitude() == expectedLat
					&& coordinatesTest.getLongitude() == expectedLong
					&& coordinatesTest.getHeight() == expectedHeight);
	}
}
