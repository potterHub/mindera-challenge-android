package com.example.potter_desktop.meetmindera.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.potter_desktop.meetmindera.R;
import com.example.potter_desktop.meetmindera.data.DayActivity;

import java.util.ArrayList;
import java.util.List;

public class EventDayListRowDescriptionAdapter extends RecyclerView.Adapter<EventDayListRowDescriptionAdapter.GridCellViewHolder> {
    private List<String> mDescriptions;

    public EventDayListRowDescriptionAdapter() {
        this.mDescriptions = new ArrayList<>();
    }

    public void setDescriptions(List<String> dataset) {
        mDescriptions.clear();
        mDescriptions.addAll(dataset);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GridCellViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new GridCellViewHolder(inflater.inflate(R.layout.description_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GridCellViewHolder view_holder, final int position) {
        view_holder.description.setText(this.mDescriptions.get(position));
    }

    @Override
    public int getItemCount() {
        return this.mDescriptions.size();
    }

    public class GridCellViewHolder extends RecyclerView.ViewHolder {
        TextView description;

        public GridCellViewHolder(View itemView) {
            super(itemView);
            this.description = itemView.findViewById(R.id.description_title_id);
        }
    }
}