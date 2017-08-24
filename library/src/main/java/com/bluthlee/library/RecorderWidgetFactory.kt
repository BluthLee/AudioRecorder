package com.bluthlee.library

import android.content.Context
import com.bluthlee.library.recorderStyle.DragRecorderWidget
import com.bluthlee.library.recorderStyle.RecorderWidget

/**
 * Created by LC on 2017/8/24.
 */
internal class RecorderWidgetFactory {

    companion object {

        fun getDragRecorderWidget(style: Int, context: Context, binder: Binder): RecorderWidget? {
            when (style) {
                RecorderStyleManager.RECORDER_STYLE_DRAG -> {
                    return DragRecorderWidget(context, binder)
                }
            }
            return null
        }
    }

}