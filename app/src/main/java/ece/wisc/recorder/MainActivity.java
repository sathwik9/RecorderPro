package ece.wisc.recorder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Record> recorded_songs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        Bundle extras = intent.getBundleExtra("songs");
        if (extras != null) {
            recorded_songs = (ArrayList<Record>) extras.getSerializable("ARRAYLIST");
        }
        else {
            recorded_songs = new ArrayList<Record>();
            Record e = new Record();
           // e.add_note(R.id.);
            e.setTitle("p");
            recorded_songs.add(e);
        }


        Toast.makeText(MainActivity.this, recorded_songs.toString(),
                Toast.LENGTH_LONG).show();
    }

    public void freePlayButton(View view) {
        // go into free play mode
        Intent intent = new Intent(this, FreePlay.class);
        Bundle arg = new Bundle();
        arg.putSerializable("ARRAYLIST", (Serializable) recorded_songs);
        intent.putExtra("songs", recorded_songs);
        startActivity(intent);
    }

    public void learnButton(View view) {
        // go into learn to play mode
        Intent intent = new Intent(this, LearnToPlay.class);
        startActivity(intent);
    }

    public void recordingsButton(View view) {
        // go into playback of recordings
        Intent intent = new Intent(this, Recordings.class);
        Bundle arg = new Bundle();
        arg.putSerializable("ARRAYLIST", (Serializable) recorded_songs);
        intent.putExtra("songs", arg);

        startActivity(intent);

    }
}