package com.company.Controller;

import java.time.LocalDateTime;

public abstract class Event {

   private LocalDateTime eventDate;
   private String eventName;

   public LocalDateTime getEventDate() {
      return eventDate;
   }

   public void setEventDate(LocalDateTime eventDate) {
      this.eventDate = eventDate;
   }

   public String getEventName() {
      return eventName;
   }

   public void setEventName(String eventName) {
      this.eventName = eventName;
   }

   public Event(LocalDateTime eventDate, String eventName) {
      this.eventDate = eventDate;
      this.eventName = eventName;
   }

   public abstract void  writeToEventFile();
}
