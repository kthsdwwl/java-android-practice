package com.mini2assignment3b.exception;

import android.util.Log;

/**
 * Created by xlin2 on 2015/11/11.
 */
public class MyException extends Exception {
    public void handle() {
        Log.e("Empty input error", "Subject or content cannot be empty.");
        Log.e("Empty input error", this.toString());
    }
}
