package com.avajlauncher;

import com.avajlauncher.JetPlane;
import com.avajlauncher.Coordinates;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


class JetPlaneTest {

	@ParameterizedTest(
		name = "testGetWeatherMessage(Coordinates({0}, {1}, {2})) should equal to {3}"
	)
	@CsvSource({
		"1,1,1,OMG! Winter is coming!",
		"124,332,98,Too hot in the cockpit.",
		"42,57,99,Too hot in the cockpit.",
		"10,20,30,It's raining. Better watch out for lightings.",
		"5,15,85,Flight delayed by Fog!",
		"7,14,49,Too hot in the cockpit.",
		"2,3,75,It's raining. Better watch out for lightings.",
		"11,22,33,Too hot in the cockpit.",
		"1,2,99,Too hot in the cockpit.",
		"6,12,48,Too hot in the cockpit.",
		"17,34,68,OMG! Winter is coming!",
		"4,8,96,It's raining. Better watch out for lightings.",
		"13,26,52,OMG! Winter is coming!"
	})
	void testGetWeatherMessage(int arg1, int arg2, int arg3,
							   String expectedResult)
	{
		JetPlane JetPlaneTest =
			new JetPlane(0, "JetPlane#B1", new Coordinates(arg1, arg2, arg3));
		JetPlaneTest.registerTower(new WeatherTower());
		assertEquals(expectedResult, JetPlaneTest.getWeatherMessage());
	}

	@ParameterizedTest(
	name = "testGetWeatherMessage(Coordinates({0}, {1}, {2})) should equal to {3}"
	)
	@CsvSource({
		"1234,5678,91,1234,5678,84",  //SNOW
		"9876,5432,10,9876,5442,12",  //SUN
		"1111,9999,50,1111,10004,50", //RAIN
		"4444,8888,20,4444,8893,20",  //RAIN
		"2468,1357,30,2468,1357,23",  //SNOW
		"7777,3332,80,7777,3333,80",  //FOG
		"5555,7777,69,5555,7778,69",  //FOG
		"8888,2222,60,8888,2232,62",  //SUN
		"9999,1112,40,9999,1112,33",  //SNOW
		"3333,7777,50,3333,7782,50",  //RAIN
		"3,7,5,3,7,-2",				  //SNOW
	})
	void testGetWeatherMessage(int arg1, int arg2, int arg3,
							   int expect1, int expect2, int expect3)
	{
		JetPlane JetPlaneTest =
			new JetPlane(0, "JetPlanee#B1", new Coordinates(arg1, arg2, arg3));
		JetPlaneTest.registerTower(new WeatherTower());
		JetPlaneTest.updateConditions();
		Coordinates testCoordinates = JetPlaneTest.coordinates;
		assertTrue(testCoordinates.getLongitude() == expect1 &&
			testCoordinates.getLatitude() == expect2 &&
			testCoordinates.getHeight() == expect3
		);
	}
}