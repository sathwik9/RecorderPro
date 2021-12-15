package ece.wisc.recorder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void freePlayButton(View view) {
        // go into free play mode
        Intent intent = new Intent(this, FreePlay.class);
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
        startActivity(intent);
    }
}