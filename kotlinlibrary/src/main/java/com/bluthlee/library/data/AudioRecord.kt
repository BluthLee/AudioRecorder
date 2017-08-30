package com.bluthlee.library.data

import android.media.MediaRecorder
import android.os.Environment
import java.io.File
import java.io.IOException

/**
 * Created by LC on 2017/8/24.
 */
internal class AudioRecord {

    private var isRecording = false
    private var startTime: Long = 0
    private var mediaRecorder: MediaRecorder? = null
    private var file: File? = null

    /**
     * 开始录制音频
     *
     * @param fileName 文件名，不包含文件扩展名
     * @param dirPath  文件夹路径
     * @return 启动录制音频操作成功，则返回开始录制时的时间戳。否则返回0
     */
    fun recordAudio(fileName: String, dirPath: String): Long {
        try {
            var name = fileName
            var path = dirPath
            if (name.isEmpty()) {
                name = System.currentTimeMillis().toString()
            }
            if (path.isEmpty()) {
                path = Environment.getExternalStorageDirectory().absolutePath
            }
            val dir = File(path)
            if (!dir.exists()) {
                if (!dir.mkdirs()) {
                    return 0
                }
            }
            file = File(dir, name + ".aac")
            if (mediaRecorder == null) {
                mediaRecorder = MediaRecorder()
            }
            mediaRecorder!!.setAudioSource(MediaRecorder.AudioSource.MIC)
            mediaRecorder!!.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            mediaRecorder!!.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            mediaRecorder!!.setOutputFile(file!!.absolutePath)
            mediaRecorder!!.prepare()
            mediaRecorder!!.start()
            isRecording = true
            startTime = System.currentTimeMillis()
            return startTime
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return 0
    }

    /**
     * 停止录制音频
     *
     * @return 返回录制的时常，单位ms。操作失败返回0
     */
    fun stopRecord(): Long {
        try {
            if (isRecording) {
                mediaRecorder!!.stop()
                mediaRecorder!!.release()
                mediaRecorder = null
                return System.currentTimeMillis() - startTime
            }
        } catch (e: Exception) {
            assert(mediaRecorder != null)
            mediaRecorder!!.release()
            mediaRecorder = null
            if (file!!.exists()) {
                file!!.delete()
            }
        } finally {
            isRecording = false
        }
        return 0
    }

    /**
     * 取消录音
     * */
    fun cancel() {
        if (isRecording && stopRecord() > 0) {
            if (file != null && file!!.exists()) {
                file!!.delete()
            }
        }
    }

    /**
     * 获取录制音频状态
     *
     * @return 正在录制返回true，否则返回false
     */
    fun isRecording(): Boolean {
        return isRecording
    }

    fun getFilePath(): String {
        if (file != null && file!!.exists()) {
            return file!!.absolutePath
        }
        return ""
    }

}