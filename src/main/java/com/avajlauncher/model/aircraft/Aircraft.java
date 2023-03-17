package com.avajlauncher;

import com.avajlauncher.Coordinates;

public class Aircraft {
	protected long			id;
	protected String		name;
	protected Coordinates	coordinates;

	Aircraft(long p_id, String p_name, Coordinates p_coordinates) {
		this.id = p_id;
		this.name = p_name;
		this.coordinates = p_coordinates;
	}

	void setCoordinates(Coordinates p_coordinates) {
		this.coordinates = p_coordinates;
	}
}