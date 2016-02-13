package com.mini2assignment3b.ui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mini2assignment3b.R;

/**
 * Created by xlin2 on 2015/11/11.
 */
public class ButtonsFragment extends Fragment {

    private Button songBtn;
    private Button videoBtn;
    private Button wallpaperBtn;
    private Button mailBtn;

    public ButtonsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_buttons, container, false);
        getElements(view);
        addListeners();
        return view;
    }

    /**
     * Get all the object elements
     * @param view
     */
    private void getElements(View view) {
        songBtn = (Button) view.findViewById(R.id.song_btn);
        videoBtn = (Button) view.findViewById(R.id.video_btn);
        wallpaperBtn = (Button) view.findViewById(R.id.wallpaper_btn);
        mailBtn = (Button) view.findViewById(R.id.mail_btn);
    }

    /**
     * Add listeners to the ui objects
     */
    private void addListeners() {
        songBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpTo(SongActivity.class);
            }
        });

        videoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpTo(VideoActivity.class);
            }
        });

        wallpaperBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpTo(WallpaperActivity.class);
            }
        });

        mailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpTo(MailActivity.class);
            }
        });
    }

    /**
     * Jump to target activity
     * @param target
     */
    void jumpTo(Class<?> target) {
        Intent intent = new Intent(getActivity(), target);
        startActivity(intent);
    }
}
