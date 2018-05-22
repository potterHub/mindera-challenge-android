package com.example.potter_desktop.meetmindera.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.potter_desktop.meetmindera.R;
import com.example.potter_desktop.meetmindera.adapter.EventAdapter;
import com.example.potter_desktop.meetmindera.data.EventOrganised;

import java.util.ArrayList;

public class Events extends Fragment {
    private ArrayList<EventOrganised> mAllEvents;

    public Events() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_events, container, false);

        this.initDummyEvents();
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_event_lists);
        recyclerView.setHasFixedSize(true);
        EventAdapter adapter = new EventAdapter(getContext());
        adapter.setEventLists(this.mAllEvents);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);

        return view;
    }

    /*
        Init all the event lists
     */
    private void initDummyEvents() {
        String[] events = getResources().getStringArray(R.array.events);
        this.mAllEvents = new ArrayList<>();
        for (int i = 1; i <= events.length; i++) {
            EventOrganised eventList = new EventOrganised(events[i - 1]);
            // i==1 true if first row (that is going to make the image header seen on the top)
            eventList.generateDummyDays(i == 1);
            mAllEvents.add(eventList);
        }
    }
}
