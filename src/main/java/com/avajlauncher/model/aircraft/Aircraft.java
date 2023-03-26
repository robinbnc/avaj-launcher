package com.avajlauncher.model.aircraft;

import com.avajlauncher.model.coordinates.Coordinates;

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

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Aircraft that = (Aircraft) obj;
		return (id == that.id
			&& this.name.equals(that.name)
			&& this.coordinates.equals(that.coordinates)
		);
	}

	public String getIndentifier() {
		return ("");
	}
	// Delete
	public void printData() {
		System.out.println("id: " + id + " name: " + name + " longitude " + coordinates.getLongitude() + " latitude " + coordinates.getLatitude() + " height " + coordinates.getHeight());
	}
}
