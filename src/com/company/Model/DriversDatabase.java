package com.company.Model;

import com.company.Controller.Driver;

import java.util.ArrayList;

public class DriversDatabase implements Database {
    static DriversDatabase instance;
    private static ArrayList<Driver> registeredDrivers = new ArrayList<>();

    private DriversDatabase() {}

    public static DriversDatabase getInstance() {
        if (instance == null) {
            instance = new DriversDatabase();
        }
        return instance;
    }

    public static ArrayList<Driver> getRegisteredDrivers() {
        return registeredDrivers;
    }

    public boolean checkIfDriverIsSuspended(Driver driver) {
        return driver.isSuspended();
    }

    public boolean checkIfDriverExists (String username) {
        for (int i = 0; i <DriversDatabase.getRegisteredDrivers().size(); i++) {
            if (username.equals(DriversDatabase.getRegisteredDrivers().get(i).getUsername())) {
                return true;
            }
        }
        return false;
    }

    public Driver searchForADriver(String username) {
        Driver d = new Driver();
        for (int i = 0; i < DriversDatabase.getRegisteredDrivers().size(); i++) {
            if (username.equals(DriversDatabase.getRegisteredDrivers().get(i).getUsername())) {
                d = DriversDatabase.getRegisteredDrivers().get(i);
            }
        }
        return d;
    }
}
