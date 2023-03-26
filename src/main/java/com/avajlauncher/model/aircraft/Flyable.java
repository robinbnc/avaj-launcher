package com.avajlauncher.model.aircraft;

import com.avajlauncher.model.tower.WeatherTower;

public interface Flyable {
	void updateConditions();
	void registerTower(WeatherTower p_weatherTower);
}