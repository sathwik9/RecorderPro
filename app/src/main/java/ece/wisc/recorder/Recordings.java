package ece.wisc.recorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;

public class Recordings extends AppCompatActivity {

    private ArrayList<Record> mSongList;
    private RecyclerView mRecyclerView;
    private SongListAdapter mAdapter;
    private MediaPlayer mp;

    class SongListAdapter extends
            RecyclerView.Adapter<SongListAdapter.SongViewHolder> {
        private final ArrayList<Record> mSongList;
        private LayoutInflater mInflater;
        public SongListAdapter(Context context,
                               ArrayList<Record> songList) {
            mInflater = LayoutInflater.from(context);
            this.mSongList = songList;
        }
        class SongViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            public final TextView SongItemView;
            final SongListAdapter mAdapter;
            public SongViewHolder(View itemView, SongListAdapter adapter) {
                super(itemView);
                SongItemView = itemView.findViewById(R.id.song);
                this.mAdapter = adapter;
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                // Get the position of the item that was clicked.
                int mPosition = getLayoutPosition();
// Use that to access the affected item in mSongList.
                Record element = mSongList.get(mPosition);
// Change the Song in the mSongList.
                mSongList.set(mPosition, element);
                playSong(element);
// Notify the adapter, that the data has changed so it can
// update the RecyclerView to display the data.
                mAdapter.notifyDataSetChanged();
            }
        }
        @Override
        public SongViewHolder onCreateViewHolder(ViewGroup parent,
                                                 int viewType) {
            View mItemView = mInflater.inflate(R.layout.songlist_item,
                    parent, false);
            return new SongViewHolder(mItemView, this);
        }

        @Override
        public void onBindViewHolder(SongViewHolder holder, int position) {
            Record mCurrent = mSongList.get(position);
            holder.SongItemView.setText(mCurrent.getTitle());
        }

        @Override
        public int getItemCount() {
            return mSongList.size();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recordings);
        Bundle extras = getIntent().getBundleExtra("songs");
        if (extras != null) {
            mSongList = (ArrayList<Record>) extras.getSerializable("ARRAYLIST");
        }
        else {
            mSongList = new ArrayList<Record>();
        }

        // Get a handle to the RecyclerView.
        mRecyclerView = findViewById(R.id.recyclerview);
// Create an adapter and supply the data to be displayed.
        mAdapter = new SongListAdapter(this, mSongList);
// Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
// Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mp = MediaPlayer.create(this, R.raw.c1);  //default note to avoid null pointers
                                                        //with later code checking mp
    }


    public void playSong(Record recordToPlay){
        ArrayList<Note> songToPlay = recordToPlay.getSong();
        long song_startTime = recordToPlay.getStartTime();
        long this_startTime = System.currentTimeMillis();
        for(int i = 0; i < songToPlay.size(); i++){

            while(true){
                long this_currTime = System.currentTimeMillis() - this_startTime;
                long note_startTime = songToPlay.get(i).time - song_startTime;
                if(this_currTime >= note_startTime){
                    break;
                }

            }
            if(!mp.isPlaying()) {
                mp.release();
                mp = MediaPlayer.create(this, songToPlay.get(i).getMp3_file());
                mp.start();
            }
        }
    }
}
