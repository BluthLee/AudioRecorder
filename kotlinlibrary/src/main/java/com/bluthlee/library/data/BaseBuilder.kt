package com.bluthlee.library.data

import com.bluthlee.library.AudioRecorder

/**
 * Created by LC on 2017/8/28.
 */
open class BaseBuilder {

    protected var onFinishListener: AudioRecorder.OnFinishListener? = null
    protected var fileName: String = ""
    protected var dirPath: String = ""

    open fun setOnFinishListener(onFinishListener: AudioRecorder.OnFinishListener): BaseBuilder {
        this.onFinishListener = onFinishListener
        return this
    }

    open fun setFileName(fileName: String): BaseBuilder {
        this.fileName = fileName
        return this
    }

    open fun setDirPath(dirPath: String): BaseBuilder {
        this.dirPath = dirPath
        return this
    }

}