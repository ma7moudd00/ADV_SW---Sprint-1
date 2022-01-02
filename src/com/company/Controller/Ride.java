package com.company.Controller;

import com.company.Views.ProgramInterface;

import java.io.IOException;
import java.util.ArrayList;

public class Ride {
    private Double price = 0.00;
    private Double discountedPrice = 0.00;
    private int rideNumber = 1;
    private int numberOfUser = 0;
    private String source, destination = "";
    private ArrayList<User> users = new ArrayList();
    private Driver driver = new Driver();
    private ArrayList<Discounts> discounts = new ArrayList<>();
    private Discounts discounts1;
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
        drivers.add(driver);
    }

    public void removeObservingDriver (Driver driver) {
        drivers.remove(driver);
    }

    public boolean hasDiscounts() {
        return discounts.size() != 0;
    }

    public ArrayList<Discounts> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(ArrayList<Discounts> discounts) {
        this.discounts = discounts;
    }

    public int getNumberOfUser() {
        return numberOfUser;
    }

    public void setNumberOfUser(int numberOfUser) {
        this.numberOfUser = numberOfUser;
    }

    public ArrayList<User> getUser() {
        return users;
    }

    public void addUser (User u) {
        users.add(u);
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Double getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(Double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public void checkFavouritePlaces() throws IOException {
        for(int i=0;i<drivers.size();i++) {
            if (drivers.get(i).getFavouritePlaces().contains(this.source)) {
                notifyAllDrivers(this);
            }
            else {
                System.out.println("Sorry! There are no drivers nearby your location! Please check again later!");
            }
        }

    }
    public void notifyAllDrivers(Ride r) throws IOException {
        for (Driver driver : drivers) {
            if (driver.getAvailable()) {
                driver.update(r);

            }
        }
    }

    public boolean checkForDiscount(ArrayList <String> arrayList) {
        boolean flag = false;
        if (arrayList.contains(this.getDestination())) {
            discounts1 = new PlacesDiscounts();
            discounts1.reAdjustPrice(this);
            this.getDriver().addToBalance(this.getPrice());
            this.getUser().get(this.getNumberOfUser()).deduceFromBalance(this.getDiscountedPrice());
            flag = true;
        }
        if (this.getUser().get(numberOfUser).getNumberOfRides() == 1) {
            discounts1 = new firstRideDiscount();
            discounts.add(discounts1);
            discounts1.reAdjustPrice(this);
            this.getDriver().addToBalance(this.getPrice());
            this.getUser().get(this.getNumberOfUser()).deduceFromBalance(this.getDiscountedPrice());
            flag = true;
        }

        if (this.getNumberOfUser() >= 2) {
            discounts1 = new TwoPassengersDiscount();
            discounts.add(discounts1);
            discounts1.reAdjustPrice(this);
            this.getDriver().addToBalance(this.getPrice());
            this.getUser().get(this.getNumberOfUser()).deduceFromBalance(this.getDiscountedPrice());
            flag = true;
        }

        if (Integer.parseInt(this.getUser().get(this.getNumberOfUser()).getBirthday()) == java.time.LocalDate.now().getDayOfMonth() && Integer.parseInt(this.getUser().get(this.getNumberOfUser()).getBirthMonth()) == java.time.LocalDate.now().getMonthValue()) {
            discounts1 = new birthdayDiscount();
            discounts.add(discounts1);
            discounts1.reAdjustPrice(this);
            this.getDriver().addToBalance(this.getPrice());
            this.getUser().get(this.getNumberOfUser()).deduceFromBalance(this.getDiscountedPrice());
            flag = true;
        }
        if (ProgramInterface.checkForHolidays()) {
            discounts1 = new publicHolidayDiscount();
            discounts.add(discounts1);
            discounts1.reAdjustPrice(this);
            this.getDriver().addToBalance(this.getPrice());
            this.getUser().get(this.getNumberOfUser()).deduceFromBalance(this.getDiscountedPrice());
            flag = true;
        }

        return flag;
    }


    @Override
    public String toString() {
        return "Ride: " +rideNumber + " From:" + getSource()+ " To: "+ getDestination();
    }
}

