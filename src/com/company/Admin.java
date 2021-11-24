package com.company;

public class Admin {

    public void verifyUser(String username) {
        for (int i =0; i<UserDatabase.getRegisteredUsers().size();i++) {
            if (UserDatabase.getRegisteredUsers().get(i).getUsername() == username) {
                UserDatabase.getRegisteredUsers().get(i).setVerified(true);
            }
        }
    }

    public void verifyDriver(String username) {
        for (int i =0; i<DriversDatabase.getRegisteredDrivers().size();i++) {
            if (DriversDatabase.getRegisteredDrivers().get(i).getUsername() == username) {
                DriversDatabase.getRegisteredDrivers().get(i).setVerified(true);
            }
        }
    }
    public void suspendUser (String username) {
        for (int i =0; i<UserDatabase.getRegisteredUsers().size();i++) {
            if (UserDatabase.getRegisteredUsers().get(i).getUsername() == username) {
                UserDatabase.getRegisteredUsers().get(i).setSuspended(true);
            }
        }
    }

    public void suspendDriver (String username) {
        for (int i =0; i<DriversDatabase.getRegisteredDrivers().size();i++) {
            if (DriversDatabase.getRegisteredDrivers().get(i).getUsername() == username) {
                DriversDatabase.getRegisteredDrivers().get(i).setSuspended(true);
            }
        }
    }
}
