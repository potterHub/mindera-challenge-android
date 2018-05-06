package com.example.potter_desktop.meetmindera.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.potter_desktop.meetmindera.R;

import java.util.ArrayList;

public class EventDayListRowDescriptionAdapter extends RecyclerView.Adapter<EventDayListRowDescriptionAdapter.GridCellViewHolder> {
    private Context mContext;
    private ArrayList<String> description;

    public EventDayListRowDescriptionAdapter(Context mContext, ArrayList<String> description) {
        this.mContext = mContext;
        this.description = description;
    }

    @NonNull
    @Override
    public GridCellViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        return new GridCellViewHolder(inflater.inflate(R.layout.description_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GridCellViewHolder view_holder, final int position) {
        view_holder.setDescription(this.description.get(position));
    }

    @Override
    public int getItemCount() {
        return this.description.size();
    }

    public class GridCellViewHolder extends RecyclerView.ViewHolder {
        private TextView description_text_view;

        public GridCellViewHolder(View itemView) {
            super(itemView);
            this.description_text_view = itemView.findViewById(R.id.description_title_id);
        }

        public void setDescription(String description) {
            this.description_text_view.setText(description);
        }
    }
}