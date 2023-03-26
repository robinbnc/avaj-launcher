package com.avajlauncher.model.tower;

import java.util.List;
import java.util.ArrayList;

import com.avajlauncher.model.aircraft.Aircraft;
import com.avajlauncher.model.aircraft.Flyable;

public class Tower {
	protected List<Flyable> observers = new ArrayList<Flyable>();	;	

	public void register(Flyable p_flyable) {
		this.observers.add(p_flyable);
	}

	public void unregister(Flyable p_flyable) {
		this.observers.remove(p_flyable);
	}

	public void conditionChanged() {
		for (Flyable flyable: observers) {
			flyable.updateConditions();
		}
	}

	public boolean equals(final Tower p_tower) {
		if (this.observers.size() != p_tower.observers.size()) {
			return (false);
		}
		for (int i = 0; i < this.observers.size(); i++) {
			if (this.observers.get(i) instanceof Aircraft
				&& p_tower.observers.get(i) instanceof Aircraft) {
				Aircraft aircraftInObject = (Aircraft) this.observers.get(i);
				Aircraft aircraftToCompare = (Aircraft) p_tower.observers.get(i);
				if (!aircraftInObject.equals(aircraftToCompare)) {
					return (false);
				}
			}
		}
		return (true);
	}

	public void printAicrafts() {
		for (Flyable flyable: observers) {
			if (flyable instanceof Aircraft) {
				Aircraft test = (Aircraft) flyable;
				test.printData();
			}
		}
	}
}