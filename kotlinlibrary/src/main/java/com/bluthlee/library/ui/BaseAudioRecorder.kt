package com.bluthlee.library.ui

import android.content.Context
import com.bluthlee.library.data.DataBinder
import com.bluthlee.library.ui.recorderWidget.RecorderWidget

/**
 * Created by LC on 2017/8/24.
 */
open class BaseAudioRecorder internal constructor() {

    private var recorderWidget: RecorderWidget? = null

    internal fun initData(dataBinder: DataBinder, context: Context) {
        when (dataBinder.style) {
            DataBinder.STYLE_DRAG -> {
                if (dataBinder.buttonView == null) {
                    throw Exception("need an attached view when use drag style !!")
                } else {
                    recorderWidget = RecorderWidgetManager.initDragAudioRecorder(dataBinder.buttonView!!, context, dataBinder)
                }
            }
            DataBinder.STYLE_FLOAT -> {
                recorderWidget = RecorderWidgetManager.initFloatAudioRecorder(context, dataBinder)
            }
        }
    }

    /**
     * 显示录音器控件
     * */
    fun show() {
        recorderWidget?.show()
    }

    /**
     * 取消显示录音器控件
     * */
    fun cancel() {
        recorderWidget?.cancel()
    }

    fun isShowing(): Boolean {
        if (recorderWidget != null) {
            return recorderWidget!!.isShowing()
        }
        return false
    }

}