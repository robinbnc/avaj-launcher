package com.avajlauncher.model.weather;

import com.avajlauncher.model.coordinates.Coordinates;

public class WeatherProvider {
	private static String[] weather = {"RAIN", "FOG", "SUN", "SNOW"};
	private static WeatherProvider weatherProvider = new WeatherProvider();

	private WeatherProvider() {}

	public static WeatherProvider getInstance() {
		return (weatherProvider);
	}

	public String getCurrentWeather(Coordinates p_coordinates) {
		int sumOfCoordinatesAttributes =
			p_coordinates.getLongitude()
			+ p_coordinates.getLatitude()
			+ p_coordinates.getHeight();
		return (weather[sumOfCoordinatesAttributes % 4]);
	}
} 