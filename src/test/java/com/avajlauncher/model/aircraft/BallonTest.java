package com.avajlauncher;

import com.avajlauncher.Baloon;
import com.avajlauncher.Coordinates;
import com.avajlauncher.WeatherTower;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


class BaloonTest {

	@ParameterizedTest(
		name = "testGetWeatherMessage(Coordinates({0}, {1}, {2})) should equal to {3}"
	)
	@CsvSource({
		"1,1,1,It's snowing. We're gonna crash.",
		"124,332,98,Let's enjoy the good weather and take some pics.",
		"42,57,99,Let's enjoy the good weather and take some pics.",
		"10,20,30,Damn you rain! You messed up my baloon.",
		"5,15,85,I can't see anything. Too much Fog",
		"7,14,49,Let's enjoy the good weather and take some pics.",
		"2,3,75,Damn you rain! You messed up my baloon.",
		"11,22,33,Let's enjoy the good weather and take some pics.",
		"1,2,99,Let's enjoy the good weather and take some pics.",
		"6,12,48,Let's enjoy the good weather and take some pics.",
		"17,34,68,It's snowing. We're gonna crash.",
		"4,8,96,Damn you rain! You messed up my baloon.",
		"13,26,52,It's snowing. We're gonna crash."
	})
	void testGetWeatherMessage(int arg1, int arg2, int arg3, String expectedResult) {
		Baloon baloonTest =
			new Baloon(0, "Baloon#B1", new Coordinates(arg1, arg2, arg3));
		baloonTest.registerTower(new WeatherTower());
		assertEquals(expectedResult, baloonTest.getWeatherMessage());
	}

	@ParameterizedTest(
	name = "testGetWeatherMessage(Coordinates({0}, {1}, {2})) should equal to {3}"
	)
	@CsvSource({
		"1234,5678,91,1234,5678,76", //SNOW
		"9876,5432,10,9878,5432,14", //SUN
		"1111,9999,50,1111,9999,45", //RAIN
		"4444,8888,20,4444,8888,15", //RAIN
		"2468,1357,30,2468,1357,15", //SNOW
		"7777,3332,80,7777,3332,77", //FOG
		"5555,7777,70,5557,7777,74", //FOG
		"8888,2222,60,8890,2222,64", //SUN
		"9999,1112,40,9999,1112,25", //SNOW
		"3333,7777,50,3333,7777,45", //RAIN
		"3,7,5,3,7,-10",			 //SNOW
	})
	void testGetWeatherMessage(int arg1, int arg2, int arg3,
							   int expect1, int expect2, int expect3)
	{
		Baloon baloonTest =
			new Baloon(0, "Baloone#B1", new Coordinates(arg1, arg2, arg3));
		baloonTest.registerTower(new WeatherTower());
		baloonTest.updateConditions();
		Coordinates testCoordinates = baloonTest.coordinates;
		assertTrue(testCoordinates.getLongitude() == expect1 &&
			testCoordinates.getLatitude() == expect2 &&
			testCoordinates.getHeight() == expect3
		);
	}
}