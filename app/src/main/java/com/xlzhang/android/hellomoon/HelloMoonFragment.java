package com.xlzhang.android.hellomoon;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class HelloMoonFragment extends Fragment {
    private Button mPlayButton;
    private Button mStopButton;
    private VideoView mView;
    private boolean stopped = true;

    public boolean isStopped() {
        return stopped;
    }

    public void setStopped(boolean stopped) {
        this.stopped = stopped;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_hello_moon, container, false);

        mView = v.findViewById(R.id.hellomoon_videoView);

        mPlayButton = v.findViewById(R.id.hellomoon_playPauseButton);
        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isStopped()){
                    Uri uri = Uri.parse("android.resource://com.xlzhang.android.hellomoon/raw/samplevideo_1280x720_20mb");
                    mView.setVideoURI(uri);
                    mView.start();
                    setStopped(false);
                }
                else if(mView.isPlaying()){
                    mView.pause();
                }
                else {
                    mView.start();
                }
            }
        });
        mStopButton = v.findViewById(R.id.hellomoon_stopButton);
        mStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mView.stopPlayback();
                setStopped(true);
            }
        });

        return v;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mView.stopPlayback();
    }
}
