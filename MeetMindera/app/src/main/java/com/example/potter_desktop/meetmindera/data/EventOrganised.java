package com.example.potter_desktop.meetmindera.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EventOrganised implements Serializable {
    private String mEventName;
    private List<EventDay> mEventDayList;


    public EventOrganised(String eventName) {
        this.mEventDayList = new ArrayList<>();
        this.setEventName(eventName);
    }

    public void addEventDay(EventDay eventDay) {
        this.mEventDayList.add(eventDay);
    }

    public String getEventName() {
        return mEventName;
    }

    public void setEventName(String eventName) {
        this.mEventName = eventName;
    }

    public List<EventDay> getEventDayList() {
        return mEventDayList;
    }

    public void resetEventDayList() {
        this.mEventDayList.clear();
    }

    public void generateDummyDays(boolean withDays) {
        Random rand = new Random();
        for (int j = 1; j <= 11; j++) {
            EventDay day = new EventDay("");
            day.generateDayActivities(15 + rand.nextInt(5));
            if (withDays)
                day.setDay("Day " + (j >= 10 ? "" : "0") + j);
            mEventDayList.add(day);
        }
    }
}
