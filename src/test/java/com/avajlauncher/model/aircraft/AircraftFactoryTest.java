package com.avajlauncher;

import com.avajlauncher.Baloon;
import com.avajlauncher.Aircraft;
import com.avajlauncher.JetPlane;
import com.avajlauncher.Helicopter;
import com.avajlauncher.Coordinates;
import com.avajlauncher.AircraftFactory;
import com.avajlauncher.IncorrectAircraftType;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AircraftFactoryTest {

	@Test
	void testBaloonCreation() {
		try {
			Aircraft testBaloon =
				AircraftFactory.getInstance()
				.newAircraft("Baloon", "Baloon", new Coordinates(1, 1, 1));
			assertTrue(testBaloon instanceof Baloon);
		}
		catch (IncorrectAircraftType e) {
			fail("Should not have thrown any exception");
		}
	}

	@Test
	void testJetPlaneCreation() {
		try {
			Aircraft testJetPlane =
				AircraftFactory.getInstance()
				.newAircraft("JetPlane", "JetPlane", new Coordinates(1, 1, 1));
			assertTrue(testJetPlane instanceof JetPlane);
		}
		catch (IncorrectAircraftType e) {
			fail("Should not have thrown any exception");
		}

	}

	@Test
	void testHelicopterCreation() {
		try {
			Aircraft testHelicopter = 
				AircraftFactory.getInstance()
				.newAircraft("Helicopter", "Helicopter", new Coordinates(1, 1, 1));
			assertTrue(testHelicopter instanceof Helicopter);
		}
		catch (IncorrectAircraftType e) {
			fail("Should not have thrown any exception");
		}
	}

	@Test
	void testUnknownAircraft() {
		Exception exception = assertThrows(IncorrectAircraftType.class, () -> {
			AircraftFactory.getInstance()
			.newAircraft("Helicopterr", "Helicopter", new Coordinates(1, 1, 1));
		});

		String expectedMessage = "Error: incorrect aircraft type as first argument";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void testUnknownAircraftEmpty() {
		Exception exception = assertThrows(IncorrectAircraftType.class, () -> {
			AircraftFactory.getInstance()
			.newAircraft("", "Helicopter", new Coordinates(1, 1, 1));
		});

		String expectedMessage = "Error: incorrect aircraft type as first argument";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}
}