package com.avajlauncher.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.avajlauncher.exceptions.IncorrectAircraftType;
import com.avajlauncher.model.aircraft.Aircraft;
import com.avajlauncher.model.aircraft.AircraftFactory;
import com.avajlauncher.model.aircraft.Flyable;
import com.avajlauncher.model.configFileParser.ConfigError;
import com.avajlauncher.model.configFileParser.ConfigParser;
import com.avajlauncher.model.tower.WeatherTower;

public class SimulationControllerFactory {
	private String				configFile;
	private ConfigParser		configParser;
	private ConfigError			configError;

	public SimulationControllerFactory(String p_configFile) {
		this.configFile = p_configFile;
		this.configParser = new ConfigParser();
		this.configError = new ConfigError();
	}

	public SimulationController
	newSimulationController()
	throws Exception
	{
		try {
			FileReader fileReader = this.openFile();
			BufferedReader reader = new BufferedReader(fileReader);
			WeatherTower weatherTower = new WeatherTower();
			SimulationController simulationController;
	
			simulationController =
				new SimulationController(this.getSimulationTime(reader), weatherTower);
			this.registerAllAircraftInTower(reader, weatherTower);
			this.closeReader(reader);
			return (simulationController);
		}
		catch (Exception e) {
			throw e;
		}
 	}

	private FileReader openFile() throws FileNotFoundException {
		try {
			FileReader fileReader = new FileReader(this.configFile);
			return (fileReader);
		}
		catch (FileNotFoundException e) {
			throw e;
		}
	}

	private int
	getSimulationTime(final BufferedReader p_reader)
	throws Exception
	{
		try {
			String line = p_reader.readLine();
			
			this.configError.setLine(line);
			if (line == null || !this.configError.isSimulationTimeLineCorrect()) {
				throw new Exception("Error: Incorrect simulation time line.");
				// personal exception
			}
			this.configParser.setLine(line);
			return (this.configParser.getSimulationTime());
		}
		catch (IOException e) {
			this.closeReader(p_reader);
			throw e;
		}
	}

	private void
	registerAllAircraftInTower(final BufferedReader p_reader, WeatherTower p_weatherTower)
	throws Exception
	{
		try {
			String line = p_reader.readLine();
			
			if (line == null) {
				// personal exception
				throw new Exception("Error: No aircraft Provided.");
			}
			while (line != null) {
				this.configError.setLine(line);
				this.configError.tokenizeAirCraftLine();
				if (!this.configError.isAircraftLineCorrect()) {
					// personal exception
					throw new Exception("Error: Incorrect Aircraft line.");
				}
				this.configParser.setLine(line);
				this.configParser.tokenizeAirCraftLine();
				this.registerAircraftInTower(p_weatherTower);
				line = p_reader.readLine();
			}
		}
		catch (Exception e) {
			this.closeReader(p_reader);
			throw e;
		} 
	}

	private void
	registerAircraftInTower(WeatherTower p_weatherTower)
	throws IncorrectAircraftType
	{
		try {
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
		catch (IncorrectAircraftType e) {
			throw e;
		}
	}

	private void closeReader(final BufferedReader reader) {
		try {
			reader.close();
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}
}
