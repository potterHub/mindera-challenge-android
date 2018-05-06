package com.example.potter_desktop.meetmindera.data;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

public class EventOrganised implements Serializable{
    private String event_name;
    private ArrayList<EventDay> event_day_list;


    public EventOrganised(String event_name) {
        this.setEventName(event_name);
        this.resetEventDayList();
    }

    public void addEventDay(EventDay eventDay) {
        this.event_day_list.add(eventDay);
    }

    public String getEventName() {
        return event_name;
    }

    public void setEventName(String event_name) {
        this.event_name = event_name;
    }

    public ArrayList<EventDay> getEventDayList() {
        return event_day_list;
    }

    public void resetEventDayList() {
        this.event_day_list = new ArrayList<>();
    }

    public void generateDummyDays(boolean withDays) {
        Random rand = new Random();
        for (int j = 1; j <= 11; j++) {
            EventDay day = new EventDay("");
            day.generateDayActivities( 15 + rand.nextInt(5));
            if (withDays)
                day.setDay("Day " + (j >= 10 ? "" : "0" ) + j );
            event_day_list.add(day);
        }
    }
}
