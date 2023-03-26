package com.avajlauncher.model.tower;

import com.avajlauncher.model.coordinates.Coordinates;
import com.avajlauncher.model.weather.WeatherProvider;

public class WeatherTower extends Tower {
	public String	getWeather(Coordinates p_coordinates) {
		return (WeatherProvider.getInstance()
				.getCurrentWeather(p_coordinates)
		);
	}

	public void	changeWeather() {
		this.conditionChanged();
	}
} 