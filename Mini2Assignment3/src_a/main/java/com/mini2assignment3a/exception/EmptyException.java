package com.mini2assignment3a.exception;

import android.util.Log;

/**
 * Created by xlin2 on 2015/11/11.
 */
public class EmptyException extends Exception {
    public void handle() {
        Log.e("Input error", "Input empty.");
        Log.e("Input error", this.toString());
    }
}
