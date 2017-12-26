package com.example.hp.votingsystemv1.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.hp.votingsystemv1.Models.PollList;
import com.example.hp.votingsystemv1.R;

import java.util.ArrayList;

/**
 * Created by hp on 12/26/2017.
 */

public class PollAdapter extends ArrayAdapter<PollList> {
    private Context context;
    private int resource;
    private ArrayList<PollList> objects;


    public PollAdapter(Context context, int resource, ArrayList<PollList> objects) {
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
        TextView title = convertView.findViewById(R.id.title);
        TextView date = convertView.findViewById(R.id.date);
        PollList resultOfArticle = objects.get(position);
        title.setText(resultOfArticle.getTitle());
        date.setText(resultOfArticle.getDate());
        return convertView;
    }
}
