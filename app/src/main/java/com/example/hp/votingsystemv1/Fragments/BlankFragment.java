package com.example.hp.votingsystemv1.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hp.votingsystemv1.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {


    public static final String ARG_TITLE = "arg_title";
    private TextView textView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_blank, container, false);

        textView = (TextView) rootView.findViewById(R.id.fragment_bottom_bar_text_activetab);

        String title = getArguments().getString(ARG_TITLE, "");
        textView.setText(title);

        return rootView;
    }
}
