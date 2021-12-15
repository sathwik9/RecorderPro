package ece.wisc.recorder;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.LinkedList;

public class Recordings extends AppCompatActivity {

    private final LinkedList<Record> mSongList = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private SongListAdapter mAdapter;
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recordings);

        // Put initial data into the word list.

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
