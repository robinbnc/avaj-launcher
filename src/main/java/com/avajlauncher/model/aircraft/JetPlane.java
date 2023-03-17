package com.avajlauncher;

import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import static java.lang.System.out;

import com.avajlauncher.Flyable;
import com.avajlauncher.Aircraft;
import com.avajlauncher.Coordinates;
import com.avajlauncher.WeatherProvider;

public class JetPlane extends Aircraft implements Flyable {
	private WeatherTower weatherTower;
	private static final Map<String, String> weatherMessages;
    static {
		Map<String, String> aMap = new HashMap<String, String>();;
		aMap.put("RAIN", "It's raining. Better watch out for lightings.");
		aMap.put("FOG", "Flight delayed by Fog!");
		aMap.put("SUN", "Too hot in the cockpit.");
		aMap.put("SNOW", "OMG! Winter is coming!");
		weatherMessages = Collections.unmodifiableMap(aMap);
    }	

	public JetPlane(long p_id, String p_name, Coordinates p_coordinates) {
		super(p_id, p_name, p_coordinates);
	}

	public void updateConditions(){
		switch (weatherTower.getWeather(this.coordinates))
		{
			case "RAIN":
				this.setCoordinates(new Coordinates(
					this.coordinates.getLongitude(),
					this.coordinates.getLatitude() + 5,
					this.coordinates.getHeight()
				));
				break ;
			case "FOG":
				this.setCoordinates(new Coordinates(
					this.coordinates.getLongitude(),
					this.coordinates.getLatitude() + 1,
					this.coordinates.getHeight()
				));
				break ;
			case "SNOW":
				this.setCoordinates(new Coordinates(
					this.coordinates.getLongitude(),
					this.coordinates.getLatitude(),
					this.coordinates.getHeight() - 7
				));
				break ;
			case "SUN":
				this.setCoordinates(new Coordinates(
					this.coordinates.getLongitude(),
					this.coordinates.getLatitude() + 10,
					this.coordinates.getHeight() + 2
				));
				break ;
			
		}
	}

	public String getWeatherMessage() {
		return (
			weatherMessages.get(
				weatherTower.getWeather(this.coordinates)
			)
		);
	}

	public void registerTower(WeatherTower p_weatherTower) {
		this.weatherTower = p_weatherTower;
	}
}