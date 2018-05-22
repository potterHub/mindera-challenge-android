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
    private Context mContext;
    private EventOrganised mEvent;

    public EventDayAdapter(Context context) {
        this.mContext = context;
    }

    public void setEvent(EventOrganised mEvent) {
        this.mEvent = mEvent;
    }

    @Override
    public EventItemCardHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.event_day_card, null);
        EventItemCardHolder cardHolder = new EventItemCardHolder(view);
        return cardHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EventItemCardHolder holder, int position) {
        final EventDay item = mEvent.getEventDayList().get(position);
        holder.mDay = item;
        holder.mTitleEventItem.setText(item.getDay());

        // when clicking in a card from mEvent
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DayListActivity.class);
                intent.putExtra(AppData.EVENT_NAME_EXTRA, mEvent.getEventName());
                intent.putExtra(AppData.EVENT_DAY_EXTRA, item);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != mEvent.getEventDayList() ? mEvent.getEventDayList().size() : 0);
    }

    public static class EventItemCardHolder extends RecyclerView.ViewHolder {
        public View mCardView;
        public EventDay mDay;
        public ImageView mItemImage;
        public TextView mTitleEventItem;

        public EventItemCardHolder(final View view) {
            super(view);

            mItemImage = view.findViewById(R.id.event_card_item_image);
            mTitleEventItem = view.findViewById(R.id.event_card_item_title);
            mCardView = view.findViewById(R.id.card_event_day_view);
        }
    }
}
