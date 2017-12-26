package com.example.hp.votingsystemv1.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.hp.votingsystemv1.Activities.PollQuestion;
import com.example.hp.votingsystemv1.Adapters.PollAdapter;
import com.example.hp.votingsystemv1.Models.PollList;
import com.example.hp.votingsystemv1.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    ListView listView;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ListView listView = view.findViewById(R.id.list_item);
        ArrayList<PollList> arrayList = new ArrayList();
        arrayList.add(new PollList("Language ", "02/12/2107"));
        arrayList.add(new PollList("Vacation ", "02/12/2107"));
        arrayList.add(new PollList("University ", "02/12/2107"));
        arrayList.add(new PollList("City ", "02/12/2107"));
        PollAdapter pollAdapter = new PollAdapter(getContext(), R.layout.item_design, arrayList);
        listView.setAdapter(pollAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                Intent intent = new Intent(getActivity(), PollQuestion.class);
                startActivity(intent);

            }
        });


        return view;
    }

}
