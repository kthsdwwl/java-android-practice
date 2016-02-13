package com.mini2assignment3a.exception;

import android.util.Log;

/**
 * Created by xlin2 on 2015/11/11.
 */
public class IDRangeException extends Exception {
    public void handle() {
        Log.e("Student id error", "Student id out of range.");
        Log.e("Student id error", this.toString());
    }
}
