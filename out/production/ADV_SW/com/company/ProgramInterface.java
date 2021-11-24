package com.company;

import java.util.Scanner;

public class ProgramInterface {
    public ProgramInterface() {
        DriversDatabase driversDatabase = DriversDatabase.getInstance();
        UserDatabase userDatabase = UserDatabase.getInstance();
        Ride ride = new Ride();
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
                    User user = new User();
                    System.out.println("1- Register" + "\n" + "2- Log in");
                    input = scanner.nextInt();
                    scanner.nextLine();
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
                        user = new User(username, password, name, mobileNumber, Email);
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
                            System.out.println("Sorry! wrong username or password!");
                        }
                    }
                    boolean flag = true;
                    while (flag) {
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
                        } else if (input == 2) {
                            System.out.println("((User Side Message)) Enter a suitable price for this ride");
                            double price = scanner.nextDouble();
                            scanner.nextLine();
                            user.acceptARide(price,ride);
                        }
                        else if (input == 3) {
                            Driver driver = new Driver();
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
                } else if (input == 2) {
                    Driver driver = new Driver();
                    System.out.println("1- Register" + "\n" + "2- Log in");
                    scanner = new Scanner(System.in);
                    input = scanner.nextInt();
                    scanner.nextLine();
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
                        }
                    }
                    boolean driverMenuFlag = true;
                    while (driverMenuFlag) {
                        System.out.println("1- Add a favourite area" + "\n" + "2- List rides in your favourite areas" + "\n" + "3- See your rating" + "\n" + "4- Back to previous Menu");
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
                        System.out.println("1- Verify a user" + "\n" + "2- Suspend a user" + "\n" + "3- Verify a driver" + "\n" + "4- Suspend a driver" + "\n" + "5- Back to previous Menu");
                        input = scanner.nextInt();
                        scanner.nextLine();
                        switch (input) {
                            case 1:
                                System.out.println("Enter the username of the user you want to verify");
                                String notVerifiedUser = scanner.next();
                                admin.verifyUser(notVerifiedUser);
                                break;
                            case 2:
                                System.out.println("Enter the username of the user you want to suspend");
                                String suspendedUser = scanner.next();
                                admin.suspendUser(suspendedUser);
                                break;
                            case 3:
                                System.out.println("Enter the username of the driver you want to verify");
                                String notVerifiedDriver = scanner.next();
                                admin.verifyDriver(notVerifiedDriver);
                                break;
                            case 4:
                                System.out.println("Enter the username of the driver you want to suspend");
                                String suspendedDriver = scanner.next();
                                admin.suspendDriver(suspendedDriver);
                                break;
                            case 5:
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
}
