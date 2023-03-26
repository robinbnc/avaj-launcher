package com.avajlauncher.model.configFileParser;

import java.util.HashMap;
import java.util.Map;

public class Config {
	protected String	line;
	protected Map<String, String>	tokenizedLine;

	public void setLine(final String p_line) {
		this.line = p_line;
	}

	public void tokenizeAirCraftLine() {
		this.tokenizedLine = new HashMap<String, String>();
		String[] splittedLine = this.line.split("\\s+");
		String[] keys = {"type", "name", "longitude", "latitude", "height"};

		for (int i = 0; i < splittedLine.length && i < keys.length; i++) {
			this.tokenizedLine.put(keys[i], splittedLine[i]);
		}
	}
}
