package com.avajlauncher.model.coordinates;

public class Coordinates {
	protected int	longitude;
	protected int	latitude;
	protected int	height;

	public Coordinates(int	p_longitude, int p_latitude, int p_height) {
		if (p_height > 100) {
			p_height = 100;
		}
		this.longitude	= p_longitude;
		this.latitude	= p_latitude;
		this.height		= p_height;
	}

	public int	getLongitude() {
		return (this.longitude);
	}

	public int	getLatitude() {
		return (this.latitude);
	}

	public int	getHeight() {
		return (this.height);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Coordinates that = (Coordinates) obj;
		return (longitude == that.longitude
			&& latitude == that.latitude
			&& height == that.height
		);
	}
}