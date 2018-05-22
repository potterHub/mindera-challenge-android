package com.example.potter_desktop.meetmindera.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DayActivity implements Serializable {
    private String mActivityName;
    private List<String> mListDescriptions;

    public DayActivity(String activityName) {
        this.mActivityName = activityName;
        this.mListDescriptions = new ArrayList<>();
    }

    public String getActivityName() {
        return this.mActivityName;
    }

    public List<String> getListDescriptions() {
        return mListDescriptions;
    }

    public void generateDescriptions(int size) {
        this.mListDescriptions.clear();
        for (int j = 1; j <= size; j++) {
            String description = "Description " + (j >= 10 ? "" : "0") + j;
            this.mListDescriptions.add(description);
        }
    }
}
