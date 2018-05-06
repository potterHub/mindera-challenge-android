package com.example.potter_desktop.meetmindera.data;

import java.io.Serializable;
import java.util.ArrayList;

public class DayActivity implements Serializable {
    private String activity_name;
    private ArrayList<String> list_descriptions;

    public DayActivity(String activity_name) {
        this.activity_name = activity_name;
        this.list_descriptions = new ArrayList<>();
    }

    public String getActivityName() {
        return this.activity_name;
    }

    public ArrayList<String> getListDescriptions() {
        return list_descriptions;
    }

    public void generateDescriptions(int size) {
        this.list_descriptions.clear();
        for (int j = 1; j <= size; j++) {
            String description = "Description " + (j >= 10 ? "" : "0") + j;
            this.list_descriptions.add(description);
        }
    }
}
