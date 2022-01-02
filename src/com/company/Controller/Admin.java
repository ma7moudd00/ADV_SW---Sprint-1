package com.company.Controller;


import com.company.Model.DriversDatabase;
import com.company.Model.UserDatabase;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Admin {

    public void Verify (People p) {
        if (p instanceof User) {
            for (int i = 0; i< UserDatabase.getRegisteredUsers().size(); i++) {
                if (UserDatabase.getRegisteredUsers().get(i).getUsername() == p.getUsername()) {
                    UserDatabase.getRegisteredUsers().get(i).setVerified(true);
                }
            }
        }
        else if (p instanceof  Driver) {
            for (int i = 0; i< DriversDatabase.getRegisteredDrivers().size(); i++) {
                if (DriversDatabase.getRegisteredDrivers().get(i).getUsername() == p.getUsername()) {
                    DriversDatabase.getRegisteredDrivers().get(i).setVerified(true);
                }
            }
        }
    }

    public void Suspend (People p) {
        if (p instanceof  User) {
            for (int i =0; i<UserDatabase.getRegisteredUsers().size();i++) {
                if (UserDatabase.getRegisteredUsers().get(i).getUsername() == p.getUsername()) {
                    UserDatabase.getRegisteredUsers().get(i).setSuspended(true);
                }
            }
        }
        else if (p instanceof  Driver) {
            for (int i =0; i<DriversDatabase.getRegisteredDrivers().size();i++) {
                if (DriversDatabase.getRegisteredDrivers().get(i).getUsername() == p.getUsername()) {
                    DriversDatabase.getRegisteredDrivers().get(i).setSuspended(true);
                }
            }
        }
    }

    public void addAnArea (String area, ArrayList<String> arrayList) {
        arrayList.add(area);
    }

    public String accessEvents(Path p) throws IOException {
        String content = Files.readString(p);
        return content;
    }

}
