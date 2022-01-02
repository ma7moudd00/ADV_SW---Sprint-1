package com.company.Views;

import com.company.Controller.*;
import com.company.Model.DriversDatabase;
import com.company.Model.UserDatabase;

import java.io.IOException;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class ProgramInterface {

    public ProgramInterface() throws IOException {
        DriversDatabase driversDatabase = DriversDatabase.getInstance();
        UserDatabase userDatabase = UserDatabase.getInstance();
        Ride ride = new Ride();
        User user = new User();
        Driver driver = new Driver();
        ArrayList<String> discountedAreas = new ArrayList<>();

        Registration registration = new Registration();
        boolean menuFlag = true;
        while (menuFlag) {
            System.out.println("Welcome to our program!");
            System.out.println("1- User Menu " + "\n" + "2- Driver Menu" +"\n" + "3- Admin Menu " +"\n" + "4- Exit");
            Scanner scanner = new Scanner(System.in);
            int input = scanner.nextInt();
            scanner.nextLine();
            while (true) {
                if (input == 1) {
                    System.out.println("1- Register" + "\n" + "2- Log in");
                    input = scanner.nextInt();
                    scanner.nextLine();
                    boolean userFlag = true;
                    if (input == 1) {
                        System.out.println("Please enter your Account information");
                        System.out.println("Name:");
                        String name = scanner.nextLine();
                        System.out.println("Mobile number:");
                        String mobileNumber = scanner.next();
                        System.out.println("Email:");
                        String Email = scanner.next();
                        System.out.println("Username:");
                        String username = scanner.next();
                        System.out.println("Password:");
                        String password = scanner.next();
                        System.out.println("Birthdate: (dd/MM) ");
                        String birthdate = scanner.next();
                        String [] BirthArray =  birthdate.split("/");
                        user = new User(username, password, name, mobileNumber, Email);
                        user.setBirthday(BirthArray[0]);
                        user.setBirthMonth(BirthArray[1]);
                        registration.registerUser(user);
                    } else if (input == 2) {
                        System.out.println("Username:");
                        String username = scanner.next();
                        System.out.println("Password:");
                        String password = scanner.next();
                        if (userDatabase.checkIfUserExists(username)) {
                            user = userDatabase.searchForAUser(username);
                            if (user.isSuspended()) {
                                System.out.println("Sorry! your account is suspended and you can't log in into our system, for more information, please contact an admin");
                                break;
                            }
                        } else {
                            userFlag = false;
                            System.out.println("Sorry! wrong username or password!");
                        }
                    }
                    boolean flag = true;
                    while (flag && userFlag) {
                        System.out.println("1- Request a ride" + "\n" + "2- Accept a ride" + "\n" +"3- Rate a driver" + "\n" + "4- Back to previous Menu");
                        input = scanner.nextInt();
                        scanner.nextLine();
                        if (input == 1) {
                            System.out.println("Source Location: ");
                            String source = scanner.nextLine();
                            System.out.println("Destination: ");
                            String destination = scanner.nextLine();
                            ride.setSource(source);
                            ride.setDestination(destination);
                            user.requestARide(ride);
                            System.out.println("Would you be willing to share the ride with someone else? Press 1 if you want, Press 2 if you dont");
                            int input2 = scanner.nextInt();
                            scanner.nextLine();
                            if (input2 == 1) {
                                user.setWillingToShareRide(true);
                            }
                            else {
                                user.setWillingToShareRide(false);
                            }
                        } else if (input == 2) {
                            System.out.println("((User Side Message)) Enter a suitable price for this ride");
                            double price = scanner.nextDouble();
                            scanner.nextLine();
                            if (user.acceptARide(price,ride)) {
                                ride.setDriver(driver);
                                ride.addUser(user);
                                if (ride.getUser().get(ride.getNumberOfUser()).isWillingToShareRide()) {
                                    ride.getDriver().setAvailable(true);
                                }
                                else {
                                    ride.getDriver().setAvailable(false);
                                }

                                if (!ride.hasDiscounts()) {
                                    System.out.println("Ride Accepted!");
                                    System.out.println("A driver will be nearby your requested location soon!");
                                    ride.getDriver().addToBalance(price);
                                    ride.getUser().get(ride.getNumberOfUser()).deduceFromBalance(price);
                                }
                                else {
                                    ride.checkForDiscount(discountedAreas);
                                }

                            }
                        }
                        else if (input == 3) {
                            Driver driver1 = new Driver();
                            System.out.println("Please enter the name of the driver you want to rate:");
                            String name = scanner.nextLine();
                            driver = driversDatabase.searchForADriver(name);
                            System.out.println("Enter your rating: ");
                            int rate = scanner.nextInt();
                            scanner.nextLine();
                            user.rateADriver(driver, rate);
                        }

                        else if (input == 4) {
                            flag = false;
                        } else {
                            System.out.println("Invalid input!");
                        }
                    }
                }
                else if (input == 2) {
                    System.out.println("1- Register" + "\n" + "2- Log in");
                    scanner = new Scanner(System.in);
                    input = scanner.nextInt();
                    scanner.nextLine();
                    boolean driverFlag = true;

                    if (input == 1) {
                        System.out.println("Please enter your Account information");
                        System.out.println("Name:");
                        String name = scanner.nextLine();
                        System.out.println("Mobile number:");
                        String mobileNumber = scanner.next();
                        System.out.println("Email:");
                        String Email = scanner.next();
                        System.out.println("Username:");
                        String username = scanner.next();
                        System.out.println("Password:");
                        String password = scanner.next();
                        System.out.println("National ID: ");
                        String ID = scanner.next();
                        System.out.println("Driving License: ");
                        String license = scanner.next();
                        driver = new Driver(username, password, name, mobileNumber, Email, ID, license);
                        registration.registerDriver(driver);
                    } else if (input == 2) {
                        System.out.println("Username:");
                        String username = scanner.next();
                        System.out.println("Password:");
                        String password = scanner.next();
                        if (driversDatabase.checkIfDriverExists(username)) {
                            driver = driversDatabase.searchForADriver(username);
                            if (driver.isSuspended()) {
                                System.out.println("Sorry! your account is suspended and you can't log in into our system, for more information, please contact an admin");
                                break;
                            }
                        } else {
                            System.out.println("Sorry! wrong username or password!");
                            driverFlag=false;
                        }
                    }
                    boolean driverMenuFlag = true;
                    while (driverMenuFlag && driverFlag) {
                        System.out.println("1- Add a favourite area" + "\n" + "2- List rides in your favourite areas" + "\n" + "3- See your rating" + "\n" + "4- Offer Price to ride" + " \n" + "5- Arrived to user's location" + "\n" + "6- Arrived to user's destination" + "\n" + "7- Back to previous Menu" );
                        input = scanner.nextInt();
                        scanner.nextLine();
                        switch (input) {
                            case 1:
                                System.out.println("Enter the location of the area you'd like to add: ");
                                String area = scanner.nextLine();
                                driver.addToFavouritePlaces(area);
                                Ride ride1 = new Ride();
                                ride1.setSource(area);
                                driver.addToRidesWithinFavouritePlaces(ride1);
                                ride1.addObservingDriver(driver);
                                break;
                            case 2:
                                driver.listRides();
                                break;
                            case 3:
                                driver.listAllRatings();
                                break;

                            case 4:
                                driver.offerPrice(ride);
                                break;
                            case 5:
                                driver.ArrivedtoUserLocation(ride);
                                break;

                            case 6:
                                driver.EndedRide(ride);
                            case 7:
                                driverMenuFlag = false;
                                break;
                            default:
                                System.out.println("Invalid input!");

                        }
                    }
                }
                else if (input == 3) {
                    Admin admin = new Admin();
                    boolean flag2 = true;
                    while (flag2) {
                        System.out.println("1- Verify a user" + "\n" + "2- Suspend a user" + "\n" + "3- Verify a driver" + "\n" + "4- Suspend a driver" + "\n" + "5- Add area to discounted areas " +
                                "\n" + "6- See the list of events" + "\n" + "7- Back to previous Menu");
                        input = scanner.nextInt();
                        scanner.nextLine();
                        switch (input) {
                            case 1:
                                System.out.println("Enter the username of the user you want to verify");
                                String notVerifiedUser = scanner.next();
                                user = userDatabase.searchForAUser(notVerifiedUser);
                                admin.Verify(user);
                                break;
                            case 2:
                                System.out.println("Enter the username of the user you want to suspend");
                                String suspendedUser = scanner.next();
                                user = userDatabase.searchForAUser(suspendedUser);
                                admin.Suspend(user);
                                break;
                            case 3:
                                System.out.println("Enter the username of the driver you want to verify");
                                String notVerifiedDriver = scanner.next();
                                driver = driversDatabase.searchForADriver(notVerifiedDriver);
                                admin.Verify(driver);
                                break;
                            case 4:
                                System.out.println("Enter the username of the driver you want to suspend");
                                String suspendedDriver = scanner.next();
                                driver = driversDatabase.searchForADriver(suspendedDriver);
                                admin.Verify(driver);
                                break;
                            case 5:
                                System.out.println("Enter the name of the area: ");
                                String discountedArea = scanner.nextLine();
                                admin.addAnArea(discountedArea,discountedAreas);
                                break;

                            case 6:
                                String events = admin.accessEvents(Path.of("log.txt"));
                                System.out.println(events);

                            case 7:
                                flag2 = false;
                                break;
                            default:
                                System.out.println("Wrong input!");
                                break;
                        }
                    }
                }
                else if (input == 4) {
                    menuFlag = false;
                } else
                    System.out.println("Invalid Number!");
                break;
            }
        }
    }

    public static boolean checkForHolidays() {
        ArrayList<LocalDate> holidays = new ArrayList<>();
        holidays.add(LocalDate.of(2022,1,7));
        holidays.add(LocalDate.of(2022,1,25));
        holidays.add(LocalDate.of(2022,4,24));
        holidays.add(LocalDate.of(2022,4,25));
        holidays.add(LocalDate.of(2022,5,1));
        holidays.add(LocalDate.of(2022,5,3));
        holidays.add(LocalDate.of(2022,7,10));
        holidays.add(LocalDate.of(2022,7,23));
        holidays.add(LocalDate.of(2022,7,31));
        holidays.add(LocalDate.of(2022,10,6));
        return holidays.contains(LocalDate.now());
    }
}
