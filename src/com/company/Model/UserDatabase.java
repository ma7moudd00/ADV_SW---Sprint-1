package com.company.Model;

import com.company.Controller.User;

import java.util.ArrayList;

public class UserDatabase implements Database {
    static UserDatabase instance;
    private static ArrayList<User> registeredUsers = new ArrayList<>();

    private UserDatabase() {}

    public static UserDatabase getInstance() {
        if (instance == null) {
            instance = new UserDatabase();
        }
        return instance;
    }


    public static ArrayList<User> getRegisteredUsers() {
        return registeredUsers;
    }

    public boolean checkIfUserIsSuspended(User user) {
        return user.isSuspended();
    }

    public boolean checkIfUserExists (String username) {
        for (int i = 0; i < UserDatabase.getRegisteredUsers().size(); i++) {
            if (username.equals(UserDatabase.getRegisteredUsers().get(i).getUsername())) {
                return true;
            }
        }
        return false;
    }

    public User searchForAUser(String username) {
        User u = new User();
        for (int i = 0; i < UserDatabase.getRegisteredUsers().size(); i++) {
            if (username.equals(UserDatabase.getRegisteredUsers().get(i).getUsername())) {
                u = UserDatabase.getRegisteredUsers().get(i);
            }
        }
        return u;
    }




}
