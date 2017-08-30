package com.bluthlee.library.ui.recorderWidget

import android.app.Dialog
import android.view.LayoutInflater
import com.bluthlee.library.data.DataBinder
import com.bluthlee.library.R

/**
 * Created by LC on 2017/8/24.
 */
internal class DragRecorderWidget(dataBinder: DataBinder) : RecorderWidget(dataBinder) {

    init {
        dialog = Dialog(dataBinder.buttonView?.context, R.style.AudioRecorderDialog)
        val view = LayoutInflater.from(dataBinder.buttonView?.context).inflate(R.layout.dialog_drag, null, false)
        dialog.setContentView(view)
        super.init()
    }

}