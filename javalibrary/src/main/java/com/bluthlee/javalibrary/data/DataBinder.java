package com.bluthlee.javalibrary.data;

import android.view.View;

import com.bluthlee.javalibrary.AudioRecorder;

/**
 * Created by LC on 2017/8/31.
 */
public class DataBinder {

    public static final int STYLE_DRAG = 0;
    public static final int STYLE_FLOAT = 1;
    public static final int STYLE_THREE = 2;

    public AudioRecorder.OnFinishListener onFinishListener = null;

    public int style = STYLE_DRAG;

    public View buttonView = null;

    public String fileName = "";

    public String dirPath = "";

}
