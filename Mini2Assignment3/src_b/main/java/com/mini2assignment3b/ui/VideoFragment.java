package com.mini2assignment3b.ui;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.Image;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.VideoView;

import com.mini2assignment3b.R;

import java.io.IOException;

/**
 * Created by xlin2 on 2015/11/11.
 */
public class VideoFragment extends Fragment {

    private VideoView video1;
    private VideoView video2;

    private Button video1Btn;
    private Button video2Btn;

    private Button stop1Btn;
    private Button stop2Btn;

    public VideoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_video, container, false);
        getElements(view);
        setVideos();
        addListeners();
        return view;
    }

    /**
     * Get all the ui objects
     * @param view
     */
    private void getElements(View view) {
        video1 = (VideoView) view.findViewById(R.id.video1);
        video2 = (VideoView) view.findViewById(R.id.video2);
        video1Btn = (Button) view.findViewById(R.id.video1_btn);
        video2Btn = (Button) view.findViewById(R.id.video2_btn);
        stop1Btn = (Button) view.findViewById(R.id.stop1_btn);
        stop2Btn = (Button) view.findViewById(R.id.stop2_btn);
    }

    /**
     * Set resources for the videoviews
     */
    private void setVideos() {
        video1.setVideoURI(Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.video1));
        video2.setVideoURI(Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.video2));
    }

    /**
     * Set listeners for ui objects
     */
    private void addListeners() {
        video1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playVideo(1);
            }
        });

        video2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playVideo(2);
            }
        });

        stop1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopVideo(1);
            }
        });

        stop2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopVideo(2);
            }
        });
    }

    /**
     * Play a specific video according to the index
     * @param i
     */
    private void playVideo(int i) {
        if (i == 1) {
            video1.seekTo(0);
            video1.start();
        }
        if (i == 2) {
            video2.seekTo(0);
            video2.start();
        }
    }

    /**
     * Stop a video according to the index
     * @param i
     */
    private void stopVideo(int i) {
        if (i == 1)
            if (video1.isPlaying())
                video1.pause();
        if (i == 2)
            if (video2.isPlaying())
                video2.pause();
    }
}
