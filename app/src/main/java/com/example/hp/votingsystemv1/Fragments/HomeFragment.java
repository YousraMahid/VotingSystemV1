package com.example.hp.votingsystemv1.Fragments;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hp.votingsystemv1.Activities.PollQuestion;
import com.example.hp.votingsystemv1.Adapters.HomeAdapter;
import com.example.hp.votingsystemv1.Loaders.HomeAsyncTaskLoader;
import com.example.hp.votingsystemv1.Models.Home;
import com.example.hp.votingsystemv1.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements LoaderManager.LoaderCallbacks<String> {

    ListView listView;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ConnectivityManager connectivityManager=(ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info=connectivityManager.getActiveNetworkInfo();
        if (info==null || !info.isConnected()){
            Toast.makeText(getContext(), "there is no internet Connection ", Toast.LENGTH_SHORT).show();
        }else
            getLoaderManager().initLoader(1,null,this).forceLoad();

        listView = view.findViewById(R.id.list_item);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                Intent intent = new Intent(getActivity(), PollQuestion.class);
                startActivity(intent);

            }
        });


        return view;
    }

    private void updateUI(String data){

        String subject;
        String question;
        String startTime;
        String endTime;
        ArrayList<Home> homeArrayList=new ArrayList<>();


        try {
            JSONArray rootArray=new JSONArray(data);
            for (int i = 0; i <rootArray.length() ; i++) {
                JSONObject object=rootArray.getJSONObject(i);

                if (object.has("subject")){
                    subject=object.getString("subject");
                }else
                    subject="";

                if (object.has("question")){
                    question=object.getString("question");
                }else
                    question="";

                if (object.has("start_time"))
                    startTime=object.getString("start_time");
                else
                    startTime="";

                if (object.has("end_time"))
                    endTime=object.getString("end_time");
                else
                    endTime="";

                Home home=new Home(subject,question,startTime,endTime);
                homeArrayList.add(home);
            }

            HomeAdapter homeAdapter=new HomeAdapter(getContext(),R.layout.home_item,homeArrayList);
            listView.setAdapter(homeAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        return new HomeAsyncTaskLoader(getContext());
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        if (data != null && !data.isEmpty()){
            updateUI(data);
            Log.v("DATA",data);
        }else
            Toast.makeText(getContext(), "there is no response", Toast.LENGTH_SHORT).show();

        Log.v("DATA",data);
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }

}
