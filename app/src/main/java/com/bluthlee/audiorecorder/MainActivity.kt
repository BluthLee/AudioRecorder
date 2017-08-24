package com.bluthlee.audiorecorder

import android.os.Bundle
import android.os.Environment
import android.support.v7.app.AppCompatActivity
import com.bluthlee.library.AudioRecorderUtil
import com.bluthlee.library.Binder
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by LC on 2017/8/24.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binder = Binder.Builder()
                .setStyle(Binder.STYLE_DRAG)
                .setAttachedRecorderButton(button)
                .setDirPath(Environment.getExternalStorageDirectory().absolutePath + "/aaaaa")
                .build()
        AudioRecorderUtil.init(binder, this)
    }

}