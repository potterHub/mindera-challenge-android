package com.example.potter_desktop.meetmindera.ui.activity;


import android.content.res.Resources;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.potter_desktop.meetmindera.R;
import com.example.potter_desktop.meetmindera.adapter.EventDayListRowAdapter;
import com.example.potter_desktop.meetmindera.application.AppData;
import com.example.potter_desktop.meetmindera.data.EventDay;

public class DayListActivity extends AppCompatActivity {
    private String mEventName;
    private EventDay mEventItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_activities_list);

        // retrieve event class
        this.mEventItem = (EventDay) getIntent().getSerializableExtra(AppData.EVENT_DAY_EXTRA);
        this.mEventName = getIntent().getStringExtra(AppData.EVENT_NAME_EXTRA);

        // set action bar
        Toolbar toolbar = findViewById(R.id.toolbar_list_activity);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            Resources res = getResources();
            String title = String.format(res.getString(R.string.mindera_title), mEventName, mEventItem.getDay());
            if (mEventItem != null && mEventName != null)
                actionBar.setTitle(title);
            actionBar.show();
        }

        if(mEventItem != null) {

            RecyclerView recyclerView = findViewById(R.id.activity_recycler_view);
            recyclerView.setHasFixedSize(true);
            EventDayListRowAdapter adapter = new EventDayListRowAdapter(this);
            adapter.setData(mEventItem.getEventDayActivities());

            LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), manager.getOrientation());
            recyclerView.addItemDecoration(dividerItemDecoration);

            recyclerView.setLayoutManager(manager);
            recyclerView.setAdapter(adapter);
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
