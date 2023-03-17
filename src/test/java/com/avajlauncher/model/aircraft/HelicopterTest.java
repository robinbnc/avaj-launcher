package com.avajlauncher;

import com.avajlauncher.Helicopter;
import com.avajlauncher.Coordinates;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


class HelicopterTest {

	@ParameterizedTest(
		name = "testGetWeatherMessage(Coordinates({0}, {1}, {2})) should equal to {3}"
	)
	@CsvSource({
		"1,1,1,My rotor is going to freeze!",
		"124,332,98,This is hot.",
		"42,57,99,This is hot.",
		"10,20,30,Damn you rain! You messed up my rotor.",
		"5,15,85,I can't pilot. the Fog is too deep",
		"7,14,49,This is hot.",
		"2,3,75,Damn you rain! You messed up my rotor.",
		"11,22,33,This is hot.",
		"1,2,99,This is hot.",
		"6,12,48,This is hot.",
		"17,34,68,My rotor is going to freeze!",
		"4,8,96,Damn you rain! You messed up my rotor.",
		"13,26,52,My rotor is going to freeze!"
	})
	void testGetWeatherMessage(int arg1, int arg2, int arg3, String expectedResult) {
		Helicopter HelicopterTest =
			new Helicopter(0, "Helicopter#B1", new Coordinates(arg1, arg2, arg3));
		HelicopterTest.registerTower(new WeatherTower());
		assertEquals(expectedResult, HelicopterTest.getWeatherMessage());
	}

	@ParameterizedTest(
	name = "testGetWeatherMessage(Coordinates({0}, {1}, {2})) should equal to {3}"
	)
	@CsvSource({
		"1234,5678,91,1234,5678,79", //SNOW
		"9876,5432,10,9886,5432,12", //SUN
		"1111,9999,50,1116,9999,50", //RAIN
		"4444,8888,20,4449,8888,20", //RAIN
		"2468,1357,30,2468,1357,18", //SNOW
		"7777,3332,80,7778,3332,80", //FOG
		"5555,7777,69,5556,7777,69", //FOG
		"8888,2222,60,8898,2222,62", //SUN
		"9999,1112,40,9999,1112,28", //SNOW
		"3333,7777,50,3338,7777,50", //RAIN
		"3,7,5,3,7,-7",				 //SNOW
	})
	void testGetWeatherMessage(int arg1, int arg2, int arg3,
							   int expect1, int expect2, int expect3)
	{
		Helicopter HelicopterTest =
			new Helicopter(0, "Helicoptere#B1", new Coordinates(arg1, arg2, arg3));
		HelicopterTest.registerTower(new WeatherTower());
		HelicopterTest.updateConditions();
		Coordinates testCoordinates = HelicopterTest.coordinates;
		assertTrue(testCoordinates.getLongitude() == expect1 &&
			testCoordinates.getLatitude() == expect2 &&
			testCoordinates.getHeight() == expect3
		);
	}
}