package com.bluthlee.audiorecorder

import android.os.Bundle
import android.os.Environment
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.bluthlee.library.AudioRecorder
import com.bluthlee.library.DragAudioRecorder
import com.bluthlee.library.FloatAudioRecorder
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by LC on 2017/8/24.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var floatAudioRecorder: FloatAudioRecorder

    private val dirPath = Environment.getExternalStorageDirectory().absolutePath + "/audio_recorder"
    private val dragDirPath = dirPath + "/drag"
    private val floatDirPath = dirPath + "/float"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //使用DragAudioRecorder
        DragAudioRecorder.Builder
                //required
                .setAttachedRecorderButton(button1)

                //optional
                .setDirPath(dragDirPath)
                .setFileName(System.currentTimeMillis().toString() + "_drag")
                .setOnFinishListener(object : AudioRecorder.OnFinishListener {
                    override fun onFinish(filePath: String) {
                        Toast.makeText(this@MainActivity, filePath, Toast.LENGTH_SHORT).show()
                    }
                })

                //build and initialize
                .build()

        //使用FloatAudioRecorder
        floatAudioRecorder = FloatAudioRecorder.Builder
                //required
                .setContext(this)

                //optional
                .setDirPath(floatDirPath)
                .setFileName(System.currentTimeMillis().toString() + "_float")
                .setOnFinishListener(object : AudioRecorder.OnFinishListener {
                    override fun onFinish(filePath: String) {
                        Toast.makeText(this@MainActivity, filePath, Toast.LENGTH_SHORT).show()
                    }
                })

                //build and initialize
                .build()
    }

    fun click(view: View) {
        if (!floatAudioRecorder.isShowing()) {
            floatAudioRecorder.show()
        } else {
            floatAudioRecorder.cancel()
        }
    }

}