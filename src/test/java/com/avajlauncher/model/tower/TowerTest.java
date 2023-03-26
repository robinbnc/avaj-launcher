package com.avajlauncher.model.tower;

import com.avajlauncher.model.aircraft.*;
import com.avajlauncher.model.coordinates.Coordinates;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.fail;

class TowerTest {

	@Test
	void testRegister() {
		Tower towerTest = new Tower();
		Flyable [] arrayTestFlyable = {
			new Baloon(0, "baloonTest", new Coordinates(1, 1, 1)),
			new JetPlane(1, "jetplaneTest", new Coordinates(1, 1, 1)),
			new Helicopter(2, "helicopterTest", new Coordinates(1, 1, 1))
		};

		for (Flyable flyable: arrayTestFlyable) {
			towerTest.register(flyable);
		}
		for (int i = 0; i < arrayTestFlyable.length; i++) {
			if (!towerTest.observers.contains(arrayTestFlyable[i])) {
				fail("A Flyable is misssing");
			}
		}
	}
	
	@Test
	void testUnregister() {
		Tower towerTest = new Tower();
		Flyable [] arrayTestFlyable = {
			new Baloon(0, "baloonTest", new Coordinates(1, 1, 1)),
			new JetPlane(1, "jetplaneTest", new Coordinates(1, 1, 1)),
			new Helicopter(2, "helicopterTest", new Coordinates(1, 1, 1))
		};

		for (Flyable flyable: arrayTestFlyable) {
			towerTest.register(flyable);
		}
		towerTest.unregister(arrayTestFlyable[1]);
		if (towerTest.observers.contains(arrayTestFlyable[1])) {
			fail("A Flyable is misssing");
		}
	}
}