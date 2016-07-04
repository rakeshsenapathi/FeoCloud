package com.senapathi.feocloud;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Tracks";
    private List<Track> mListItems;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        mListItems = new ArrayList<Track>();
        ListView listView = (ListView) findViewById(R.id.listView);
        mAdapter = new MyAdapter(this, mListItems);
        listView.setAdapter(mAdapter);

        //
        // Making service call to get recent tracks
        SCService scService = SoundCloud.getService();
        scService.getRecentTracks(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()), new Callback<List<Track>>() {
                    @Override
                    public void success(List<Track> tracks, Response response) {
                        Log.d(TAG, "First track title :" + tracks.get(0).getTitle());
                        loadTracks(tracks);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.d(TAG, "Error occured");
                    }
                }
        );
    }

    private void loadTracks(List<Track> tracks) {

        mListItems.clear();
        mListItems.addAll(tracks);
        mAdapter.notifyDataSetChanged();
    }


}
