package com.avajlauncher;

import java.util.Map;
import com.avajlauncher.*;


public class AircraftFactory {
	private static AircraftFactory aircraftFactory = new AircraftFactory();
	private static long id = 0;

	private AircraftFactory() {}

	public static AircraftFactory getInstance() {
		return (aircraftFactory);
	}

	public Aircraft newAircraft(
		String p_type,
		String p_name,
		Coordinates p_coordinates) throws IncorrectAircraftType
	{
		switch (p_type) {
			case "Baloon":
				return (new Baloon(id++, p_name, p_coordinates));
			case "JetPlane":
				return (new JetPlane(id++, p_name, p_coordinates));
			case "Helicopter":
				return (new Helicopter(id++, p_name, p_coordinates));
			default:
				throw new IncorrectAircraftType(
					"Error: incorrect aircraft type as first argument"
				);
		}
	}
} 