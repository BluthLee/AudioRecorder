package com.bluthlee.library.ui.recorderWidget

import android.app.Dialog
import android.os.SystemClock
import android.widget.Chronometer
import android.widget.TextView
import com.bluthlee.library.data.AudioRecord
import com.bluthlee.library.data.DataBinder
import com.bluthlee.library.R

/**
 * Created by LC on 2017/8/24.
 */
internal open class RecorderWidget(private val dataBinder: DataBinder) {

    companion object {
        const val STATUS_IDLE = 0
        const val STATUS_START = 1
        const val STATUS_DRAG = 2
        const val STATUS_RESUME = 3
        const val STATUS_FINISH = 4
        const val STATUS_CANCEL = 5
        const val STATUS_CLOSE = 6
    }

    lateinit var dialog: Dialog
    private lateinit var tvHint: TextView
    lateinit var chronometer: Chronometer

    private val audioRecorder = AudioRecord()

    fun init() {
        tvHint = dialog.findViewById(R.id.tv_dialog_hint)
        chronometer = dialog.findViewById(R.id.chronometer_dialog)
    }


    fun setStatus(status: Int) {
        when (status) {
            STATUS_IDLE -> {
                tvHint.setText(R.string.hold_to_record)
                chronometer.base = SystemClock.elapsedRealtime()
            }
            STATUS_START -> {
                dialog.show()
                tvHint.setText(R.string.drag_cancel)
                chronometer.base = SystemClock.elapsedRealtime()
                chronometer.start()
                audioRecorder.recordAudio(dataBinder.fileName, dataBinder.dirPath)
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
                chronometer.stop()
                audioRecorder.stopRecord()
                dataBinder.onFinishListener?.onFinish(audioRecorder.getFilePath())
            }
            STATUS_CLOSE -> {
                audioRecorder.cancel()
                dialog.cancel()
                chronometer.stop()
            }
        }
    }

    /**
     * 显示录音布局
     * */
    open fun show() {

    }

    /**
     * 取消显示录音布局
     * */
    open fun cancel() {

    }

    open fun isShowing(): Boolean {
        return dialog.isShowing
    }

}