package com.bluthlee.javalibrary.ui;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;

import com.bluthlee.javalibrary.data.DataBinder;
import com.bluthlee.javalibrary.ui.recorderWidget.DragRecorderWidget;
import com.bluthlee.javalibrary.ui.recorderWidget.FloatRecorderWidget;
import com.bluthlee.javalibrary.ui.recorderWidget.RecorderWidget;

/**
 * Created by LC on 2017/8/31.
 */

public class RecorderWidgetManager {

    public static RecorderWidget initDragAudioRecorder(View recordButton, Context context, DataBinder dataBinder) {
        DragRecorderWidget dragRecorderWidget = (DragRecorderWidget) RecorderWidgetFactory.getRecorderWidget(DataBinder.STYLE_DRAG, context, dataBinder);
        setTouchListener(recordButton, dragRecorderWidget);
        return dragRecorderWidget;
    }

    public static RecorderWidget initFloatAudioRecorder(Context context, DataBinder dataBinder) {
        FloatRecorderWidget floatRecorderWidget = (FloatRecorderWidget) RecorderWidgetFactory.getRecorderWidget(DataBinder.STYLE_FLOAT, context, dataBinder);
        floatRecorderWidget.setStatus(RecorderWidget.STATUS_IDLE);
        setTouchListener(floatRecorderWidget.recorderView, floatRecorderWidget);
        return floatRecorderWidget;
    }

    private static void setTouchListener(final View recordButton, final RecorderWidget recorderWidget) {
        final boolean[] isCanceled = {false};
        final float[] downY = {0};
        recordButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //开始录音
                        recorderWidget.setStatus(RecorderWidget.STATUS_START);
                        downY[0] = motionEvent.getY();
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        float moveY = motionEvent.getY();
                        if (downY[0] - moveY > 100) {
                            recorderWidget.setStatus(RecorderWidget.STATUS_DRAG);
                            isCanceled[0] = true;
                        } else {
                            recorderWidget.setStatus(RecorderWidget.STATUS_RESUME);
                            isCanceled[0] = false;
                        }
                        return true;
                    case MotionEvent.ACTION_UP:
                        //停止录音
                        recordButton.performClick();
                        if (isCanceled[0]) {
                            //取消录音
                            recorderWidget.setStatus(RecorderWidget.STATUS_CANCEL);
                            isCanceled[0] = false;
                        } else {
                            //完成录音
                            recorderWidget.setStatus(RecorderWidget.STATUS_FINISH);
                        }
                        return true;
                }
                return false;
            }
        });
    }

}
