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
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventRowHolder> {
    private List<EventOrganised> mEventLists;
    private Context mContext;

    public EventAdapter(Context context) {
        this.mEventLists = new ArrayList<>();
        this.mContext = context;
    }

    @NonNull
    @Override
    public EventRowHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // case 0 the is the mEvent header
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
            holder.setEvent(mEventLists.get(i - 1));
            holder.setEventItemAdapter(mContext);
        }
    }

    public void setEventLists(ArrayList<EventOrganised> events) {
        mEventLists.clear();
        mEventLists.addAll(events);
        notifyDataSetChanged();
    }

    public void addEvent(int position, EventOrganised event) {
        mEventLists.add(position, event);
        notifyItemInserted(position);
    }

    @Override
    public int getItemCount() {
        return (null != mEventLists ? mEventLists.size() + 1 : 1);
    }

    public class EventRowHolder extends RecyclerView.ViewHolder {
        private boolean mFirstRow;
        private TextView mEventTitle;
        private RecyclerView mRecyclerViewEventList;
        private EventOrganised mEvent;

        public EventRowHolder(View view, int row) {
            super(view);
            this.mFirstRow = (row == 0);
            if (!mFirstRow) {
                this.mEventTitle = view.findViewById(R.id.event_row_title);
                this.mRecyclerViewEventList = view.findViewById(R.id.recycler_view_list);
            }
        }

        private void setEvent(EventOrganised event) {
            if (event != null && !mFirstRow) {
                this.mEvent = event;
                this.mEventTitle.setText(event.getEventName());
            }
        }

        private void setEventItemAdapter(Context mContext) {
            if(!mFirstRow) {
                EventDayAdapter mItemAdapter = new EventDayAdapter(mContext);
                mItemAdapter.setEvent(this.mEvent);

                mRecyclerViewEventList.setHasFixedSize(true);
                mRecyclerViewEventList.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
                mRecyclerViewEventList.addItemDecoration(new RecyclerView.ItemDecoration() {
                    @Override
                    public void getItemOffsets(Rect outRect, View view,
                                               RecyclerView parent, RecyclerView.State state) {
                        outRect.right = EventAdapter.this.mContext.getResources().getDimensionPixelSize(R.dimen.event_item_spacing);
                    }
                });
                mRecyclerViewEventList.setAdapter(mItemAdapter);
            }
        }
    }
}

