package com.bluthlee.library

import android.content.Context
import android.view.MotionEvent
import android.view.View
import com.bluthlee.library.recorderStyle.RecorderWidget

/**
 * Created by LC on 2017/8/24.
 */
internal class RecorderStyleManager {

    companion object {

        const val RECORDER_STYLE_DRAG = 0
        const val RECORDER_STYLE_TWO = 1
        const val RECORDER_STYLE_THREE = 2

        fun initDragAudioRecorder(recordButton: View, context: Context, binder: Binder) {

            var isCanceled = false
            val recorderWidget = RecorderWidgetFactory.getDragRecorderWidget(RecorderStyleManager.RECORDER_STYLE_DRAG, context, binder)
            var downY = 0F

            recordButton.setOnTouchListener(View.OnTouchListener { view, motionEvent ->
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        //开始录音
                        recorderWidget?.setStatus(RecorderWidget.STATUS_START)
                        downY = motionEvent.y
                        return@OnTouchListener true
                    }
                    MotionEvent.ACTION_MOVE -> {
                        val moveY = motionEvent.y
                        if (downY - moveY > 100) {
                            recorderWidget?.setStatus(RecorderWidget.STATUS_DRAG)
                            isCanceled = true
                        } else {
                            recorderWidget?.setStatus(RecorderWidget.STATUS_RESUME)
                            isCanceled = false
                        }
                        return@OnTouchListener true
                    }
                    MotionEvent.ACTION_UP -> {
                        //停止录音
                        recordButton.performClick()
                        if (isCanceled) {
                            //取消录音
                            recorderWidget?.setStatus(RecorderWidget.STATUS_CANCEL)
                            isCanceled = false
                        } else {
                            //完成录音
                            recorderWidget?.setStatus(RecorderWidget.STATUS_FINISH)
                        }
                        return@OnTouchListener true
                    }
                }
                false
            })
        }

    }

}