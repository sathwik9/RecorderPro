package ece.wisc.recorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.Arrays;

public class CompletedSong extends AppCompatActivity {

    private String[] song;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_song);

        // get intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            song = extras.getStringArray("song");
        }
    }

    public void home(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void playAgain(View view) {
        Intent intent = new Intent(this, PlayAlong.class);

        intent.putExtra("song", song);
        startActivity(intent);
    }
}