package com.madmobiledevs.videoapp;

import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.LoopingMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

import java.util.ArrayList;

public class videoAdapter extends RecyclerView.Adapter<videoAdapter.MyViewHolder> {

    ArrayList<videoModel> videos;

    public videoAdapter(ArrayList<videoModel> videos) {
        this.videos = videos;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_layout, parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.setData(videos.get(position));
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title,desc;
        SimpleExoPlayerView videoView;
        SimpleExoPlayer exoPlayer;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_TxtVw);
            desc = itemView.findViewById(R.id.desc_TxtVw);

            videoView = itemView.findViewById(R.id.video_View);

        }

        void setData(videoModel videoModel){

            title.setText(videoModel.getTitle());
            desc.setText(videoModel.getDesc());

            try {

                // bandwisthmeter is used for
                // getting default bandwidth
                BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();

                // track selector is used to navigate between
                // video using a default seekbar.
                TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));

                // we are adding our track selector to exoplayer.
                exoPlayer = ExoPlayerFactory.newSimpleInstance(videoView.getContext(), trackSelector);

                // we are parsing a video url
                // and parsing its video uri.
                Uri videouri = Uri.parse(videoModel.getUrl());

                // we are creating a variable for datasource factory
                // and setting its user agent as 'exoplayer_view'
                DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory("exoplayer_video");

                // we are creating a variable for extractor factory
                // and setting it to default extractor factory.
                ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();

                // we are creating a media source with above variables
                // and passing our event handler as null,
                MediaSource mediaSource = new ExtractorMediaSource(videouri, dataSourceFactory, extractorsFactory, null, null);

                // inside our exoplayer view
                // we are setting our player
                videoView.setPlayer(exoPlayer);

                LoopingMediaSource loopingSource = new LoopingMediaSource(mediaSource);
                exoPlayer.prepare(loopingSource);


                // we are setting our exoplayer
                // when it is ready.
                exoPlayer.setPlayWhenReady(true);

            } catch (Exception e) {
                // below line is used for
                // handling our errors.
                Log.e("TAG", "Error : " + e.toString());
            }
        }
    }
}
