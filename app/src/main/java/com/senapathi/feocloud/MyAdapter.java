package com.senapathi.feocloud;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Senapathi on 04-07-2016.
 */
public class MyAdapter extends BaseAdapter {

    private Context mContext;
    private List<Track> mTracks;

    public MyAdapter(Context context, List<Track> tracks) {
        mContext = context;
        mTracks = tracks;
    }

    @Override
    public int getCount() {
        return mTracks.size();
    }

    @Override
    public Object getItem(int position) {
        return mTracks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Track track = (Track) getItem(position);

        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_row, parent, false);
            holder = new ViewHolder();
            holder.trackImageView = (ImageView) convertView.findViewById(R.id.albumImage);
            holder.titleTextView = (TextView) convertView.findViewById(R.id.albumTitle);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.titleTextView.setText(track.getTitle());
        // Trigger the download of the URL asynchronously into the image view.
        if (track.getArtworkURL() != null)
            Picasso.with(mContext).load(track.getArtworkURL()).into(holder.trackImageView);
        else
            Picasso.with(mContext).load(R.drawable.image).into(holder.trackImageView);
        return convertView;

    }

    static class ViewHolder {
        ImageView trackImageView;
        TextView titleTextView;
    }
}
