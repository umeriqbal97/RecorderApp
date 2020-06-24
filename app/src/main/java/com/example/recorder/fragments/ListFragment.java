package com.example.recorder.fragments;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.recorder.R;
import com.example.recorder.adapter.RecyclerViewAdapter;
import com.example.recorder.model.Recording;

import java.io.File;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment implements RecyclerViewAdapter.OnRecordingListener {
    /**
     * The File.
     */
    File file;
    /**
     * The File data.
     */
    List<Recording> fileData=new ArrayList<>();
    /**
     * The Recording.
     */
    Recording recording;
    /**
     * The Recycler view.
     */
    RecyclerView recyclerView;
    /**
     * The Context.
     */
    Context context;

    /**
     * Instantiates a new List fragment.
     */
    public ListFragment() {
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
        View view= inflater.inflate(R.layout.fragment_list, container, false);
        context=getContext();
        recyclerView=view.findViewById(R.id.recordinglist);
        getDataFromFolder();
        putInAdapterView();
        return view;
    }

    /**
     * The Filename.
     */
    String filename, /**
     * The Lastmodifed.
     */
    lastmodifed, /**
     * The Time.
     */
    time;

    /**
     * Gets data from folder.
     */
    public void getDataFromFolder() {
        file = new File(Environment.getExternalStorageDirectory() + "/MySoundRec/");

        if (file.exists()) {
            for (File f : file.listFiles()
            ) {
                if (f.isFile()) {
                    filename = f.getName();
                    Log.d("MyTag", "getDataFromFolder: " + filename);
                    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                    lastmodifed = sdf.format(f.lastModified());
                    Log.d("MyTag", "getDataFromFolder: " + lastmodifed);
                    recording = new Recording(filename, lastmodifed);
                    fileData.add(recording);
                }
            }
        }
    }

    /**
     * The Recycler view adapter.
     */
    RecyclerViewAdapter recyclerViewAdapter;

    /**
     * Put in adapter view.
     */
    public void putInAdapterView(){
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager manager=new LinearLayoutManager(context);
        recyclerView.setLayoutManager(manager);
        recyclerViewAdapter=new RecyclerViewAdapter(context,fileData,this);
        //DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
        //        manager.getOrientation());
        //recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(recyclerViewAdapter);

    }

    @Override
    public void onRecordingListener(int position) {
        Recording recording=fileData.get(position);
        MyCustomDialog dialog=new MyCustomDialog(recording);
        dialog.show(getFragmentManager(),"MyCustomDialog");
    }
}
