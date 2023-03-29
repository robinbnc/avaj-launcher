package com.avajlauncher.model.configFileParser;

public class ConfigError extends Config {
	public boolean isSimulationTimeLineCorrect() {
		return (
			this.isNumberOfTokenInSimulationLineCorrect()
			&& this.isSimulationTimeValidInt()
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

	public boolean isSimulationTimeValidInt() {
		try {
			Integer.parseInt(this.line);
		}
		catch (Exception e) {
			return (false);
		}
		return (true);
	}

	public boolean isAircraftLineCorrect() {
		return (
			this.isNumberOfTokenInAircraftLineCorrect()
			&& this.isLongitudeValidInt()
			&& this.isLatitudeValidInt()
			&& this.isHeightValidInt()
			&& this.isLongitudePositive()
			&& this.isLatitudePositive()
			&& this.isHeightPositive()
		);
	}

	public boolean isNumberOfTokenInAircraftLineCorrect() {
		String[] splittedLine = this.line.split("\\s+");
		return (splittedLine.length == 5);
	}

	public boolean isLongitudeValidInt() {
		try {
			Integer.parseInt(this.tokenizedLine.get("longitude"));
		}
		catch (Exception e) {
			return (false);
		}
		return (true);
	}

	public boolean isLatitudeValidInt() {
		try {
			Integer.parseInt(this.tokenizedLine.get("latitude"));
		}
		catch (Exception e) {
			return (false);
		}
		return (true);
	}

	public boolean isHeightValidInt() {
		try {
			Integer.parseInt(this.tokenizedLine.get("height"));
		}
		catch (Exception e) {
			return (false);
		}
		return (true);
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
