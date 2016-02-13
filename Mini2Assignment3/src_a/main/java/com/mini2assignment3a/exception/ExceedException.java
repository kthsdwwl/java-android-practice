package com.mini2assignment3a.exception;

import android.util.Log;

/**
 * Created by GIGABYTE on 2015/11/11.
 */
public class ExceedException extends Exception {
    public void handle() {
        Log.e("Too many students error", "Student number more than 40.");
        Log.e("Too many students error", this.toString());
    }
}
