package com.bluthlee.javalibrary.data;

import android.media.MediaRecorder;
import android.os.Environment;

import java.io.File;
import java.io.IOException;

/**
 * Created by LC on 2017/8/31.
 */

public class AudioRecord {

    private boolean isRecording = false;
    private long startTime = 0;
    private MediaRecorder mediaRecorder = null;
    private File file = null;

    /**
     * 开始录制音频
     *
     * @param fileName 文件名，不包含文件扩展名
     * @param dirPath  文件夹路径
     * @return 启动录制音频操作成功，则返回开始录制时的时间戳。否则返回0
     */
    public long recordAudio(String fileName, String dirPath) {
        try {
            String name = fileName;
            String path = dirPath;
            if (name.isEmpty()) {
                name = System.currentTimeMillis() + "";
            }
            if (path.isEmpty()) {
                path = Environment.getExternalStorageDirectory().getAbsolutePath();
            }
            File dir = new File(path);
            if (!dir.exists()) {
                if (!dir.mkdirs()) {
                    return 0;
                }
            }
            file = new File(dir, name + ".aac");
            if (mediaRecorder == null) {
                mediaRecorder = new MediaRecorder();
            }
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
            mediaRecorder.setOutputFile(file.getAbsolutePath());
            mediaRecorder.prepare();
            mediaRecorder.start();
            isRecording = true;
            startTime = System.currentTimeMillis();
            return startTime;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 停止录制音频
     *
     * @return 返回录制的时常，单位ms。操作失败返回0
     */
    public long stopRecord() {
        try {
            if (isRecording) {
                mediaRecorder.stop();
                mediaRecorder.release();
                mediaRecorder = null;
                return System.currentTimeMillis() - startTime;
            }
        } catch (Exception e) {
            if (mediaRecorder != null) {
                mediaRecorder.release();
                mediaRecorder = null;
            }
            if (file != null && file.exists()) {
                file.delete();
            }
        } finally {
            isRecording = false;
        }
        return 0;
    }

    /**
     * 取消录音
     */
    public void cancel() {
        if (isRecording && stopRecord() > 0) {
            if (file != null && file.exists()) {
                file.delete();
            }
        }
    }

    /**
     * 获取录制音频状态
     *
     * @return 正在录制返回true，否则返回false
     */
    public boolean isRecording() {
        return isRecording;
    }


    public String getFilePath() {
        if (file != null && file.exists()) {
            return file.getAbsolutePath();
        }
        return "";
    }
}
