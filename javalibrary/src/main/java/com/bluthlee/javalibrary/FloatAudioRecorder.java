package com.bluthlee.javalibrary;

import android.content.Context;

import com.bluthlee.javalibrary.data.BaseBuilder;
import com.bluthlee.javalibrary.data.DataBinder;

/**
 * Created by LC on 2017/8/31.
 */

public class FloatAudioRecorder extends AudioRecorder {

    public static class Builder extends BaseBuilder {
        private Builder() {
        }

        private Context context;

        public static Builder setContext(Context context) {
            Builder builder = new Builder();
            builder.context = context;
            return builder;
        }

        @Override
        public Builder setOnFinishListener(OnFinishListener onFinishListener) {
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

        public FloatAudioRecorder build() {
            FloatAudioRecorder floatAudioRecorder = new FloatAudioRecorder();
            floatAudioRecorder.setStyle(DataBinder.STYLE_FLOAT);
            floatAudioRecorder.setListener(onFinishListener);
            floatAudioRecorder.setFileName(fileName);
            floatAudioRecorder.setDirPath(dirPath);
            try {
                floatAudioRecorder.init(context);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return floatAudioRecorder;
        }

    }

}
