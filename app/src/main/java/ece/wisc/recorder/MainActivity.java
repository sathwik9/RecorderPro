package ece.wisc.recorder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.Activity;
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
    private int requestCodeChild;

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

            try {
                e.add_note(R.raw.a1);
                Thread.sleep(1000);
                e.add_note(R.raw.b1);
                Thread.sleep(1000);
                e.add_note(R.raw.c1);
                Thread.sleep(1000);
                e.add_note(R.raw.d1);
                Thread.sleep(1000);
                e.add_note(R.raw.e1);
                Thread.sleep(1000);
                e.add_note(R.raw.f1);
                Thread.sleep(1000);
                e.add_note(R.raw.g1);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }

            e.setTitle("Example0");
            recorded_songs.add(e);
            Record e1 = new Record();
            e1.setTitle("Example1");
            recorded_songs.add(e1);
            Record e2 = new Record();
            e2.setTitle("Example2");
            recorded_songs.add(e2);
            Record e3 = new Record();
            e3.setTitle("Example3");
            recorded_songs.add(e3);

        }


        //Toast.makeText(MainActivity.this, recorded_songs.toString(),
        //        Toast.LENGTH_LONG).show();
    }

    public void freePlayButton(View view) {
        // go into free play mode
        Intent intent = new Intent(this, FreePlay.class);
        Bundle arg = new Bundle();
        arg.putSerializable("ARRAYLIST", (Serializable) recorded_songs);
        intent.putExtra("songs", recorded_songs);
        //change the startActivity call so that a result will be returned when a song is recorded
        //1 is the request code returned when the activity is properly finished
        startActivityForResult(intent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (1) : {
                if (resultCode == FreePlay.RESULT_OK) {
                    Bundle extras = data.getBundleExtra("songs");
                    if (extras != null) {
                        ArrayList<Record> newSongs = (ArrayList<Record>) extras.getSerializable("ARRAYLIST");
                        for(int s = 0; s < newSongs.size(); s ++){
                            recorded_songs.add(newSongs.get(s));
                        }
                    }
                    else {
                        recorded_songs = new ArrayList<Record>();
                        Record e = new Record();
                        // e.add_note(R.id.);
                        e.setTitle("p");
                        recorded_songs.add(e);
                    }
                }
                break;
            }
        }
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