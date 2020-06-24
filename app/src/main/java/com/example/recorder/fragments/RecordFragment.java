package com.example.recorder.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recorder.R;
import com.example.recorder.service.RecordingService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.text.SimpleDateFormat;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecordFragment extends Fragment {
    /**
     * The Image button.
     */
    ImageButton imageButton;
    /**
     * The Chronometer.
     */
    Chronometer chronometer;
    /**
     * The Startrecording.
     */
    boolean startrecording=true;
    /**
     * The Stoprecording.
     */
    boolean stoprecording=true;
    /**
     * The Timewhenpaused.
     */
    long timewhenpaused=0;
    /**
     * The File.
     */
    File file;

    /**
     * Instantiates a new Record fragment.
     */
    public RecordFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View recordView=inflater.inflate(R.layout.fragment_record, container, false);
        imageButton=recordView.findViewById(R.id.recordbutton);
        chronometer=recordView.findViewById(R.id.simpleChronometer);
        whenClick();
        return recordView;
    }

    /**
     * When click.
     */
    public void whenClick(){
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRecord(startrecording);
                startrecording=!startrecording;
            }
        });
    }




    private void onRecord(boolean startrecording) {
        Intent intent=new Intent(getActivity(), RecordingService.class);

        if(startrecording){
            imageButton.setImageResource(R.drawable.ic_stop);
            Toast.makeText(getContext(),"Recording Started",Toast.LENGTH_LONG).show();

            File folder= new File(Environment.getExternalStorageDirectory()+"/MySoundRec");

            if(!folder.exists()){
                folder.mkdir();
            }
            chronometer.setBase(SystemClock.elapsedRealtime());
            chronometer.start();
            getActivity().startService(intent);
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
        else{
            Log.d("MyTag", "onRecord: else");
            imageButton.setImageResource(R.drawable.ic_mic);
            chronometer.stop();
            chronometer.setBase(SystemClock.elapsedRealtime());
            timewhenpaused=0;
            getActivity().stopService(intent);
        }


    }


}
