package com.mini2assignment3a.exception;

import android.util.Log;

/**
 * Created by xlin2 on 2015/11/11.
 */
public class ExistException extends Exception {
    public void handle() {
        Log.e("Student exist error", "Student id already exist.");
        Log.e("Student exist error", this.toString());
    }
}
