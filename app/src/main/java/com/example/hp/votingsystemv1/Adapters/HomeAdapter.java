package com.example.hp.votingsystemv1.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.hp.votingsystemv1.Models.Home;
import com.example.hp.votingsystemv1.R;

import java.util.ArrayList;

/**
 * Created by hp on 12/26/2017.
 */

public class HomeAdapter extends ArrayAdapter<Home> {
    private Context context;
    private int resource;
    private ArrayList<Home> objects;


    public HomeAdapter(Context context, int resource, ArrayList<Home> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(resource, null);
        }
        TextView subject=convertView.findViewById(R.id.subject);
        TextView question = convertView.findViewById(R.id.title);
        TextView startTime = convertView.findViewById(R.id.start_time);
        TextView endTime = convertView.findViewById(R.id.end_time);
        Home resultOfArticle = objects.get(position);
        subject.setText(resultOfArticle.getSubject());
        question.setText(resultOfArticle.getQuestion());
        startTime.setText(resultOfArticle.getStartTime());
        endTime.setText(resultOfArticle.getEndTime());
        return convertView;
    }
}
