package com.mini2assignment2.exception;

import android.util.Log;

/**
 * Created by xlin2 on 2015/11/6.
 */
public class MyException extends Exception {
    public void handle() {
        Log.e("Input error", "Input invalid or empty.");
        Log.e("Input error", this.toString());
    }
}
