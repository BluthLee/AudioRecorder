package com.bluthlee.library

import android.os.Environment
import android.view.View

/**
 * Created by LC on 2017/8/24.
 */
class Binder private constructor() {

    var onFinishListener: OnFinishListener? = null
        private set

    var style: Int = STYLE_DRAG
        private set

    var buttonView: View? = null
        private set

    var fileName = System.currentTimeMillis().toString()

    var dirPath = Environment.getExternalStorageDirectory().absolutePath

    interface OnFinishListener {
        fun onFinish(filePath: String)
    }

    companion object {
        const val STYLE_DRAG = 0
        const val STYLE_TWO = 1
        const val STYLE_THREE = 2
    }

    open class Builder {

        private val binder = Binder()

        /**
         * 设置样式
         * */
        fun setStyle(style: Int): Builder {
            binder.style = style
            return this
        }

        /**
         * 设置录音完成的监听器
         * */
        fun setListener(onFinishListener: OnFinishListener): Builder {
            binder.onFinishListener = onFinishListener
            return this
        }

        /**
         * 当样式选择第一种时，需要指定一个录音按键用作触摸时启动录音
         * */
        fun setAttachedRecorderButton(view: View): Builder {
            binder.buttonView = view
            return this
        }

        fun setFileName(fileName: String): Builder {
            binder.fileName = fileName
            return this
        }

        fun setDirPath(dirPath: String): Builder {
            binder.dirPath = dirPath
            return this
        }

        fun build(): Binder {
            return binder
        }
    }

}