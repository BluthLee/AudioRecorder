package com.bluthlee.javalibrary.ui.recorderWidget;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.bluthlee.javalibrary.R;
import com.bluthlee.javalibrary.data.DataBinder;

/**
 * Created by LC on 2017/8/31.
 */

public class FloatRecorderWidget extends RecorderWidget {

    public View recorderView;

    public FloatRecorderWidget(Context context, DataBinder dataBinder) {
        super(dataBinder);
        dialog = new Dialog(context, R.style.FloatDialog);
        dialog.setCanceledOnTouchOutside(false);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_float, null, false);
        recorderView = view.findViewById(R.id.iv_recorder);
        view.findViewById(R.id.iv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setStatus(STATUS_CLOSE);
            }
        });
        dialog.setContentView(view);
        super.init();
    }

    @Override
    public void show() {
        super.show();
        dialog.show();
        setStatus(STATUS_IDLE);
    }

    @Override
    public void cancel() {
        super.cancel();
        dialog.cancel();
    }
}
