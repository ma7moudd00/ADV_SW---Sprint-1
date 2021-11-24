package com.company;
public class User extends People {
    private Ride requestedRide = new Ride();

    public User() {
        super("","","","","");
    }

    public User(String username, String password, String name, String mobileNumber, String email)
    {
        super(username, password, name, mobileNumber, email);

    }


    public void requestARide(Ride ride) {
        ride.checkFavouritePlaces();
    }

    public boolean acceptARide(double price, Ride r) {
        if (price >= r.getPrice()) {
            requestedRide = r;
            System.out.println("Ride Accepted!");
            System.out.println("A driver will be nearby your requested location soon!");
            return true;
        }
        System.out.println("Ride Rejected!");

        return false;
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
