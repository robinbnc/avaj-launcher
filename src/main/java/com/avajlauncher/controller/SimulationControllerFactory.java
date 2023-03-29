package com.avajlauncher.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.avajlauncher.exceptions.ConfigExceptions;
import com.avajlauncher.exceptions.IncorrectAircraftType;
import com.avajlauncher.model.aircraft.Aircraft;
import com.avajlauncher.model.aircraft.AircraftFactory;
import com.avajlauncher.model.aircraft.Flyable;
import com.avajlauncher.model.configFileParser.ConfigError;
import com.avajlauncher.model.configFileParser.ConfigParser;
import com.avajlauncher.model.tower.WeatherTower;

public class SimulationControllerFactory {
	private final String		configFile;
	private final ConfigParser	configParser;
	private final ConfigError	configError;

	public SimulationControllerFactory(String p_configFile) {
		this.configFile = p_configFile;
		this.configParser = new ConfigParser();
		this.configError = new ConfigError();
	}

	public SimulationController
	newSimulationController()
	throws Exception
	{
		BufferedReader reader = new BufferedReader(this.openFile());
		try {
			WeatherTower weatherTower = new WeatherTower();
			SimulationController simulationController =
				new SimulationController(this.getSimulationTime(reader), weatherTower);

			this.registerAllAircraftInTower(reader, weatherTower);
			this.closeReader(reader);
			return (simulationController);
		} catch (Exception e) {
			throw e;
		} finally {
			this.closeReader(reader);
		}
 	}

	private FileReader
	openFile()
	throws FileNotFoundException
	{ return (new FileReader(this.configFile)); }

	private int
	getSimulationTime(final BufferedReader p_reader)
	throws Exception
	{
		String line = p_reader.readLine();
		
		this.configError.setLine(line);
		if (line == null || !this.configError.isSimulationTimeLineCorrect()) {
			throw new ConfigExceptions("Error: Incorrect simulation time line.");
		}
		this.configParser.setLine(line);
		return (this.configParser.getSimulationTime());
	}

	private void
	registerAllAircraftInTower(final BufferedReader p_reader, WeatherTower p_weatherTower)
	throws Exception
	{
		String line = p_reader.readLine();
		
		if (line == null) {
			throw new ConfigExceptions("Error: No aircraft Provided.");
		}
		while (line != null) {
			this.configError.setLine(line);
			this.configError.tokenizeAirCraftLine();
			if (!this.configError.isAircraftLineCorrect()) {
				throw new ConfigExceptions("Error: Incorrect Aircraft line.");
			}
			this.configParser.setLine(line);
			this.configParser.tokenizeAirCraftLine();
			this.registerAircraftInTower(p_weatherTower);
			line = p_reader.readLine();
		}
	}

	private void
	registerAircraftInTower(WeatherTower p_weatherTower)
	throws IncorrectAircraftType
	{
		Flyable newAircraft =
			AircraftFactory.getInstance()
			.newAircraft(
				this.configParser.getAircraftType(),
				this.configParser.getAircraftName(),
				this.configParser.getAircraftCoordinates()
		);
		p_weatherTower.register(newAircraft);
		System.out.println("Tower says: "
			+ ((Aircraft)newAircraft).getIndentifier()
			+ " registered to weather tower."
		);
		newAircraft.registerTower(p_weatherTower);
	}

	private void closeReader(final BufferedReader reader) {
		try {
			reader.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}
}
