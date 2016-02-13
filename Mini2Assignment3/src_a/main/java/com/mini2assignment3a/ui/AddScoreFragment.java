package com.mini2assignment3a.ui;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mini2assignment3a.R;

/**
 * Created by xlin2 on 2015/11/11.
 */
public class AddScoreFragment extends Fragment {

    public AddScoreFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_score, container, false);
    }
}
