package com.example.potter_desktop.meetmindera.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.potter_desktop.meetmindera.R;
import com.example.potter_desktop.meetmindera.adapter.EventDayListRowDescriptionAdapter;
import com.example.potter_desktop.meetmindera.application.AppData;

import java.util.ArrayList;

public class DescriptionGridActivity extends AppCompatActivity {
    private ArrayList<String> descriptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_grid);

        // retrive descriptions list and the name of the day activity
        descriptions = getIntent().getStringArrayListExtra(AppData.DESCRIPTIONS);
        String activity_name = getIntent().getStringExtra(AppData.NAME_ACTIVITY);

        // set action bar
        Toolbar toolbar = findViewById(R.id.toolbar_description_activity);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            if (descriptions != null && activity_name != null)
                actionBar.setTitle(activity_name);
            actionBar.show();
        }

        RecyclerView recycler_view = findViewById(R.id.recycler_description_grid_id);
        EventDayListRowDescriptionAdapter myAdapter = new EventDayListRowDescriptionAdapter(this, this.descriptions);
        recycler_view.setLayoutManager(new GridLayoutManager(this, 2));
        recycler_view.setAdapter(myAdapter);
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
