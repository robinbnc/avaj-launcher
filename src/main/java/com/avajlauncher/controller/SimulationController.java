package com.avajlauncher.controller;

import com.avajlauncher.model.tower.WeatherTower;

public class SimulationController {
	int simulationTime;
	WeatherTower weatherTower;

	public SimulationController(int p_simulationTime, WeatherTower p_weatherTower) {
		this.simulationTime = p_simulationTime;
		this.weatherTower = p_weatherTower;
	}

	public void runSimulation() {
		while (this.simulationTime > 0) {
			this.weatherTower.conditionChanged();
			this.simulationTime--;
		}
	}
}
