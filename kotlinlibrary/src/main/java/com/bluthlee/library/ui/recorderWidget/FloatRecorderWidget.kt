package com.bluthlee.library.ui.recorderWidget

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.bluthlee.library.data.DataBinder
import com.bluthlee.library.R

/**
 * Created by LC on 2017/8/25.
 */
internal class FloatRecorderWidget(context: Context, dataBinder: DataBinder) : RecorderWidget(dataBinder) {

    var recorderView: View

    init {
        dialog = Dialog(context, R.style.FloatDialog)
        dialog.setCanceledOnTouchOutside(false)
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_float, null, false)
        recorderView = view.findViewById(R.id.iv_recorder)
        view.findViewById<ImageView>(R.id.iv_close).setOnClickListener {
            setStatus(STATUS_CLOSE)
        }
        dialog.setContentView(view)
        super.init()
    }

    override fun show() {
        dialog.show()
        setStatus(STATUS_IDLE)
    }

    override fun cancel() {
        dialog.cancel()
    }

}