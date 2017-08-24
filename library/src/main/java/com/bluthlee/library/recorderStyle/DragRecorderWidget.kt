package com.bluthlee.library.recorderStyle

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import com.bluthlee.library.Binder
import com.bluthlee.library.R

/**
 * Created by LC on 2017/8/24.
 */
internal class DragRecorderWidget(context: Context, binder: Binder) : RecorderWidget(binder) {

    init {
        val dialog = Dialog(context, R.style.AudioRecorderDialog)
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_audio_recorder, null, false)
        dialog.setContentView(view)
        super.init(dialog)
    }

}