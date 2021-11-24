package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Driver extends People {

    private String Driving_license,NationalID="";
    private float averageRating;
    private float sumOfRatings = 0;
    private Ride currentRide = new Ride();
    private ArrayList<Integer> ratings = new ArrayList<>();
    private ArrayList<String> favouritePlaces = new ArrayList<>();
    private ArrayList <Ride> ridesWithinFavouritePlaces = new ArrayList<>();


    public Driver() {
        super("","","","","");

    };

    public Driver(String username, String password, String name, String mobileNumber, String email,String NationalID,String Driving_license) {
        super(username, password, name, mobileNumber, email);
        this.NationalID=NationalID;
        this.Driving_license=Driving_license;

    }

    public String getDriving_license() {
        return Driving_license;
    }

    public void setDriving_license(String driving_license) {
        Driving_license = driving_license;
    }

    public String getNationalID() {
        return NationalID;
    }

    public void setNationalID(String nationalID) {
        NationalID = nationalID;
    }
    public ArrayList<String> getFavouritePlaces() {
        return favouritePlaces;
    }

    public Ride getCurrentRide() {
        return currentRide;
    }


    public float getAverageRating() {
        for (int i =0; i<ratings.size(); i++) {
            sumOfRatings += ratings.get(i);
        }
        averageRating= (sumOfRatings)/ (float) (ratings.size());
        return averageRating;
    }


    public void addRating (int rating) {
        ratings.add(rating);
    }

    public void listAllRatings() {
        for (int i =0;i<ratings.size();i++) {
            System.out.println(ratings.get(i));
        }
    }


    public void addToFavouritePlaces(String favouriteSource) {
        favouritePlaces.add(favouriteSource);
    }

    public void addToRidesWithinFavouritePlaces (Ride r) {
        if (favouritePlaces.contains(r.getSource())) {
            ridesWithinFavouritePlaces.add(r);
        }
    }

    public void listRides() {
        for (Ride r: ridesWithinFavouritePlaces) {
            System.out.println(r);
        }
    }

    public void offerPrice (Ride r) {
        System.out.println("((Driver Side Message)) Please enter a price for this ride: ");
        Scanner scanner = new Scanner(System.in);
        double price = scanner.nextDouble();
        scanner.nextLine();
        r.setPrice(price);
    }

    public void update(Ride r) {
        offerPrice(r);
    }

}
