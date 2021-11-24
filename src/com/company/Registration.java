package com.company;

public class Registration {
    public void registerUser(User user) {
        for (int i =0; i<UserDatabase.getRegisteredUsers().size();i++) {
            if (user.getUsername() == UserDatabase.getRegisteredUsers().get(i).getUsername()) {
                System.out.println("Duplicate username!");
            }
            else {
                UserDatabase.getRegisteredUsers().add(user);
                System.out.println("User Registered Successfully!");
            }
        }
    }

    public void registerDriver (Driver driver) {
            for (int i =0; i<UserDatabase.getRegisteredUsers().size();i++) {
                if (driver.getUsername() == UserDatabase.getRegisteredUsers().get(i).getUsername()) {
                    System.out.println("Duplicate username!");
                }
                else {
                    DriversDatabase.getRegisteredDrivers().add(driver);
                    System.out.println("Driver Registered Successfully!");
                }
            }
    }
}
