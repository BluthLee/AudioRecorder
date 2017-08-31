package com.bluthlee.javaapp;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bluthlee.javalibrary.AudioRecorder;
import com.bluthlee.javalibrary.DragAudioRecorder;
import com.bluthlee.javalibrary.FloatAudioRecorder;
import com.bluthlee.javalibrary.ui.recorderWidget.FloatRecorderWidget;

public class MainActivity extends AppCompatActivity {

    private Button button1;
    private Button button2;
    private FloatAudioRecorder floatAudioRecorder;

    private String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/audio_recorder";
    private String dragDirPath = dirPath + "/drag";
    private String floatDirPath = dirPath + "/float";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);

        //使用DragAudioRecorder
        DragAudioRecorder.Builder
                //required
                .setAttachedRecorderButton(button1)

                //optional
                .setDirPath(dragDirPath)
                .setFileName(System.currentTimeMillis() + "_drag")
                .setOnFinishListener(new AudioRecorder.OnFinishListener() {
                    @Override
                    public void onFinish(String filePath) {
                        Toast.makeText(MainActivity.this, filePath, Toast.LENGTH_SHORT).show();
                    }
                })

                //build and initialize
                .build();

        //使用FloatAudioRecorder
        floatAudioRecorder = FloatAudioRecorder.Builder
                //required
                .setContext(this)

                //optional
                .setDirPath(floatDirPath)
                .setFileName(System.currentTimeMillis() + "")
                .setOnFinishListener(new AudioRecorder.OnFinishListener() {
                    @Override
                    public void onFinish(String filePath) {
                        Toast.makeText(MainActivity.this, filePath, Toast.LENGTH_SHORT).show();
                    }
                })

                //build and initialize
                .build();
    }

    public void click(View view) {
        if (!floatAudioRecorder.isShowing()) {
            floatAudioRecorder.show();
        } else {
            floatAudioRecorder.cancel();
        }
    }

}
