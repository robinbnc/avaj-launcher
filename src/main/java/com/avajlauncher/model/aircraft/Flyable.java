package com.avajlauncher;

import com.avajlauncher.WeatherTower;

public interface Flyable {
	void updateConditions();
	void registerTower(WeatherTower p_weatherTower);
}