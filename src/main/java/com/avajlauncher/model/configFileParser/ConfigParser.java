package com.avajlauncher.model.configFileParser;

import com.avajlauncher.model.coordinates.Coordinates;


public class ConfigParser extends Config {
	public int getSimulationTime() {
		return (Integer.parseInt(this.line));
	}

	public String getAircraftType() {
		return (this.tokenizedLine.get("type"));
	}

	public String getAircraftName() {
		return (this.tokenizedLine.get("name"));
	}

	public Coordinates getAircraftCoordinates() {
		return (new Coordinates(
				Integer.parseInt(this.tokenizedLine.get("longitude")),
				Integer.parseInt(this.tokenizedLine.get("latitude")),
				Integer.parseInt(this.tokenizedLine.get("height"))
			)
		);
	}
}
