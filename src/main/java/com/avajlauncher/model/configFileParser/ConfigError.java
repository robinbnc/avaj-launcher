package com.avajlauncher.model.configFileParser;

public class ConfigError extends Config {
	public boolean isSimulationTimeLineCorrect() {
		return (
			this.isNumberOfTokenInSimulationLineCorrect()
			&& this.isSimulationLinePositive()
		);
	}
	
	public boolean isNumberOfTokenInSimulationLineCorrect() {
		String[] splittedLine = this.line.split("\\s+");
		return (splittedLine.length == 1);
	}

	public boolean isSimulationLinePositive() {
		return (Integer.parseInt(this.line) >= 0);
	}

	public boolean isAircraftLineCorrect() {
		return (
			this.isNumberOfTokenInAircraftLineCorrect()
			&& this.isLongitudePositive()
			&& this.isLatitudePositive()
			&& this.isHeightPositive()
		);
	}

	public boolean isNumberOfTokenInAircraftLineCorrect() {
		String[] splittedLine = this.line.split("\\s+");
		return (splittedLine.length == 5);
	}

	public boolean isLongitudePositive() {
		return (Integer.parseInt(this.tokenizedLine.get("longitude")) >= 0);
	}

	public boolean isLatitudePositive() {
		return (Integer.parseInt(this.tokenizedLine.get("latitude")) >= 0);
	}

	public boolean isHeightPositive() {
		return (Integer.parseInt(this.tokenizedLine.get("height")) >= 0);
	}
}
