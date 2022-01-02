package com.company.Controller;

import com.company.Model.DriversDatabase;

import java.io.IOException;
import java.time.LocalDateTime;

public class User extends People {

    private int numberOfRides = 0;
    private boolean willingToShareRide = false;
    private String birthMonth;
    private String  birthday;


    public User() {
        super("","","","","");
    }

    public User(String username, String password, String name, String mobileNumber, String email) {
        super(username, password, name, mobileNumber, email);

    }

    public String getBirthMonth() {
        return birthMonth;
    }

    public void setBirthMonth(String birthMonth) {
        this.birthMonth = birthMonth;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public boolean isWillingToShareRide() {
        return willingToShareRide;
    }

    public void setWillingToShareRide(boolean willingToShareRide) {
        this.willingToShareRide = willingToShareRide;
    }

    public void requestARide(Ride ride) throws IOException {
        ride.checkFavouritePlaces();
    }

    public boolean acceptARide(double price, Ride r) {
        if (price >= r.getPrice()) {
            r.addUser(this);
            r.setPrice(price);
            Event event = new UserAcceptedRideEvent(LocalDateTime.now(),"User Accepted a ride",
                    this.getUsername());
            event.writeToEventFile();
            numberOfRides++;
            r.setNumberOfUser(r.getNumberOfUser()+1);
            return true;
        }
        System.out.println("Ride Rejected!");

        return false;
    }

    public int getNumberOfRides() {
        return numberOfRides;
    }

    public float checkDriverAverageRating(Driver d) {
        return d.getAverageRating();
    }

    public void rateADriver (Driver driver, int rating) {
        if (rating > 5 || rating < 1) {
            System.out.println("Invalid rating!");
            return;
        }
        for (Driver driver1: DriversDatabase.getRegisteredDrivers()) {
            if (driver.getUsername() == driver1.getUsername()) {
                driver.addRating(rating);
            }
        }
    }
}
