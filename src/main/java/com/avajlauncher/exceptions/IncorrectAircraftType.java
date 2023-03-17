package com.avajlauncher;

public class IncorrectAircraftType extends Exception { 
    public IncorrectAircraftType(String errorMessage) {
        super(errorMessage);
    }
}
