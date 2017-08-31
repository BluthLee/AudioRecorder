package com.bluthlee.library.ui

import android.content.Context
import android.view.MotionEvent
import android.view.View
import com.bluthlee.library.data.DataBinder
import com.bluthlee.library.ui.recorderWidget.DragRecorderWidget
import com.bluthlee.library.ui.recorderWidget.FloatRecorderWidget
import com.bluthlee.library.ui.recorderWidget.RecorderWidget

/**
 * Created by LC on 2017/8/24.
 */
internal class RecorderWidgetManager {

    companion object {

        fun initDragAudioRecorder(recordButton: View, context: Context, dataBinder: DataBinder): RecorderWidget {
            val recorderWidget = RecorderWidgetFactory.getRecorderWidget(DataBinder.STYLE_DRAG, context, dataBinder) as DragRecorderWidget
            setTouchListener(recordButton, recorderWidget)
            return recorderWidget
        }

        fun initFloatAudioRecorder(context: Context, dataBinder: DataBinder): RecorderWidget {
//            var downX = 0F
//            var downY = 0F
            val recorderWidget: FloatRecorderWidget = RecorderWidgetFactory.getRecorderWidget(DataBinder.STYLE_FLOAT, context, dataBinder) as FloatRecorderWidget
            recorderWidget.setStatus(RecorderWidget.STATUS_IDLE)
            setTouchListener(recorderWidget.recorderView, recorderWidget)

//            recorderWidget.recorderView.setOnTouchListener({ view, motionEvent ->
//                when (motionEvent.action) {
//                    MotionEvent.ACTION_DOWN -> {
//                        downX = motionEvent.rawX
//                        downY = motionEvent.rawY
//                        return@setOnTouchListener true
//                    }
//                    MotionEvent.ACTION_MOVE -> {
//                        view.animate().translationX(motionEvent.rawX - downX)
//                                .translationY(motionEvent.rawY - downY)
//                                .setDuration(0)
//                                .start()
//                        return@setOnTouchListener true
//                    }
//                }
//                return@setOnTouchListener false
//            })
            return recorderWidget
        }

        private fun setTouchListener(recordButton: View, recorderWidget: RecorderWidget?) {
            var isCanceled = false
            var downY = 0F
            recordButton.setOnTouchListener({ view, motionEvent ->
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        //开始录音
                        recorderWidget?.setStatus(RecorderWidget.STATUS_START)
                        downY = motionEvent.y
                        return@setOnTouchListener true
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
                        return@setOnTouchListener true
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
                        return@setOnTouchListener true
                    }
                }
                false
            })

        }

    }

}