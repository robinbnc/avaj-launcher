package com.avajlauncher.model.aircraft;

import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

import com.avajlauncher.model.coordinates.Coordinates;
import com.avajlauncher.model.tower.WeatherTower;

public class Helicopter extends Aircraft implements Flyable {
	private WeatherTower weatherTower;
	private static final Map<String, String> weatherMessages;
    static {
		Map<String, String> aMap = new HashMap<String, String>();;
		aMap.put("RAIN", "Damn you rain! You messed up my rotor.");
		aMap.put("FOG", "I can't pilot. the Fog is too deep");
		aMap.put("SUN", "This is hot.");
		aMap.put("SNOW", "My rotor is going to freeze!");
		weatherMessages = Collections.unmodifiableMap(aMap);
    }	

	public Helicopter(long p_id, String p_name, Coordinates p_coordinates) {
		super(p_id, p_name, p_coordinates);
	}

	public void updateConditions() {
		this.printWeatherMessage();
		switch (weatherTower.getWeather(this.coordinates))
		{
			case "RAIN":
				this.setCoordinates(new Coordinates(
					this.coordinates.getLongitude() + 5,
					this.coordinates.getLatitude(),
					this.coordinates.getHeight()
				));
				break ;
			case "FOG":
				this.setCoordinates(new Coordinates(
					this.coordinates.getLongitude() + 1,
					this.coordinates.getLatitude(),
					this.coordinates.getHeight()
				));
				break ;
			case "SNOW":
				this.setCoordinates(new Coordinates(
					this.coordinates.getLongitude(),
					this.coordinates.getLatitude(),
					this.coordinates.getHeight() - 12
				));
				break ;
			case "SUN":
				this.setCoordinates(new Coordinates(
					this.coordinates.getLongitude() + 10,
					this.coordinates.getLatitude(),
					this.coordinates.getHeight() + 2
				));
				break ;
		}
		if (this.isLanding()) {
			this.printLanding();
			weatherTower.unregister(this);
		}
	}

	private void printWeatherMessage() {
		System.out.println(
			this.getIndentifier() + ": "
			+ this.getWeatherMessage()
		);
	}

	public String getWeatherMessage() {
		return (
			weatherMessages.get(
				weatherTower.getWeather(this.coordinates)
			)
		);
	}

	private boolean isLanding() {
		return (this.coordinates.getHeight() <= 0);
	}

	private void printLanding() {
		System.out.println(
			this.getIndentifier() + " Landing."
		);
	}

	@Override
	public String getIndentifier() {
		return ("Helicopter" + "#" + this.name + "(" + this.id + ")");
	}

	public void registerTower(WeatherTower p_weatherTower) {
		this.weatherTower = p_weatherTower;
	}
}