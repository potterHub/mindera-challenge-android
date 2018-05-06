package com.example.potter_desktop.meetmindera.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.potter_desktop.meetmindera.R;
import com.example.potter_desktop.meetmindera.data.EventOrganised;

import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventRowHolder> {
    private ArrayList<EventOrganised> event_lists;
    private Context mContext;

    public EventAdapter(Context context, ArrayList<EventOrganised> all_events) {
        this.mContext = context;
        this.event_lists = all_events;
    }

    @NonNull
    @Override
    public EventRowHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // case 0 the is the event header
        View view;
        if (i == 1) {
            // first row
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.event_day_list_row_header, null);
        } else {
            // all the other rows
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.event_day_list_row, null);
        }
        return new EventRowHolder(view, i);
    }

    // used so that the onCreateViewHolder receives 1 for the first row and 2 for all the others
    @Override
    public int getItemViewType(int position) {
        if (position == 0) return 1;
        else return 2;
    }
    @Override
    public void onBindViewHolder(@NonNull EventRowHolder holder, int i) {
        if(i > 0) {
            holder.setEvent(event_lists.get(i - 1));
            holder.setEventItemAdapter(mContext);
        }
    }

    public void setEventLists(ArrayList<EventOrganised> events) {
        event_lists.clear();
        event_lists.addAll(events);
        notifyDataSetChanged();
    }

    public void addEvent(int position, EventOrganised event) {
        event_lists.add(position, event);
        notifyItemInserted(position);
    }

    @Override
    public int getItemCount() {
        return (null != event_lists ? event_lists.size() + 1 : 1);
    }

    public class EventRowHolder extends RecyclerView.ViewHolder {
        private boolean first_row;
        private TextView event_title;
        private RecyclerView recycler_view_event_list;
        private EventOrganised event;

        public EventRowHolder(View view,int row) {
            super(view);
            this.first_row = (row == 0);
            if (!first_row) {
                this.event_title = view.findViewById(R.id.event_row_title);
                this.recycler_view_event_list = view.findViewById(R.id.recycler_view_list);
            }
        }

        private void setEvent(EventOrganised event) {
            if (event != null && !first_row) {
                this.event = event;
                this.event_title.setText(event.getEventName());
            }
        }

        private void setEventItemAdapter(Context mContext) {
            if(!first_row) {
                EventDayAdapter item_adapter = new EventDayAdapter(mContext);
                item_adapter.setEvent(this.event);

                recycler_view_event_list.setHasFixedSize(true);
                recycler_view_event_list.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
                recycler_view_event_list.addItemDecoration(new RecyclerView.ItemDecoration() {
                    @Override
                    public void getItemOffsets(Rect outRect, View view,
                                               RecyclerView parent, RecyclerView.State state) {
                        outRect.right = EventAdapter.this.mContext.getResources().getDimensionPixelSize(R.dimen.event_item_spacing);
                    }
                });
                recycler_view_event_list.setAdapter(item_adapter);
            }
        }
    }
}

