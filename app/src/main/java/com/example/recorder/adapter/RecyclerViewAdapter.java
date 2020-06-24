package com.example.recorder.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recorder.R;
import com.example.recorder.model.Recording;

import java.util.List;

/**
 * The type Recycler view adapter.
 */
public class RecyclerViewAdapter  extends RecyclerView.Adapter<RecyclerViewAdapter.RecordingViewHolder> {
    private Context context;
    private List<Recording> recordings;
    private Recording recording;
    private OnRecordingListener onRecordingListener;

    /**
     * Instantiates a new Recycler view adapter.
     *
     * @param context             the context
     * @param recordings          the recordings
     * @param onRecordingListener the on recording listener
     */
    public RecyclerViewAdapter(Context context, List<Recording> recordings,OnRecordingListener onRecordingListener) {
        this.context = context;
        this.onRecordingListener=onRecordingListener;
        this.recordings = recordings;
    }

    @NonNull
    @Override
    public RecordingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.recyclerviewui,null);
        RecordingViewHolder recordingViewHolder=new RecordingViewHolder(view,onRecordingListener);
        return recordingViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecordingViewHolder holder, int position) {
        recording=recordings.get(position);
        holder.textView1.setText(recording.getName());
        holder.textView2.setText(recording.getAdded());
    }

    @Override
    public int getItemCount() {
        return recordings.size();
    }

    /**
     * The type Recording view holder.
     */
    class RecordingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        /**
         * The Text view 1.
         */
        TextView textView1, /**
         * The Text view 2.
         */
        textView2;
        /**
         * The On recording listener.
         */
        OnRecordingListener onRecordingListener;

        /**
         * Instantiates a new Recording view holder.
         *
         * @param itemView            the item view
         * @param onRecordingListener the on recording listener
         */
        public RecordingViewHolder(@NonNull View itemView,OnRecordingListener onRecordingListener) {
            super(itemView);
            this.onRecordingListener=onRecordingListener;
            textView1= itemView.findViewById(R.id.filename);
            textView2=itemView.findViewById(R.id.datetime);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onRecordingListener.onRecordingListener(getAdapterPosition());
        }
    }

    /**
     * The interface On recording listener.
     */
    public interface OnRecordingListener{
        /**
         * On recording listener.
         *
         * @param position the position
         */
        void onRecordingListener(int position);
    }
}
