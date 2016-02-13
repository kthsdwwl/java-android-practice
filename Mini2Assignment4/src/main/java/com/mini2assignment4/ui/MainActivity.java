package com.mini2assignment4.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mini2assignment4.R;

/**
 * Created by xlin2 on 2015/11/16.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().add(R.id.container, new GeoFragment()).commit();
    }
}
