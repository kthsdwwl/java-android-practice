package com.mini2assignment4.exception;

import android.util.Log;

/**
 * Created by xlin2 on 2015/11/16.
 */
public class GPSException extends Exception {
    private int errNo;

    public GPSException(ErrType type) {
        setErrNo(type.ordinal());
    }

    public int getErrNo() {
        return errNo;
    }

    public void setErrNo(int errNo) {
        this.errNo = errNo;
    }

    /**
     * Get error message according to error number
     * @return error message
     */
    public String getErrMsg() {
        switch(errNo) {
            case 0:
                return "Cannot get location manager.";
            case 1:
                return "Neither network nor GPS is enabled";
            default:
                return "Unknown error.";
        }
    }

    /**
     * Output error message through log
     */
    public void handle() {
        Log.e("GPSException", getErrMsg());
    }
}
