package com.avajlauncher;

import com.avajlauncher.controller.SimulationController;
import com.avajlauncher.controller.SimulationControllerFactory;

public final class App {
	private App() {
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Invalid Number of arguments");
			System.exit(1);
		}
		try {
			SimulationControllerFactory controllerFactory =
				new SimulationControllerFactory(args[0]);
			SimulationController controller =
				controllerFactory.newSimulationController();
			controller.runSimulation();
		}
		catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
