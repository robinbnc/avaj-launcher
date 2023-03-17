package com.avajlauncher;

import com.avajlauncher.Coordinates;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CoordinatesTest {

	@Test
	void testGetLongitudeIsEqualToLongitude() {
		Coordinates testCoordinates = new Coordinates(1, 2, 3);
		assertEquals(1, testCoordinates.getLongitude());
	}

	@Test
	void testGetLatitudeIsEqualToLatitude() {
		Coordinates testCoordinates = new Coordinates(1, 2, 3);
		assertEquals(2, testCoordinates.getLatitude());
	}

	@Test
	void testGetHeightIsEqualToLongitude() {
		Coordinates testCoordinates = new Coordinates(1, 2, 3);
		assertEquals(3, testCoordinates.getHeight());
	}
}