package com.example.potter_desktop.meetmindera.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.potter_desktop.meetmindera.R;
import com.example.potter_desktop.meetmindera.application.AppData;
import com.example.potter_desktop.meetmindera.data.EventDay;
import com.example.potter_desktop.meetmindera.data.EventOrganised;
import com.example.potter_desktop.meetmindera.ui.activity.DayListActivity;

public class EventDayAdapter extends RecyclerView.Adapter<EventDayAdapter.EventItemCardHolder> {
    private EventOrganised event;

    private Context mContext;

    public EventDayAdapter(Context context) {
        this.mContext = context;
    }

    public void setEvent(EventOrganised event) {
        this.event = event;
    }

    @Override
    public EventItemCardHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.event_day_card, null);
        EventItemCardHolder card_holder = new EventItemCardHolder(view);
        return card_holder;
    }

    @Override
    public void onBindViewHolder(@NonNull EventItemCardHolder holder, int position) {
        EventDay item = event.getEventDayList().get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return (null != event.getEventDayList() ? event.getEventDayList().size() : 0);
    }

    public class EventItemCardHolder extends RecyclerView.ViewHolder {
        private EventDay day;
        private ImageView item_image;
        private TextView title_event_item;

        public EventItemCardHolder(View view) {
            super(view);

            this.item_image = view.findViewById(R.id.event_card_item_image);
            this.title_event_item = view.findViewById(R.id.event_card_item_title);

            // when clicking in a card from event
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, DayListActivity.class);
                    intent.putExtra(AppData.EVENT_NAME_EXTRA, event.getEventName());
                    intent.putExtra(AppData.EVENT_DAY_EXTRA, day);
                    mContext.startActivity(intent);
                }
            });
        }

        public void setItem(EventDay day) {
            this.day = day;
            this.title_event_item.setText(day.getDay());
        }
    }
}
