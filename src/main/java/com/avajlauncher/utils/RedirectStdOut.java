package com.avajlauncher.utils;

import java.io.PrintStream;
import java.io.File;
import java.io.FileOutputStream;

public class RedirectStdOut {
    PrintStream console = System.out;

    public void redirectOutToFile(String fileName) {
        try {
            File file = new File(fileName);
            FileOutputStream fos = new FileOutputStream(file);
            PrintStream ps = new PrintStream(fos);
            System.setOut(ps);
        }
        catch (Exception e) {
            e.printStackTrace(System.out);
        }
	}

    public void resetStdOutToConsole() {
		System.setOut(this.console);
	}
}
