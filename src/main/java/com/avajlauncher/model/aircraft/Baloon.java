package com.avajlauncher.model.aircraft;

import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

import com.avajlauncher.model.coordinates.Coordinates;
import com.avajlauncher.model.tower.WeatherTower;

public class Baloon extends Aircraft implements Flyable {
	private WeatherTower	weatherTower;
	private static final Map<String, String> weatherMessages;
    static {
		Map<String, String> aMap = new HashMap<String, String>();;
		aMap.put("RAIN", "Damn you rain! You messed up my baloon.");
		aMap.put("FOG", "I can't see anything. Too much Fog");
		aMap.put("SUN", "Let's enjoy the good weather and take some pics.");
		aMap.put("SNOW", "It's snowing. We're gonna crash.");
		weatherMessages = Collections.unmodifiableMap(aMap);
    }	

	public Baloon(long p_id, String p_name, Coordinates p_coordinates) {
		super(p_id, p_name, p_coordinates);
	}

	public void updateConditions() {
		this.printWeatherMessage();
		switch (weatherTower.getWeather(this.coordinates))
		{
			case "RAIN":
				this.setCoordinates(new Coordinates(
					this.coordinates.getLongitude(),
					this.coordinates.getLatitude(),
					this.coordinates.getHeight() - 5
				));
				break ;
			case "FOG":
				this.setCoordinates(new Coordinates(
					this.coordinates.getLongitude(),
					this.coordinates.getLatitude(),
					this.coordinates.getHeight() - 3
				));
				break ;
			case "SNOW":
				this.setCoordinates(new Coordinates(
					this.coordinates.getLongitude(),
					this.coordinates.getLatitude(),
					this.coordinates.getHeight() - 15
				));
				break ;
			case "SUN":
				this.setCoordinates(new Coordinates(
					this.coordinates.getLongitude() + 2,
					this.coordinates.getLatitude(),
					this.coordinates.getHeight() + 4
				));
				break ;
		}
		if (this.isLanding()) {
			this.printLanding();
			this.weatherTower.unregister(this);
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
		return ("Baloon" + "#" + this.name + "(" + this.id + ")");
	}

	public void registerTower(WeatherTower p_weatherTower) {
		this.weatherTower = p_weatherTower;
	}
}