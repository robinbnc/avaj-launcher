package com.avajlauncher.model.weather;

import com.avajlauncher.model.coordinates.Coordinates;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WeatherProviderTest {

	@ParameterizedTest(name = "getCurrentWeather(Coordinates({0}, {1}, {2})) should equal to {3}")
	@CsvSource({
		"1,1,1,SNOW",		"124,332,98,SUN",
		"42,57,99,SUN",		"10,20,30,RAIN",
		"5,15,85,FOG",		"7,14,49,SUN",
		"2,3,75,RAIN",		"11,22,33,SUN",
		"1,2,99,SUN",		"6,12,48,SUN",
		"17,34,68,SNOW",	"4,8,96,RAIN",
		"13,26,52,SNOW"
	})
	void testGetCurrentWeather(int arg1, int arg2, int arg3, String expectedResult) {
		String weatherTest =
			WeatherProvider.getInstance()
			.getCurrentWeather(new Coordinates(arg1, arg2, arg3));
		assertEquals(weatherTest, expectedResult);
	}
}