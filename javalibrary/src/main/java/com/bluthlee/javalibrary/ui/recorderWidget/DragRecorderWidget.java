package com.bluthlee.javalibrary.ui.recorderWidget;

import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;

import com.bluthlee.javalibrary.R;
import com.bluthlee.javalibrary.data.DataBinder;

/**
 * Created by LC on 2017/8/31.
 */

public class DragRecorderWidget extends RecorderWidget {

    public DragRecorderWidget(DataBinder dataBinder) {
        super(dataBinder);
        dialog = new Dialog(dataBinder.buttonView.getContext(), R.style.AudioRecorderDialog);
        View view = LayoutInflater.from(dataBinder.buttonView.getContext()).inflate(R.layout.dialog_drag, null, false);
        dialog.setContentView(view);
        super.init();
    }

}
