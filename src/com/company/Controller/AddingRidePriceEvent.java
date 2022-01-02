package com.company.Controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class AddingRidePriceEvent extends Event {
    private double Price;
    private String driverName;
    public AddingRidePriceEvent(LocalDateTime eventDate, String eventName,double RidePrice,String CaptainName) {
        super(eventDate,eventName);
        this.Price = RidePrice;
        this.driverName = CaptainName;
    }

    @Override
    public void writeToEventFile() {
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("log.txt", true)));
            out.println(getEventName() + " " + getEventDate() + " " + driverName + " " + Price + "\n");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
