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
import com.example.potter_desktop.meetmindera.data.EventDay;
import com.example.potter_desktop.meetmindera.data.EventOrganised;

import java.util.ArrayList;

public class Events extends Fragment {
    private ArrayList<EventOrganised> all_events;

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

        this.init_dummy_events();
        RecyclerView recycler_view = view.findViewById(R.id.recycler_view_event_lists);
        recycler_view.setHasFixedSize(true);
        EventAdapter adapter = new EventAdapter(getContext(), this.all_events);

        recycler_view.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recycler_view.setAdapter(adapter);

        return view;
    }

    /*
        Init all the event lists
     */
    private void init_dummy_events() {
        String[] events = getResources().getStringArray(R.array.Events);
        this.all_events = new ArrayList<>();
        for (int i = 1; i <= events.length; i++) {
            EventOrganised event_list = new EventOrganised(events[i - 1]);
            // i==1 true if first row (that is going to make the image header seen on the top)
            event_list.generateDummyDays(i == 1);
            all_events.add(event_list);
        }
    }
}
