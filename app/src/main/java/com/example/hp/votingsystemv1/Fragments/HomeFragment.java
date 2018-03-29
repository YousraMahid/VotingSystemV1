package com.example.hp.votingsystemv1.Fragments;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hp.votingsystemv1.Activities.PollActivity;
import com.example.hp.votingsystemv1.Adapters.HomeAdapter;
import com.example.hp.votingsystemv1.Loaders.HomeAsyncTaskLoader;
import com.example.hp.votingsystemv1.Models.Home;
import com.example.hp.votingsystemv1.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements LoaderManager.LoaderCallbacks<String> {

    ListView listView;
    HomeAdapter homeAdapter;
    SwipeRefreshLayout swipeRefreshLayout;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        swipeRefreshLayout=view.findViewById(R.id.srl_home);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ConnectivityManager connectivityManager=(ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo info=connectivityManager.getActiveNetworkInfo();
                if (info==null || !info.isConnected()){
                    Toast.makeText(getContext(), "there is no internet Connection4", Toast.LENGTH_SHORT).show();
                }else
                    getLoaderManager().initLoader(1,null,HomeFragment.this).forceLoad();
            }
        });
        ConnectivityManager connectivityManager=(ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info=connectivityManager.getActiveNetworkInfo();
        if (info==null || !info.isConnected()){
            Toast.makeText(getContext(), "there is no internet Connection4", Toast.LENGTH_SHORT).show();
        }else
            getLoaderManager().initLoader(1,null,this).forceLoad();

        UUID uuid=UUID.randomUUID();
        String guid=uuid.toString();
        Log.v("GUID",guid);

        listView = view.findViewById(R.id.list_item);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                Home home=homeAdapter.getItem(i);
                String pollId=home.getId();
                Log.v("POLL_ID_HOME",pollId);
                String question=home.getQuestion();
                Intent intent = new Intent(getActivity(), PollActivity.class);
                intent.putExtra("QUESTION",question);
                intent.putExtra("POLL_ID",pollId);
                startActivity(intent);


            }
        });


        return view;
    }

    private void updateUI(String data){
        String pollId;
        String subject;
        String startTime;
        String endTime;
        String question;
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
                if (object.has("poll_id"))
                    pollId=object.getString("poll_id");

                else pollId="";


                Home home=new Home(pollId,subject,question,startTime,endTime);
                homeArrayList.add(home);
            }

             homeAdapter=new HomeAdapter(getContext(),R.layout.home_item,homeArrayList);
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
            swipeRefreshLayout.setRefreshing(false);
            Log.v("DATA",data);
        }else
            Toast.makeText(getContext(), "there is no internet connection6", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }

}
