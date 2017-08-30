package com.bluthlee.library

import android.view.View
import com.bluthlee.library.data.BaseBuilder
import com.bluthlee.library.data.DataBinder

/**
 * Created by LC on 2017/8/28.
 */
class DragAudioRecorder private constructor() : AudioRecorder() {

    class Builder private constructor() : BaseBuilder() {

        private lateinit var recorderButton: View

        companion object {
            /**
             * 需要指定一个录音按键用作触摸时启动录音
             * */
            fun setAttachedRecorderButton(recorderButton: View): Builder {
                val builder = Builder()
                builder.recorderButton = recorderButton
                return builder
            }
        }

        override fun setOnFinishListener(onFinishListener: OnFinishListener): Builder {
            super.setOnFinishListener(onFinishListener)
            return this
        }

        override fun setFileName(fileName: String): Builder {
            super.setFileName(fileName)
            return this
        }

        override fun setDirPath(dirPath: String): Builder {
            super.setDirPath(dirPath)
            return this
        }

        fun build(): DragAudioRecorder {
            val dragAudioRecorder = DragAudioRecorder()
            dragAudioRecorder.setStyle(DataBinder.STYLE_DRAG)
            dragAudioRecorder.setAttachedRecorderButton(recorderButton)
            dragAudioRecorder.setListener(onFinishListener)
            dragAudioRecorder.setFileName(fileName)
            dragAudioRecorder.setDirPath(dirPath)
            dragAudioRecorder.init(recorderButton.context)
            return dragAudioRecorder
        }

    }

}