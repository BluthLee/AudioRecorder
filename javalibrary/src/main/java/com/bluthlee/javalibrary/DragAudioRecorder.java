package com.bluthlee.javalibrary;

import android.view.View;

import com.bluthlee.javalibrary.data.BaseBuilder;
import com.bluthlee.javalibrary.data.DataBinder;

/**
 * Created by LC on 2017/8/31.
 */

public class DragAudioRecorder extends AudioRecorder {

    public static class Builder extends BaseBuilder {
        private Builder() {
        }

        private View recorderButton;

        /**
         * 需要指定一个录音按键用作触摸时启动录音
         */
        public static Builder setAttachedRecorderButton(View recorderButton) {
            Builder builder = new Builder();
            builder.recorderButton = recorderButton;
            return builder;
        }

        @Override
        public Builder setOnFinishListener(AudioRecorder.OnFinishListener onFinishListener) {
            super.setOnFinishListener(onFinishListener);
            return this;
        }

        @Override
        public Builder setFileName(String fileName) {
            super.setFileName(fileName);
            return this;
        }

        @Override
        public Builder setDirPath(String dirPath) {
            super.setDirPath(dirPath);
            return this;
        }

        public DragAudioRecorder build() {
            DragAudioRecorder dragAudioRecorder = new DragAudioRecorder();
            dragAudioRecorder.setStyle(DataBinder.STYLE_DRAG);
            dragAudioRecorder.setAttachedRecorderButton(recorderButton);
            dragAudioRecorder.setListener(onFinishListener);
            dragAudioRecorder.setFileName(fileName);
            dragAudioRecorder.setDirPath(dirPath);
            try {
                dragAudioRecorder.init(recorderButton.getContext());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return dragAudioRecorder;
        }

    }


}
