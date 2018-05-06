package com.example.potter_desktop.meetmindera.application;

import android.app.Application;

public class AppData extends Application {
    // paths used to pass information between activities and fragments
    public static final String EVENT_NAME_EXTRA = "Event";
    public static final String EVENT_DAY_EXTRA = "EventDay";
    public static final String NAME_ACTIVITY = "NameActivity";
    public static final String DESCRIPTIONS = "Descriptions";

    private static AppData context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static AppData getApplication() {
        return context;
    }
}
