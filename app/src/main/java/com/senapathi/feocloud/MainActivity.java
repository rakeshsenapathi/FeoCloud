package com.senapathi.feocloud;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

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
    private TextView mSelectedTrackTitle;
    private ImageView mSelectedTrackImage;
    private CardView mToolbar;
    private MediaPlayer mMediaPlayer;
    private ImageView mPlayerControl;

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
        mSelectedTrackTitle = (TextView) findViewById(R.id.selected_track_title);
        mSelectedTrackImage = (ImageView) findViewById(R.id.selected_track_image);
        mToolbar = (CardView) findViewById(R.id.toolbar);
        //
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(mToolbar != null)
                mToolbar.setVisibility(View.VISIBLE);
                Track track = mListItems.get(position);
                mSelectedTrackTitle.setText(track.getTitle());
                if (track.getArtworkURL() != null)
                    Picasso.with(MainActivity.this).load(track.getArtworkURL()).into(mSelectedTrackImage);
                else
                    Picasso.with(MainActivity.this).load(Utils.dummyImage).into(mSelectedTrackImage);


            }
        });

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
