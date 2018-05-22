package com.example.potter_desktop.meetmindera.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.potter_desktop.meetmindera.R;
import com.example.potter_desktop.meetmindera.application.AppData;
import com.example.potter_desktop.meetmindera.data.DayActivity;
import com.example.potter_desktop.meetmindera.ui.activity.DescriptionGridActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EventDayListRowAdapter extends RecyclerView.Adapter<EventDayListRowAdapter.ViewHolderList> {
    private Context mContext;
    private List<DayActivity> mEvents;

    public EventDayListRowAdapter(Context context) {
        this.mContext = context;
        this.mEvents = new ArrayList<>();
    }

    public void setData(List<DayActivity> dataset) {
        mEvents.clear();
        mEvents.addAll(dataset);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolderList onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_day_activities_list, parent, false);
        ViewHolderList view_holder = new ViewHolderList(view);
        return view_holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderList holder, int position) {
        final DayActivity dayActivity = this.mEvents.get(position);
        final String nameActivity = dayActivity.getActivityName();

        // set the custom row view values
        if (nameActivity != null) {
            holder.mTitle.setText(nameActivity);
            holder.mRowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, DescriptionGridActivity.class);
                    intent.putExtra(AppData.NAME_ACTIVITY, nameActivity);
                    intent.putExtra(AppData.DESCRIPTIONS, (Serializable) dayActivity.getListDescriptions());
                    mContext.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return (null != mEvents ? mEvents.size() : 0);
    }

    public static class ViewHolderList extends RecyclerView.ViewHolder{
        public View mRowView;
        public TextView mTitle;

        public ViewHolderList(View view) {
            super(view);
            this.mTitle = view.findViewById(R.id.title);
            this.mRowView = view.findViewById(R.id.list_day_row);
        }
    }
}
