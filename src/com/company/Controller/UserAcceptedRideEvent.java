package com.company.Controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class UserAcceptedRideEvent extends Event {
    String Username;

    public UserAcceptedRideEvent(LocalDateTime eventDate, String eventName,String username) {
        super(eventDate,eventName);
        Username = username;
    }

    @Override
    public void writeToEventFile() {
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("log.txt", true)));
            out.println(getEventName() + " " + getEventDate() + " " + Username + "\n");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
