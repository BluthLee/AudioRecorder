package com.bluthlee.javalibrary;

import android.content.Context;
import android.view.View;

import com.bluthlee.javalibrary.data.DataBinder;
import com.bluthlee.javalibrary.ui.BaseAudioRecorder;

/**
 * Created by LC on 2017/8/31.
 */

public class AudioRecorder extends BaseAudioRecorder {

    public interface OnFinishListener {
        void onFinish(String filePath);
    }

    private DataBinder dataBinder = new DataBinder();

    protected BaseAudioRecorder init(Context context) throws Exception {
        super.initData(dataBinder, context);
        return this;
    }

    /**
     * 设置样式
     */
    protected BaseAudioRecorder setStyle(int style) {
        dataBinder.style = style;
        return this;
    }

    /**
     * 设置录音完成的监听器
     */
    protected BaseAudioRecorder setListener(OnFinishListener onFinishListener) {
        dataBinder.onFinishListener = onFinishListener;
        return this;
    }

    /**
     * 当样式选择第一种时，需要指定一个录音按键用作触摸时启动录音
     */
    protected BaseAudioRecorder setAttachedRecorderButton(View view) {
        dataBinder.buttonView = view;
        return this;
    }

    /**
     * 设置文件名称，不包含文件扩展名
     */
    protected BaseAudioRecorder setFileName(String fileName) {
        dataBinder.fileName = fileName;
        return this;
    }

    /**
     * 设置文件存放文件夹的绝对路径
     */
    protected BaseAudioRecorder setDirPath(String dirPath) {
        dataBinder.dirPath = dirPath;
        return this;
    }

}
