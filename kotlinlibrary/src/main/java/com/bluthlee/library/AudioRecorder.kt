package com.bluthlee.library

import android.content.Context
import android.view.View
import com.bluthlee.library.ui.BaseAudioRecorder
import com.bluthlee.library.data.DataBinder

/**
 * Created by LC on 2017/8/28.
 */
open class AudioRecorder internal constructor() : BaseAudioRecorder() {

    interface OnFinishListener {
        fun onFinish(filePath: String)
    }

    private val dataBinder: DataBinder = DataBinder()

    protected fun init(context: Context): BaseAudioRecorder {
        super.initData(dataBinder, context)
        return this
    }

    /**
     * 设置样式
     * */
    protected fun setStyle(style: Int): BaseAudioRecorder {
        dataBinder.style = style
        return this
    }

    /**
     * 设置录音完成的监听器
     * */
    protected fun setListener(onFinishListener: OnFinishListener?): BaseAudioRecorder {
        dataBinder.onFinishListener = onFinishListener
        return this
    }

    /**
     * 当样式选择第一种时，需要指定一个录音按键用作触摸时启动录音
     * */
    protected fun setAttachedRecorderButton(view: View): BaseAudioRecorder {
        dataBinder.buttonView = view
        return this
    }

    /**
     * 设置文件名称，不包含文件扩展名
     * */
    protected fun setFileName(fileName: String): BaseAudioRecorder {
        dataBinder.fileName = fileName
        return this
    }

    /**
     * 设置文件存放文件夹的绝对路径
     * */
    protected fun setDirPath(dirPath: String): BaseAudioRecorder {
        dataBinder.dirPath = dirPath
        return this
    }

}
