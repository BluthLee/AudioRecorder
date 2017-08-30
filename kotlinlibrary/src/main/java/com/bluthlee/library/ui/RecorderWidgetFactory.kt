package com.bluthlee.library.ui

import android.content.Context
import com.bluthlee.library.data.DataBinder
import com.bluthlee.library.ui.recorderWidget.DragRecorderWidget
import com.bluthlee.library.ui.recorderWidget.FloatRecorderWidget
import com.bluthlee.library.ui.recorderWidget.RecorderWidget

/**
 * Created by LC on 2017/8/24.
 */
internal class RecorderWidgetFactory {

    companion object {

        fun getRecorderWidget(style: Int, context: Context, dataBinder: DataBinder): RecorderWidget? {
            when (style) {
                DataBinder.STYLE_DRAG -> {
                    return DragRecorderWidget(dataBinder)
                }
                DataBinder.STYLE_FLOAT -> {
                    return FloatRecorderWidget(context, dataBinder)
                }
            }
            return null
        }

    }

}