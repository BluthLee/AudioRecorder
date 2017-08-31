package com.bluthlee.javalibrary.ui;

import android.content.Context;

import com.bluthlee.javalibrary.data.DataBinder;
import com.bluthlee.javalibrary.ui.recorderWidget.DragRecorderWidget;
import com.bluthlee.javalibrary.ui.recorderWidget.FloatRecorderWidget;
import com.bluthlee.javalibrary.ui.recorderWidget.RecorderWidget;

/**
 * Created by LC on 2017/8/31.
 */

public class RecorderWidgetFactory {

    public static RecorderWidget getRecorderWidget(int style, Context context, DataBinder dataBinder) {
        switch (style) {
            case DataBinder.STYLE_DRAG:
                return new DragRecorderWidget(dataBinder);
            case DataBinder.STYLE_FLOAT:
                return new FloatRecorderWidget(context, dataBinder);
        }
        return null;
    }

}
