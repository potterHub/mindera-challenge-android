package com.example.potter_desktop.meetmindera.data;

import com.example.potter_desktop.meetmindera.ui.activity.DayListActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class EventDay implements Serializable{
    private String day;
    private ArrayList<DayActivity> event_day_activity;

    public EventDay(String day) {
        this.event_day_activity = new ArrayList<>();
        this.day = day;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public ArrayList<DayActivity> getEventDayActivities() {
        return event_day_activity;
    }

    public void setEventDayActivities(ArrayList<DayActivity> event_day_activities) {
        this.event_day_activity = event_day_activities;
    }

    public void generateDayActivities(int size){
        Random rand = new Random();
        this.event_day_activity.clear();
        for (int j = 1; j <= size; j++) {
            String list = "List " + (j >= 10 ? "" : "0" ) + j;
            DayActivity activity = new DayActivity(list);
            activity.generateDescriptions(16 + rand.nextInt(6));
            this.event_day_activity.add(activity);
        }
    }
}
