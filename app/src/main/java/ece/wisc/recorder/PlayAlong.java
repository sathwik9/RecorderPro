package ece.wisc.recorder;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.res.ResourcesCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Queue;

public class PlayAlong extends AppCompatActivity {

    private final int MY_PERMISSIONS_RECORD_AUDIO = 1;
    private static final int RECORDER_SAMPLERATE = 8000;
    private static final int RECORDER_CHANNELS = AudioFormat.CHANNEL_IN_MONO;
    private static final int RECORDER_AUDIO_ENCODING = AudioFormat.ENCODING_PCM_16BIT;

    private AudioRecord ar = null;
    private int minSize = 0;

    private boolean isRecording = true;
    private boolean blowDetected = false;

    private Thread recordingThread = null;
    private Thread determiningThread = null;
    private Thread playingThread = null;


    MediaPlayer mp;
    TextView note_being_played;
    ImageView Left1, Left2, Left3, Left4, Left4_2, Right1, Right2, Right3, Right4, Right3_2, Right4_2;
    private int L1_b = R.drawable.left_button;
    private int L2_b = R.drawable.left_button;
    private int L3_b = R.drawable.left_button;
    private int L4_b = R.drawable.left_button;
    private int L4_2_b = R.drawable.left_button;
    private int R1_b = R.drawable.right_button;
    private int R2_b = R.drawable.right_button;
    private int R3_b = R.drawable.right_button;
    private int R3_2_b = R.drawable.right_button;
    private int R4_b = R.drawable.right_button;
    private int R4_2_b = R.drawable.right_button;
    private int notes_played;
    public boolean L1, L2, L3, L4, L4_2, R1, R2, R3, R3_2, R4, R4_2;
    public Integer mp3_file = R.raw.a1;

    private String[] song;
    public String note = "";
    private Queue<String> notes = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_along);

        note_being_played = findViewById(R.id.Note_to_be_played);

        Left1 = findViewById(R.id.Left1);
        Left2 = findViewById(R.id.Left2);
        Left3 = findViewById(R.id.Left3);
        Left4 = findViewById(R.id.Left4);
        Left4_2 = findViewById(R.id.Left4_2);
        Right1 = findViewById(R.id.Right1);
        Right2 = findViewById(R.id.Right2);
        Right3 = findViewById(R.id.Right3);
        Right3_2 = findViewById(R.id.Right3_2);
        Right4 = findViewById(R.id.Right4);
        Right4_2 = findViewById(R.id.Right4_2);

        Left1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                switch (arg1.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        // Left1.setImageResource(R.drawable.pushed_button);
                        L1 = true;
                        break;
                    }
                    case MotionEvent.ACTION_UP:{
                        // Left1.setImageResource(L1_b);
                        L1 = false;
                        break;
                    }
                }
                return true;
            }
        });

        Left2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                switch (arg1.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        // Left2.setImageResource(R.drawable.pushed_button);
                        L2 = true;
                        break;
                    }
                    case MotionEvent.ACTION_UP:{
                        // Left2.setImageResource(L2_b);
                        L2 = false;
                        break;
                    }
                }
                return true;
            }
        });

        Left3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                switch (arg1.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        // Left3.setImageResource(R.drawable.pushed_button);
                        L3 = true;
                        break;
                    }
                    case MotionEvent.ACTION_UP:{
                        // Left3.setImageResource(L3_b);
                        L3 = false;
                        break;
                    }
                }
                return true;
            }
        });

        Left4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                switch (arg1.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        // Left4.setImageResource(R.drawable.pushed_button);
                        L4 = true;
                        break;
                    }
                    case MotionEvent.ACTION_UP:{
                        // Left4.setImageResource(L4_b);
                        L4 = false;
                        break;
                    }
                }
                return true;
            }
        });

        Left4_2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                switch (arg1.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        // Left4_2.setImageResource(R.drawable.pushed_button);
                        L4_2 = true;
                        break;
                    }
                    case MotionEvent.ACTION_UP:{
                        // Left4_2.setImageResource(L4_2_b);
                        L4_2 = false;
                        break;
                    }
                }
                return true;
            }
        });

        Right1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                switch (arg1.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        // Right1.setImageResource(R.drawable.pushed_button);
                        R1 = true;
                        break;
                    }
                    case MotionEvent.ACTION_UP:{
                        // Right1.setImageResource(R1_b);
                        R1 = false;
                        break;
                    }
                }
                return true;
            }
        });

        Right2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                switch (arg1.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        // Right2.setImageResource(R.drawable.pushed_button);
                        R2 = true;
                        break;
                    }
                    case MotionEvent.ACTION_UP:{
                        // Right2.setImageResource(R2_b);
                        R2 = false;
                        break;
                    }
                }
                return true;
            }
        });

        Right3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                switch (arg1.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        // Right3.setImageResource(R.drawable.pushed_button);
                        R3 = true;
                        break;
                    }
                    case MotionEvent.ACTION_UP:{
                        // Right3.setImageResource(R3_b);
                        R3 = false;
                        break;
                    }
                }
                return true;
            }
        });

        Right3_2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                switch (arg1.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        // Right3_2.setImageResource(R.drawable.pushed_button);
                        R3_2 = true;
                        break;
                    }
                    case MotionEvent.ACTION_UP:{
                        // Right3_2.setImageResource(R3_2_b);
                        R3_2 = false;
                        break;
                    }
                }
                return true;
            }
        });

        Right4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                switch (arg1.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        // Right4.setImageResource(R.drawable.pushed_button);
                        R4 = true;
                        break;
                    }
                    case MotionEvent.ACTION_UP:{
                        // Right4.setImageResource(R4_b);
                        R4 = false;
                        break;
                    }
                }
                return true;
            }
        });

        Right4_2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                switch (arg1.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        // Right4_2.setImageResource(R.drawable.pushed_button);
                        R4_2 = true;
                        break;
                    }
                    case MotionEvent.ACTION_UP:{
                        // Right4_2.setImageResource(R4_2_b);
                        R4_2 = false;
                        break;
                    }
                }
                return true;
            }
        });

        // request permission
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO},
                    123);
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

        }
        minSize = AudioRecord.getMinBufferSize(8000, RECORDER_CHANNELS, AudioFormat.ENCODING_PCM_16BIT);
        ar = new AudioRecord(MediaRecorder.AudioSource.MIC, 8000, RECORDER_CHANNELS, AudioFormat.ENCODING_PCM_16BIT, minSize);
        mp = MediaPlayer.create(this, R.raw.a1);   //change to default blank sound!!!!!!!!!!!!!!!!!!!!!!!!
        mp3_file = R.raw.a1;

        // get intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            song = extras.getStringArray("song");
            Log.v("SONG PASSED", Arrays.deepToString(song));
        }

        // put notes into queue
        for (int i = 0; i < song.length; ++i) {
            notes.add(song[i]);
        }

        notes_played = 0;
        playSong();
    }

    public void startButton(View view) {


    }
    public void pauseButton(View view) {


    }
    public void stopButton(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(PlayAlong.this);
        builder.setTitle("Save Song Recording");

        View viewInflated = LayoutInflater.from(PlayAlong.this).inflate(R.layout.save_recording_dialog, (ViewGroup) findViewById(R.id.content), false);

        // text input field
        final EditText input = (EditText) viewInflated.findViewById(R.id.input);
        builder.setView(viewInflated);

        // save file
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                String m_Text = input.getText().toString();
            }
        });

        // discard file
        builder.setNegativeButton("Discard", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    public boolean isBlowingValue() {
        boolean recorder = true;
        short[] buffer = new short[minSize];

        ar.startRecording();
        while(recorder)
        {
            // determine_note();
            ar.read(buffer, 0, minSize);
            for (short s : buffer)
            {
                if (Math.abs(s) > 27000)   //DETECT VOLUME (IF I BLOW IN THE MIC)
                {
                    int blow_value = Math.abs(s);
                    // System.out.println("Blow Value="+blow_value);
                    ar.stop();
                    recorder = false;

//                    if(!mp.isPlaying()) {
//                        mp.release();
//                        mp = MediaPlayer.create(this, mp3_file);
//                        mp.start();
//                    }

//                    try {
//                        Thread.sleep(500);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    return true;
                }
            }
        }
        return false;

    }

    public boolean isBlowing() {
        recordingThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    isBlowingValue();
                    // Log.v("mic", "is blowing is" + isBlowingValue());
                }
                //display done screen?????????????????!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!?!
            }
        });
        recordingThread.start();
        return blowDetected;
    }

    public void playSong() {
        isBlowing();
        determineNote();

        playingThread = new Thread(new Runnable() {
            @Override
            public void run() {
                    // loop through song array and for each note wait until the correct button combo + microphone is played before you move to next note
                    String noteInSong = "";
                    String prevNote = "";
                    while (!notes.isEmpty()) {

                        // note to be played
                        noteInSong = notes.peek();

                        if (prevNote != noteInSong) {
                            // display the note that needs to be played
                            note_being_played.setText(noteInSong.toUpperCase() + "\n1");

                            // highlight the buttons that need to be played for this note
                            highlightNote(noteInSong);
                        }

                        // determine note user is pressing
                        if (note.equals(noteInSong)) {
                            notes.remove();
                            unhighlightNote();

                            // play the note here!!!
                            if(!mp.isPlaying()) {
                                mp.release();
                                mp = MediaPlayer.create(PlayAlong.this, mp3_file);
                                mp.start();
                            }

                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        prevNote = noteInSong;

                        // unhighlight the note buttons now that they're pressed
                        // unhighlightNote();

                        // wait until that button combo is pressed + blow detected to move to next note
                    }

                Log.v("done", "completed song!!");

                Intent intent = new Intent(getBaseContext(), CompletedSong.class);
                intent.putExtra("song", song);
                startActivity(intent);
                }
        });

        playingThread.start();

    }

    private void unhighlightNote() {
        Left1.setImageResource(R.drawable.left_button);
        Left2.setImageResource(R.drawable.left_button);
        Left3.setImageResource(R.drawable.left_button);
        Left4.setImageResource(R.drawable.left_button);
        Right1.setImageResource(R.drawable.right_button);
        Right2.setImageResource(R.drawable.right_button);
        Right3.setImageResource(R.drawable.right_button);
        Right4.setImageResource(R.drawable.right_button);
    }

    private void highlightNote(String playNote) {
        switch(playNote) {
            case "a":
                Left1.setImageResource(R.drawable.pushed_button);
                Left2.setImageResource(R.drawable.pushed_button);
                Left4.setImageResource(R.drawable.pushed_button);
                break;
            case "b":
                Left1.setImageResource(R.drawable.pushed_button);
                Left4.setImageResource(R.drawable.pushed_button);
                break;
            case "c":
                Left1.setImageResource(R.drawable.pushed_button);
                Left2.setImageResource(R.drawable.pushed_button);
                Left3.setImageResource(R.drawable.pushed_button);
                Left4.setImageResource(R.drawable.pushed_button);
                Right1.setImageResource(R.drawable.pushed_button);
                Right2.setImageResource(R.drawable.pushed_button);
                Right3.setImageResource(R.drawable.pushed_button);
                Right4.setImageResource(R.drawable.pushed_button);
                break;
            case "d":
                Left1.setImageResource(R.drawable.pushed_button);
                Left2.setImageResource(R.drawable.pushed_button);
                Left3.setImageResource(R.drawable.pushed_button);
                Left4.setImageResource(R.drawable.pushed_button);
                Right1.setImageResource(R.drawable.pushed_button);
                Right2.setImageResource(R.drawable.pushed_button);
                Right3.setImageResource(R.drawable.pushed_button);
                break;
            case "e":
                Left1.setImageResource(R.drawable.pushed_button);
                Left2.setImageResource(R.drawable.pushed_button);
                Left3.setImageResource(R.drawable.pushed_button);
                Left4.setImageResource(R.drawable.pushed_button);
                Right1.setImageResource(R.drawable.pushed_button);
                Right2.setImageResource(R.drawable.pushed_button);
                break;
            case "f":
                Left1.setImageResource(R.drawable.pushed_button);
                Left2.setImageResource(R.drawable.pushed_button);
                Left3.setImageResource(R.drawable.pushed_button);
                Left4.setImageResource(R.drawable.pushed_button);
                Right1.setImageResource(R.drawable.pushed_button);
                Right3.setImageResource(R.drawable.pushed_button);
                Right4.setImageResource(R.drawable.pushed_button);
                break;
            case "g":
                Left1.setImageResource(R.drawable.pushed_button);
                Left2.setImageResource(R.drawable.pushed_button);
                Left3.setImageResource(R.drawable.pushed_button);
                Left4.setImageResource(R.drawable.pushed_button);
                break;
            default:
                // nothing is highlighted
        }
    }

    public void determineNote(){
        determiningThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    boolean a = L1 && L2 && !L3 && L4 && !L4_2 && !R1 && !R2 && !R3 && !R3_2 && !R4 && !R4_2;
                    boolean b = L1 && !L2 && !L3 && L4 && !L4_2 && !R1 && !R2 && !R3 && !R3_2 && !R4 && !R4_2;
                    boolean c = L1 && L2 && L3 && L4 && !L4_2 && R1 && R2 && R3 && !R3_2 && R4 && !R4_2;
                    boolean d = L1 && L2 && L3 && L4 && !L4_2 && R1 && R2 && R3 && !R3_2 && !R4 && !R4_2;
                    boolean e = L1 && L2 && L3 && L4 && !L4_2 && R1 && R2 && !R3 && !R3_2 && !R4 && !R4_2;
                    boolean f = L1 && L2 && L3 && L4 && !L4_2 && R1 && !R2 && R3 && !R3_2 && !R4 && !R4_2;
                    boolean g = L1 && L2 && L3 && L4 && !L4_2 && !R1 && !R2 && !R3 && !R3_2 && !R4 && !R4_2;

                    if (a) {
                        mp3_file = R.raw.a2;
                        note = "a";
                    } else if (b) {
                        mp3_file = R.raw.b2;
                        note = "b";
                    } else if (c) {
                        mp3_file = R.raw.c2;
                        note = "c";
                    } else if (d) {
                        mp3_file = R.raw.d2;
                        note = "d";
                    } else if (e) {
                        mp3_file = R.raw.e2;
                        note = "e";
                    } else if (f) {
                        mp3_file = R.raw.f2;
                        note = "f";
                    } else if (g) {
                        mp3_file = R.raw.g2;
                        note = "g";
                    } else {
                        // no valid note played
                        note = "";
                    }
                }
            }
        });
        determiningThread.start();
    }

}