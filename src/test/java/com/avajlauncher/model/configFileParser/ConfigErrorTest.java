package com.avajlauncher.model.configFileParser;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConfigErrorTest {

	@ParameterizedTest(name = "isNumberOfTokenInAircraftLineCorrect() should equal to 1")
	@CsvSource({
		"1,true",
		"124,true",
		"      42,true",
		"	254 2,false",
		"283 qwen,false",
		"qwerqw 22,false",
		"1 we wer qwe,false"
	})
	void testSimulationNbOfTokens(String arg1, Boolean arg2) {
		ConfigError parserTest = new ConfigError();

		parserTest.setLine(arg1);
		assertEquals(arg2, parserTest.isNumberOfTokenInSimulationLineCorrect());
	}

	@ParameterizedTest(name = "isNumberOfTokenInAircraftLineCorrect() should equal to 1")
	@CsvSource({
		"1,true",
		"124,true",
		"42,true",
		"25,true",
		"-2,false",
		"-283,false",
		"-22,false",
		"-1,false"
	})
	void testSimulationPositive(String arg1, Boolean arg2) {
		ConfigError parserTest = new ConfigError();

		parserTest.setLine(arg1);
		assertEquals(arg2, parserTest.isSimulationLinePositive());
	}

	@ParameterizedTest(name = "isNumberOfTokenInAircraftLineCorrect() should equal to 1")
	@CsvSource({
		"123,true",
		"12341,true",
		"5,true",
		"-10,false",
		"-233,false",
		"-2232,false",
		"-11,false",
		"-11 qwer,false",
		"11 qwer,false"
	})
	void testIsSimulationTimeLineCorrect(String arg1, Boolean arg2) {
		ConfigError parserTest = new ConfigError();

		parserTest.setLine(arg1);
		assertEquals(arg2, parserTest.isSimulationTimeLineCorrect());
	}

	@ParameterizedTest(name = "isNumberOfTokenInAircraftLineCorrect() should equal to 1")
	@CsvSource({
		"Baloon B1 12 13 1,true",
		"JetPlane B1 1 3 1,true",
		"Helicopter H4 2 13 1,true",
		"Baloon B1 12 13 1 2,false",
		"Jetplane J1 12 13 1 1 234 12,false",
		"Helicopter B1 12 1 1 2,false",
		"Baloon B1 13 1,false",
		"Jetplane J1 12 ,false",
		"Helicopter 12 13 1,false",
		"B1 12 13 1,false",
		"Baloon Helicopter B1 12 13 1,false",
	})
	void testIsNumberOfTokenInAircraftLineCorrect(String arg1, Boolean arg2) {
		ConfigError parserTest = new ConfigError();

		parserTest.setLine(arg1);
		parserTest.tokenizeAirCraftLine();
		assertEquals(arg2, parserTest.isNumberOfTokenInAircraftLineCorrect());
	}

	@ParameterizedTest(name = "isNumberOfTokenInAircraftLineCorrect() should equal to 1")
	@CsvSource({
		"Baloon B1 12 13 1,true",
		"JetPlane B1 1 3 1,true",
		"Helicopter H4 2 13 1,true",
		"Baloon B1 -12 13 1,false",
		"JetPlane B1 -1 3 1,false",
		"Helicopter H4 -1223 123 4,false",
		"Baloon B1 -13232 13 1,false",
		"JetPlane B1 -1234121 3 1,false",
		"Helicopter H4 -813742 85613 1,false",

	})
	void testIsLongitudePositive(String arg1, Boolean arg2) {
		ConfigError parserTest = new ConfigError();

		parserTest.setLine(arg1);
		parserTest.tokenizeAirCraftLine();
		assertEquals(arg2, parserTest.isLongitudePositive());
	}

	@ParameterizedTest(name = "isNumberOfTokenInAircraftLineCorrect() should equal to 1")
	@CsvSource({
		"Baloon B1 534 87 14,true",
		"JetPlane B1 16 3 1,true",
		"Helicopter H4 98 113 18,true",
		"Baloon B1 12 -13 1,false",
		"JetPlane B1 1 -3 1,false",
		"Helicopter H4 1223 -123 4,false",
		"Baloon B1 13232 -134 1,false",
		"JetPlane B1 1234121 -67 1,false",
		"Helicopter H4 813742 -85613 1,false",

	})
	void testIsLatitudePositive(String arg1, Boolean arg2) {
		ConfigError parserTest = new ConfigError();

		parserTest.setLine(arg1);
		parserTest.tokenizeAirCraftLine();
		assertEquals(arg2, parserTest.isLatitudePositive());
	}

	@ParameterizedTest(name = "isNumberOfTokenInAircraftLineCorrect() should equal to 1")
	@CsvSource({
		"Baloon B1 534 87 14,true",
		"JetPlane B1 16 3 1,true",
		"Helicopter H4 98 113 138,true",
		"Baloon B1 12 13 -1,false",
		"JetPlane B1 1 3 -43,false",
		"Helicopter H4 1223 123 -234,false",
		"Baloon B1 13232 134 -9584,false",
		"JetPlane B1 1234121 67 -42, false",
		"Helicopter H4 813742 85613 -857,false",

	})
	void testIsHeightPositive(String arg1, Boolean arg2) {
		ConfigError parserTest = new ConfigError();

		parserTest.setLine(arg1);
		parserTest.tokenizeAirCraftLine();
		assertEquals(arg2, parserTest.isHeightPositive());
	}
}
