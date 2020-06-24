package com.example.recorder.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recorder.model.Recording;

import java.io.File;
import java.io.IOException;

/**
 * The type Recording service.
 */
public class RecordingService extends Service {

    /**
     * The Media recorder.
     */
    MediaRecorder mediaRecorder;
    /**
     * The Filename.
     */
    String filename;
    /**
     * The File.
     */
    File file;


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startRecording();
        return START_STICKY;
    }

    private void startRecording() {
        Long tsmillis= System.currentTimeMillis()/1000;
        String ts=tsmillis.toString();

        filename="audio_"+ts;

        file=new File(Environment.getExternalStorageDirectory()+"/MySoundRec/"+filename+".mp3");
        Log.d("MyTag", "startRecording: "+file.getAbsolutePath());
        String fullpath=file.getAbsolutePath();
        mediaRecorder=new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mediaRecorder.setOutputFile(fullpath);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        mediaRecorder.setAudioChannels(1);
        try {
            mediaRecorder.prepare();
            mediaRecorder.start();
        }
        catch (IllegalStateException e){

        }
        catch (IOException e){

        }
    }

    /**
     * Stop recording.
     */
    public void stopRecording(){
        try {
            mediaRecorder.stop();
        }
        catch (IllegalStateException e){

        }
        mediaRecorder.release();
        Toast.makeText(getApplicationContext(),"Recording Saved"+file.getAbsolutePath(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy() {
        if(mediaRecorder!=null){
            stopRecording();
        }
        super.onDestroy();
    }



}
