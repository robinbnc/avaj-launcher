package com.avajlauncher;

import com.avajlauncher.Tower;
import com.avajlauncher.Coordinates;

public class WeatherTower extends Tower {
	public String	getWeather(Coordinates p_coordinates) {
		return (WeatherProvider.getInstance()
				.getCurrentWeather(p_coordinates)
		);
	}

	public void	changeWeather() {

	}
} 