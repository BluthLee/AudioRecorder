package com.bluthlee.library.data

import android.view.View
import com.bluthlee.library.AudioRecorder

/**
 * Created by LC on 2017/8/24.
 */
internal class DataBinder {

    companion object {
        const val STYLE_DRAG = 0
        const val STYLE_FLOAT = 1
        const val STYLE_THREE = 2
    }

    var onFinishListener: AudioRecorder.OnFinishListener? = null

    var style: Int = STYLE_DRAG

    var buttonView: View? = null

    var fileName = ""

    var dirPath = ""

}