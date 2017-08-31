package com.bluthlee.javalibrary.ui.recorderWidget;

import android.app.Dialog;
import android.os.SystemClock;
import android.widget.Chronometer;
import android.widget.TextView;

import com.bluthlee.javalibrary.R;
import com.bluthlee.javalibrary.data.AudioRecord;
import com.bluthlee.javalibrary.data.DataBinder;

/**
 * Created by LC on 2017/8/31.
 */

public class RecorderWidget {

    public static final int STATUS_IDLE = 0;
    public static final int STATUS_START = 1;
    public static final int STATUS_DRAG = 2;
    public static final int STATUS_RESUME = 3;
    public static final int STATUS_FINISH = 4;
    public static final int STATUS_CANCEL = 5;
    public static final int STATUS_CLOSE = 6;

    public Dialog dialog;
    private TextView tvHint;
    public Chronometer chronometer;

    private AudioRecord audioRecord = new AudioRecord();
    private DataBinder dataBinder;

    protected RecorderWidget(DataBinder dataBinder) {
        this.dataBinder = dataBinder;
    }

    protected void init() {
        tvHint = dialog.findViewById(R.id.tv_dialog_hint);
        chronometer = dialog.findViewById(R.id.chronometer_dialog);
    }

    public void setStatus(int status) {
        switch (status) {
            case STATUS_IDLE:
                tvHint.setText(R.string.hold_to_record);
                chronometer.setBase(SystemClock.elapsedRealtime());
                break;
            case STATUS_START:
                dialog.show();
                tvHint.setText(R.string.drag_cancel);
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
                audioRecord.recordAudio(dataBinder.fileName, dataBinder.dirPath);
                break;
            case STATUS_DRAG:
                tvHint.setText(R.string.let_go_cancel);
                break;
            case STATUS_RESUME:
                tvHint.setText(R.string.drag_cancel);
                break;
            case STATUS_CANCEL:
                audioRecord.cancel();
                tvHint.setText(R.string.canceled);
                chronometer.stop();
                tvHint.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialog.cancel();
                    }
                }, 700);
                break;
            case STATUS_FINISH:
                dialog.cancel();
                chronometer.stop();
                audioRecord.stopRecord();
                if (dataBinder.onFinishListener != null) {
                    dataBinder.onFinishListener.onFinish(audioRecord.getFilePath());
                }
                break;
            case STATUS_CLOSE:
                audioRecord.cancel();
                dialog.cancel();
                chronometer.stop();
                break;
        }
    }

    /**
     * 显示录音布局
     */
    public void show() {

    }

    /**
     * 取消显示录音布局
     */
    public void cancel() {

    }

    public boolean isShowing() {
        return dialog.isShowing();
    }

}
