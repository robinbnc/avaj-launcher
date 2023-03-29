package com.avajlauncher.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.avajlauncher.model.aircraft.Baloon;
import com.avajlauncher.model.aircraft.JetPlane;
import com.avajlauncher.model.aircraft.Helicopter;
import com.avajlauncher.model.coordinates.Coordinates;
import com.avajlauncher.model.tower.WeatherTower;
import com.avajlauncher.utils.RedirectStdOut;

public class SimulationControllerFactoryTest {
	@Test
	void testNewSimulationControllerSuccess() {
		RedirectStdOut redirectOut = new RedirectStdOut();
		redirectOut.redirectOutToFile("nul");
		try {
			SimulationControllerFactory scFactory = new SimulationControllerFactory("./src/test/java/com/avajlauncher/controller/configFilesTest/succesConfig0.txt");
			SimulationController simulationController = scFactory.newSimulationController();
			WeatherTower weatherTower = new WeatherTower();
			weatherTower.register(new Baloon(0, "B1", new Coordinates(2, 3, 20)));
			weatherTower.register(new Baloon(1, "B2", new Coordinates(1, 8, 66)));
			weatherTower.register(new JetPlane(2, "J1", new Coordinates(23, 44, 32)));
			weatherTower.register(new Helicopter(3, "H1", new Coordinates(654, 33, 20)));
			weatherTower.register(new Helicopter(4, "H2", new Coordinates(22, 33, 44)));
			weatherTower.register(new Helicopter(5, "H3", new Coordinates(98, 68, 99)));
			weatherTower.register(new Baloon(6, "B3", new Coordinates(102, 22, 34)));
			weatherTower.register(new JetPlane(7, "J2", new Coordinates(11, 99, 768)));
			weatherTower.register(new Helicopter(8, "H4", new Coordinates(223, 23, 54)));
			redirectOut.resetStdOutToConsole();

			assertTrue(
				simulationController.simulationTime == 25
				&& simulationController.weatherTower.equals(weatherTower)
			);
		}
		catch ( Exception e ) {
			redirectOut.resetStdOutToConsole();
			fail("Failed creation Simulation Controller");
		}
	}

	@ParameterizedTest(
		name = "Error in simulation Line with file {1}"
	)
	@ValueSource(strings = {
		"./src/test/java/com/avajlauncher/controller/configFilesTest",
		"./src/test/java/com/avajlauncher/controller/configFilesTest/FileThatDoNotExists.txt",
		"./src/test/java/com/avajlauncher/controller/configFilesTest/emptyFile.txt",
	})
	void testNewSimulationControllerErrorOnInputFile(String arg1) {
		RedirectStdOut redirectOut = new RedirectStdOut();
		redirectOut.redirectOutToFile("nul");
		try {
			SimulationControllerFactory scFactory = new SimulationControllerFactory(arg1);
			SimulationController simulationController = scFactory.newSimulationController();
			redirectOut.resetStdOutToConsole();
			fail("Error: An exception should be thrown.");
		}
		catch ( Exception e ) {
			redirectOut.resetStdOutToConsole();
			assertTrue(true);
		}
	}

	@ParameterizedTest(
		name = "Error in simulation Line with file {1}"
	)
	@ValueSource(strings = {
		"./src/test/java/com/avajlauncher/controller/configFilesTest/errorConfigOnSimulationTime0.txt",
		"./src/test/java/com/avajlauncher/controller/configFilesTest/errorConfigOnSimulationTime1.txt",
		"./src/test/java/com/avajlauncher/controller/configFilesTest/errorConfigOnSimulationTime2.txt",
		"./src/test/java/com/avajlauncher/controller/configFilesTest/errorConfigOnSimulationTime3.txt",
		"./src/test/java/com/avajlauncher/controller/configFilesTest/notArealConfigFile.cpp"
	})
	void testNewSimulationControllerErrorSimulationLine(String arg1) {
		RedirectStdOut redirectOut = new RedirectStdOut();
		redirectOut.redirectOutToFile("nul");
		try {
			SimulationControllerFactory scFactory = new SimulationControllerFactory(arg1);
			SimulationController simulationController = scFactory.newSimulationController();
			redirectOut.resetStdOutToConsole();
			fail("Error: An exception should be thrown.");
		}
		catch ( Exception e ) {
			redirectOut.resetStdOutToConsole();
			assertTrue(true);
		}
	}

	@ParameterizedTest(
		name = "Error in simulation Line with file {1}"
	)
	@ValueSource(strings = {
		"./src/test/java/com/avajlauncher/controller/configFilesTest/errorConfigOnAircraftLine0.txt",
		"./src/test/java/com/avajlauncher/controller/configFilesTest/errorConfigOnAircraftLine1.txt",
		"./src/test/java/com/avajlauncher/controller/configFilesTest/errorConfigOnAircraftLine2.txt",
		"./src/test/java/com/avajlauncher/controller/configFilesTest/errorConfigOnAircraftLine3.txt",
		"./src/test/java/com/avajlauncher/controller/configFilesTest/errorConfigOnAircraftLine4.txt",
		"./src/test/java/com/avajlauncher/controller/configFilesTest/errorConfigOnAircraftLine5.txt",
	})
	void testNewSimulationControllerErrorAircraftLine(String arg1) {
		RedirectStdOut redirectOut = new RedirectStdOut();
		redirectOut.redirectOutToFile("nul");
		try {
			SimulationControllerFactory scFactory = new SimulationControllerFactory(arg1);
			SimulationController simulationController = scFactory.newSimulationController();
			redirectOut.resetStdOutToConsole();
			fail("Error: An exception should be thrown.");
		}
		catch ( Exception e ) {
			redirectOut.resetStdOutToConsole();
			assertTrue(true);
		}
	}
}

