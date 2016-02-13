package com.mini2assignment3a.exception;

import android.util.Log;

/**
 * Created by xlin2 on 2015/11/11.
 */
public class ScoreRangeException extends Exception {
    public void handle() {
        Log.e("Score error", "Score out of range.");
        Log.e("Score error", this.toString());
    }
}
