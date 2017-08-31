package com.bluthlee.javalibrary.ui;

import android.content.Context;

import com.bluthlee.javalibrary.data.DataBinder;
import com.bluthlee.javalibrary.ui.recorderWidget.RecorderWidget;

/**
 * Created by LC on 2017/8/31.
 */

public class BaseAudioRecorder {

    private RecorderWidget recorderWidget = null;

    protected void initData(DataBinder dataBinder, Context context) throws Exception {
        switch (dataBinder.style) {
            case DataBinder.STYLE_DRAG:
                if (dataBinder.buttonView == null) {
                    throw new Exception("need an attached view when use drag style !!");
                } else {
                    recorderWidget = RecorderWidgetManager.initDragAudioRecorder(dataBinder.buttonView, context, dataBinder);
                }
                break;
            case DataBinder.STYLE_FLOAT:
                recorderWidget = RecorderWidgetManager.initFloatAudioRecorder(context, dataBinder);
                break;
        }
    }

    /**
     * 显示录音器控件
     */
    public void show() {
        recorderWidget.show();
    }

    /**
     * 取消显示录音器控件
     */
    public void cancel() {
        recorderWidget.cancel();
    }

    public boolean isShowing() {
        return recorderWidget.isShowing();
    }

}
