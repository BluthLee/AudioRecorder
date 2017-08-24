package com.bluthlee.library

import android.content.Context

/**
 * Created by LC on 2017/8/24.
 */
class AudioRecorderUtil {

    companion object {
        fun init(binder: Binder, context: Context) {
            when (binder.style) {
                RecorderStyleManager.RECORDER_STYLE_DRAG -> {
                    if (binder.buttonView == null) {
                        throw Exception("need an attached view when use drag style !!")
                    } else {
                        RecorderStyleManager.initDragAudioRecorder(binder.buttonView!!, context, binder)
                    }
                }
            }
        }
    }

}