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
		System.out.println(
			"Tower says: "
			+ ((Aircraft)p_flyable).getIndentifier()
			+ " unregistered from weather tower."
		);
	}

	public void conditionChanged() {
		for (int i = 0; i < observers.size(); i++) {
			this.observers.get(i).updateConditions();
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
}