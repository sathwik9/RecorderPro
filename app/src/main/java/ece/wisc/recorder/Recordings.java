package ece.wisc.recorder;

import androidx.appcompat.app.AppCompatActivity;

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

import java.util.LinkedList;

public class Recordings extends AppCompatActivity {

    private final LinkedList<Record> mSongList = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private SongListAdapter mAdapter;

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
    }

}
