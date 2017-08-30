# AudioRecorder

该库是一个使用Kotlin开发的录音模块，用于Android平台。目前包含两种样式：DragAudioRecorder，FloatAudioRecorder.

![DragAudioRecorder](https://github.com/BluthLee/AudioRecorder/raw/master/images/drag_audio_recorder.png "DragAudioRecorder")
![FloatAudioRecorder](https://github.com/BluthLee/AudioRecorder/blob/master/images/float_audio_recorder.png "FloatAudioRecorder")


## Compatibility

最低API版本要求 API 15 (Android 4.0.3)


## Download

在此下载最新的aar格式打包文件![audio-recorder-kotlin-1.0.0.aar](https://github.com/BluthLee/AudioRecorder/blob/master/kotlinlibrary/audio-recorder-kotlin-1.0.0.aar "audio-recorder-kotlin-1.0.0.aar")

另外需要额外引用一个Kotlin的库，在你的module的build.gradle文件中添加如下Kotlin库
```groovy
dependencies {
    implementation "org.jetbrains.kotlin:kotlin-stdlib-jre7:1.1.4-2+"
}
```


## Usage
使用相应AudioRecorder的子类Builder，如欲使用DragAudioRecorder，则需使用DragAudioRecorder.Builder
并对其进行配置，最后调用build方法构造一个AudioRecorder对象，然后可以对其进行操作，如显示和隐藏。


### Builder的一般方法
setDirPath(dirPath:String): 设置存放录音文件的绝对路径

setFileName(fileName:String): 设置录音文件名（不含文件扩展名）

setOnFinishListener(listener:AudioRecorder.OnFinishListener): 设置录音完成的监听事件，在录音结束后会调用

AudioRecorder.OnFinishListener.onFinish(filePath:String): filePath为录音文件的绝对路径，如果录音取消或者失败则为空字符串""


### DragAudioRecorder专有方法
DragAudioRecorder.Builder的方法：

setAttachedRecorderButton(view:View): 设置需要依附的view，例如使用一个button，会响应其触摸事件，按住button会弹出提示并开始录音


### FloatAudioRecorder专有方法
FloatAudioRecorder.Builder的方法：

setContext(context:Context): 设置context


FloatAudioRecorder的方法：

show(): 显示录音控件

cancel(): 取消显示录音控件

isShowing(): 获取当前录音控件的显示状态


### 使用示例
```kotlin
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
val floatAudioRecorder = FloatAudioRecorder.Builder
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
        
//get status
val isShowing=floatAudioRecorder.isShowing()
        
//show the recorder
floatAudioRecorder.show()        
        
//cancel the recorder
floatAudioRecorder.cancel()
```
