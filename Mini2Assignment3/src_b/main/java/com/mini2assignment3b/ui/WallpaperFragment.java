package com.mini2assignment3b.ui;

import android.app.Activity;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.mini2assignment3b.R;

/**
 * Created by xlin2 on 2015/11/11.
 */
public class WallpaperFragment extends Fragment {

    private Button wall1_btn;
    private Button wall2_btn;
    private Button wall3_btn;

    private ImageView wallpaper;

    public WallpaperFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_wallpaper, container, false);
        getElements(view);
        addListeners();
        return view;
    }

    /**
     * Get all the ui objects
     * @param view
     */
    private void getElements(View view) {
        wall1_btn = (Button) view.findViewById(R.id.wall1_btn);
        wall2_btn = (Button) view.findViewById(R.id.wall2_btn);
        wall3_btn = (Button) view.findViewById(R.id.wall3_btn);
        wallpaper = (ImageView) view.findViewById(R.id.img_wallpaper);
    }

    /**
     * Set listeners for the ui objects
     */
    private void addListeners() {
        wall1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setWallpaper(1);
            }
        });

        wall2_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setWallpaper(2);
            }
        });

        wall3_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setWallpaper(3);
            }
        });
    }

    /**
     * Set corresponding wall paper according to index
     * @param i
     */
    private void setWallpaper(int i) {
        if (i == 1)
            wallpaper.setImageResource(R.drawable.wall1);
        if (i == 2)
            wallpaper.setImageResource(R.drawable.wall2);
        if (i == 3)
            wallpaper.setImageResource(R.drawable.wall3);
    }
}
