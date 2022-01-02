package com.company.Controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class CaptainArrivedEvent extends Event {
    private String captainName;
    private String username;

    public CaptainArrivedEvent(LocalDateTime eventDate, String eventName, String captainName, String username) {
        super(eventDate, eventName);
        this.captainName = captainName;
        this.username = username;
    }

    @Override
    public void writeToEventFile(){
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("log.txt", true)));
            out.println(getEventName() + " " + getEventDate() + " " + captainName + " " + username + " " + "\n");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
