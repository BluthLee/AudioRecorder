package com.bluthlee.library.recorderStyle

import android.app.Dialog
import android.os.SystemClock
import android.widget.Chronometer
import android.widget.TextView
import com.bluthlee.library.AudioRecorder
import com.bluthlee.library.Binder
import com.bluthlee.library.R

/**
 * Created by LC on 2017/8/24.
 */
internal open class RecorderWidget(private val binder: Binder) {

    companion object {
        const val STATUS_START = 0
        const val STATUS_DRAG = 1
        const val STATUS_RESUME = 2
        const val STATUS_FINISH = 3
        const val STATUS_CANCEL = 4
    }

    lateinit var dialog: Dialog
    private lateinit var tvHint: TextView
    lateinit var chronometer: Chronometer

    private val audioRecorder = AudioRecorder()

    fun init(dialog: Dialog) {
        this.dialog = dialog
        tvHint = dialog.findViewById(R.id.tv_dialog_hint)
        chronometer = dialog.findViewById(R.id.chronometer_dialog)
    }


    fun setStatus(status: Int) {
        when (status) {
            STATUS_START -> {
                dialog.show()
                tvHint.setText(R.string.drag_cancel)
                chronometer.base = SystemClock.elapsedRealtime()
                chronometer.start()
                audioRecorder.recordAudio(binder.fileName, binder.dirPath)
            }
            STATUS_DRAG -> {
                tvHint.setText(R.string.let_go_cancel)
            }
            STATUS_RESUME -> {
                tvHint.setText(R.string.drag_cancel)
            }
            STATUS_CANCEL -> {
                audioRecorder.cancel()
                tvHint.setText(R.string.canceled)
                chronometer.stop()
                tvHint.postDelayed({
                    dialog.cancel()
                }, 700)
            }
            STATUS_FINISH -> {
                dialog.cancel()
                audioRecorder.stopRecord()
                binder.onFinishListener?.onFinish(audioRecorder.getFilePath())
            }
        }
    }

}