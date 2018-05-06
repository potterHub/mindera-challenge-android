package com.example.potter_desktop.meetmindera.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.potter_desktop.meetmindera.R;
import com.example.potter_desktop.meetmindera.application.AppData;
import com.example.potter_desktop.meetmindera.data.DayActivity;
import com.example.potter_desktop.meetmindera.ui.activity.DescriptionGridActivity;

import java.util.ArrayList;

public class EventDayListRowAdapter extends ArrayAdapter<DayActivity> {
    private ArrayList<DayActivity> event_lists;
    private Context mContext;

    public EventDayListRowAdapter(Context context, ArrayList<DayActivity> all_events) {
        super(context, R.layout.row_day_activities_list, all_events);
        this.mContext = context;
    }

    public void setEventLists(ArrayList<DayActivity> all_events) {
        super.clear();
        super.addAll(all_events);
        this.notifyDataSetChanged();
    }

    @Override // to make our custom adapter build each row according with our list layout
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.row_day_activities_list, parent, false); // get out the custom layout
        }

        final DayActivity day_activity = super.getItem(position);
        String name_activity = day_activity.getActivityName();

        // set the custom row view values
        if (row != null && name_activity != null) {
            ((TextView) row.findViewById(R.id.title)).setText(name_activity);
            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, DescriptionGridActivity.class);
                    intent.putExtra(AppData.NAME_ACTIVITY, day_activity.getActivityName());
                    intent.putExtra(AppData.DESCRIPTIONS, day_activity.getListDescriptions());
                    EventDayListRowAdapter.this.mContext.startActivity(intent);
                }
            });
        }

        // return the changed row
        return row;
    }
}
