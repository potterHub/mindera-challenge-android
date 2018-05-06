package com.example.potter_desktop.meetmindera.ui.activity;


import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.potter_desktop.meetmindera.R;
import com.example.potter_desktop.meetmindera.adapter.EventDayListRowAdapter;
import com.example.potter_desktop.meetmindera.application.AppData;
import com.example.potter_desktop.meetmindera.data.EventDay;

public class DayListActivity extends AppCompatActivity {
    private String event_name;
    private EventDay event_item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_activities_list);

        // retrieve event class
        this.event_item = (EventDay) getIntent().getSerializableExtra(AppData.EVENT_DAY_EXTRA);
        this.event_name = getIntent().getStringExtra(AppData.EVENT_NAME_EXTRA);

        // set action bar
        Toolbar toolbar = findViewById(R.id.toolbar_list_activity);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            if (event_item != null && event_name != null)
                actionBar.setTitle(event_name + " _ " + event_item.getDay());
            actionBar.show();
        }

        ListView list_view = findViewById(R.id.activity_list_view);

        if(event_item != null) {
            // instantiate the adapter
            EventDayListRowAdapter listAdapter = new EventDayListRowAdapter(DayListActivity.this, event_item.getEventDayActivities());

            //attach the adapter to the list
            list_view.setAdapter(listAdapter);
        }
    }

    // to make home button(back button on top) to act as back button on bottom
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
