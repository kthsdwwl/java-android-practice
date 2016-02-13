package com.mini2assignment3b.ui;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.mini2assignment3b.R;

import java.io.IOException;

/**
 * Created by xlin2 on 2015/11/11.
 */
public class SongFragment extends Fragment {

    private Button song1Btn;
    private Button song2Btn;
    private Button song3Btn;
    private Button stopBtn;

    private ImageView cover;

    private MediaPlayer player1;
    private MediaPlayer player2;
    private MediaPlayer player3;

    public SongFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_song, container, false);
        getElements(view);
        setPlayers();
        addListeners();
        return view;
    }

    /**
     * Get all the ui objects
     * @param view
     */
    private void getElements(View view) {
        song1Btn = (Button) view.findViewById(R.id.song1_btn);
        song2Btn = (Button) view.findViewById(R.id.song2_btn);
        song3Btn = (Button) view.findViewById(R.id.song3_btn);
        stopBtn = (Button) view.findViewById(R.id.stop_music_btn);
        cover = (ImageView) view.findViewById(R.id.img_cover);
    }

    /**
     * Set resources for all the media players
     */
    private void setPlayers() {
        player1 = new MediaPlayer();
        player2 = new MediaPlayer();
        player3 = new MediaPlayer();
        try {
            // get music from asset
            AssetFileDescriptor d1 = getActivity().getAssets().openFd("music1.mp3");
            AssetFileDescriptor d2 = getActivity().getAssets().openFd("music2.mp3");
            AssetFileDescriptor d3 = getActivity().getAssets().openFd("music3.mp3");
            // set resources
            player1.setDataSource(d1.getFileDescriptor(), d1.getStartOffset(), d1.getLength());
            player2.setDataSource(d2.getFileDescriptor(), d2.getStartOffset(), d2.getLength());
            player3.setDataSource(d3.getFileDescriptor(), d3.getStartOffset(), d3.getLength());

            d1.close();
            d2.close();
            d3.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add listeners to ui objects
     */
    private void addListeners() {
        song1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playMusic(1);
            }
        });

        song2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playMusic(2);
            }
        });

        song3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playMusic(3);
            }
        });

        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopMusicIfPlaying();
            }
        });
    }

    /**
     * Play the specific music according to music index
     * @param i
     */
    private void playMusic(int i) {
        try {
            if (i == 1) {
                stopMusicIfPlaying();
                player1.prepare();
                player1.start();
                cover.setImageResource(R.drawable.cover1);
            }


            if (i == 2) {
                stopMusicIfPlaying();
                player2.prepare();
                player2.start();
                cover.setImageResource(R.drawable.cover2);
            }

            if (i == 3) {
                stopMusicIfPlaying();
                player3.prepare();
                player3.start();
                cover.setImageResource(R.drawable.cover3);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Stop the music if it is being played
     */
    private void stopMusicIfPlaying() {
        if (player1.isPlaying())
            player1.stop();
        if (player2.isPlaying())
            player2.stop();
        if (player3.isPlaying())
            player3.stop();
    }
}
