package com.company;

import java.util.ArrayList;

public class Ride {
    private Double price = 0.00;
    private int rideNumber = 1;
    private String source, destination = "";
    private static ArrayList <Driver> drivers = new ArrayList<>();

    public Ride() {
        rideNumber++;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void addObservingDriver (Driver driver) {
        this.drivers.add(driver);
    }

    public void removeObservingDriver (Driver driver) {
        this.drivers.remove(driver);
    }

    public void checkFavouritePlaces() {
        for(int i=0;i<drivers.size();i++) {
            if (drivers.get(i).getFavouritePlaces().contains(this.source)) {
                notifyAllDrivers(drivers.get(i).getCurrentRide());
            }
            else {
                System.out.println("Sorry! There are no drivers nearby your location! Please check again later!");
            }
        }

    }
    public void notifyAllDrivers(Ride r){
        for (Driver driver : drivers) {
            driver.update(r);
        }
    }


    @Override
    public String toString() {
        return "Ride: " +rideNumber + " From:" + getSource()+ " To: "+ getDestination();
    }
}

