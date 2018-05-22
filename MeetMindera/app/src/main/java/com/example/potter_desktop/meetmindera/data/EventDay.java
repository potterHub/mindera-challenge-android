package com.example.potter_desktop.meetmindera.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EventDay implements Serializable{
    private String mDay;
    private List<DayActivity> mEventDayActivity;

    public EventDay(String day) {
        this.mEventDayActivity = new ArrayList<>();
        this.mDay = day;
    }

    public String getDay() {
        return mDay;
    }

    public void setDay(String day) {
        this.mDay = day;
    }

    public List<DayActivity> getEventDayActivities() {
        return mEventDayActivity;
    }

    public void setEventDayActivities(List<DayActivity> event_day_activities) {
        this.mEventDayActivity = event_day_activities;
    }

    public void generateDayActivities(int size){
        Random rand = new Random();
        this.mEventDayActivity.clear();
        for (int j = 1; j <= size; j++) {
            String list = "List " + (j >= 10 ? "" : "0" ) + j;
            DayActivity activity = new DayActivity(list);
            activity.generateDescriptions(16 + rand.nextInt(6));
            this.mEventDayActivity.add(activity);
        }
    }
}
