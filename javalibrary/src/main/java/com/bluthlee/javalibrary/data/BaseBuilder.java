package com.bluthlee.javalibrary.data;

import com.bluthlee.javalibrary.AudioRecorder;

/**
 * Created by LC on 2017/8/31.
 */

public class BaseBuilder {

    protected AudioRecorder.OnFinishListener onFinishListener = null;
    protected String fileName = "";
    protected String dirPath = "";

    public BaseBuilder setOnFinishListener(AudioRecorder.OnFinishListener onFinishListener) {
        this.onFinishListener = onFinishListener;
        return this;
    }

    public BaseBuilder setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public BaseBuilder setDirPath(String dirPath) {
        this.dirPath = dirPath;
        return this;
    }

}
