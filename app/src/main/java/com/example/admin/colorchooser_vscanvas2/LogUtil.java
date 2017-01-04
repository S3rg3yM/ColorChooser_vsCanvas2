package com.example.admin.colorchooser_vscanvas2;

import android.util.Log;

public class LogUtil {
    public static void info(Object o, String message){
        Log.d(o.getClass().getName(), message);
    }
}
