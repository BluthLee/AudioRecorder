package com.bluthlee.library

import android.content.Context
import com.bluthlee.library.data.BaseBuilder
import com.bluthlee.library.data.DataBinder

/**
 * Created by LC on 2017/8/28.
 */
class FloatAudioRecorder : AudioRecorder() {

    class Builder private constructor() : BaseBuilder() {

        private lateinit var context: Context

        companion object {
            /**
             * 需要指定一个录音按键用作触摸时启动录音
             * */
            fun setContext(context: Context): Builder {
                val builder = Builder()
                builder.context = context
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


        fun build(): FloatAudioRecorder {
            val floatAudioRecorder = FloatAudioRecorder()
            floatAudioRecorder.setStyle(DataBinder.STYLE_FLOAT)
            floatAudioRecorder.setListener(onFinishListener)
            floatAudioRecorder.setFileName(fileName)
            floatAudioRecorder.setDirPath(dirPath)
            floatAudioRecorder.init(context)
            return floatAudioRecorder
        }

    }

}