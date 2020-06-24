package com.example.recorder.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.recorder.R;
import com.example.recorder.model.Recording;

import java.io.File;
import java.io.IOException;
import java.util.DuplicateFormatFlagsException;
import java.util.zip.Inflater;

/**
 * The type My custom dialog.
 */
public class MyCustomDialog extends DialogFragment {

    /**
     * The Filename.
     */
    private TextView filename;
    /**
     * The Seek bar.
     */
    private SeekBar seekBar;
    /**
     * The Cancel.
     */
    private Button cancel;
    /**
     * The Recording.
     */
    private Recording recording;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    /**
     * The File.
     */
    private File file;

    /**
     * Instantiates a new My custom dialog.
     *
     * @param recording the recording
     */
    public MyCustomDialog(Recording recording) {
        this.recording = recording;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());

        View view = View.inflate(getContext(),R.layout.dialog_layout, null);
        cancel = view.findViewById(R.id.button);
        seekBar = view.findViewById(R.id.seekBar);
        filename = view.findViewById(R.id.title);
        dialogFunction();
        builder.setView(view);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        return builder.create();
    }

    /**
     * The Media player.
     */
    private MediaPlayer mediaPlayer;

    /**
     * Dialog function.
     */
    public void dialogFunction() {
        filename.setText(recording.getName());
        file = new File(Environment.getExternalStorageDirectory() + "/MySoundRec/" + recording.getName());
        Log.d("MyTag", "dialogFunction: "+file);
        Log.d("MyTag", "dialogFunction: above setSource");
        mediaPlayer=MediaPlayer.create(getContext(),Uri.parse(String.valueOf(file)));
        Log.d("MyTag", "dialogFunction: after setSource ");


        seekBar.setMax(mediaPlayer.getDuration());
        Log.d("MyTag", "dialogFunction: before Prepare");
        try {
            mediaPlayer.prepare();
            Log.d("MyTag", "dialogFunction: in Prepare");
        }
        catch (IOException e){

        }
        catch (IllegalStateException e){

        }
        try{
            mediaPlayer.start();
            Log.d("MyTag", "dialogFunction: in start");
        }
        catch (IllegalStateException e){

        }
        seekProgress();
    }

    /**
     * The Progress thread.
     */
    private Thread progressThread;

    /**
     * Seek progress.
     */
    public void seekProgress(){
            progressThread=new Thread() {
            @Override
            public void run() {
                int currentposition = 0;
                int totalduration = mediaPlayer.getDuration();
                seekBar.setMax(totalduration);
                while (currentposition < totalduration) {
                    if(whilebreaker==true){
                        break;
                    }
                    try {
                        sleep(500);
                        Log.d("MyTag", String.valueOf(currentposition));
                        try{
                            currentposition = mediaPlayer.getCurrentPosition();
                        }
                        catch (IllegalStateException e){

                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    final int finalCurrentposition = currentposition;
                     mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            seekBar.setProgress(finalCurrentposition);
                        }
                    });
                }
            }
        };
        progressThread.start();

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(mediaPlayer != null && fromUser){
                    mediaPlayer.seekTo(progress);
                }
            }
        });
    }

    /**
     * The Whilebreaker.
     */
    private boolean whilebreaker=false;

    @Override
    public void onDestroy() {
        Log.d("MyTag", "onDestroy: ");
        whilebreaker=true;
        mediaPlayer.stop();
        mediaPlayer.release();

        try {
            mediaPlayer.reset();
        }
        catch (IllegalStateException e){

        }
        super.onDestroy();
    }
}