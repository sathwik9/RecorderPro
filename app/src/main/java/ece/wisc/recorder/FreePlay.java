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

public class FreePlay extends AppCompatActivity {

    private final int MY_PERMISSIONS_RECORD_AUDIO = 1;
    private static final int RECORDER_SAMPLERATE = 8000;
    private static final int RECORDER_CHANNELS = AudioFormat.CHANNEL_IN_MONO;
    private static final int RECORDER_AUDIO_ENCODING = AudioFormat.ENCODING_PCM_16BIT;

    private AudioRecord ar = null;
    private int minSize = 0;

    private boolean isRecording = true;
    private Thread recordingThread = null;
    private boolean blowDetected = false;

    MediaPlayer mp;
    TextView note_being_played;
    ImageView Left1, Left2, Left3, Left4, Left4_2, Right1, Right2, Right3, Right4, Right3_2, Right4_2;
    private boolean L1, L2, L3, L4, L4_2, R1, R2, R3, R3_2, R4, R4_2;
    private Integer mp3_file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_play);

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
                        Left1.setImageResource(R.drawable.pushed_button);
                        L1 = true;
                        break;
                    }
                    case MotionEvent.ACTION_UP:{
                        Left1.setImageResource(R.drawable.left_button);
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
                        Left2.setImageResource(R.drawable.pushed_button);
                        L2 = true;
                        break;
                    }
                    case MotionEvent.ACTION_UP:{
                        Left2.setImageResource(R.drawable.left_button);
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
                        Left3.setImageResource(R.drawable.pushed_button);
                        L3 = true;
                        break;
                    }
                    case MotionEvent.ACTION_UP:{
                        Left3.setImageResource(R.drawable.left_button);
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
                        Left4.setImageResource(R.drawable.pushed_button);
                        L4 = true;
                        break;
                    }
                    case MotionEvent.ACTION_UP:{
                        Left4.setImageResource(R.drawable.left_button);
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
                        Left4_2.setImageResource(R.drawable.pushed_button);
                        L4_2 = true;
                        break;
                    }
                    case MotionEvent.ACTION_UP:{
                        Left4_2.setImageResource(R.drawable.left_button);
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
                        Right1.setImageResource(R.drawable.pushed_button);
                        R1 = true;
                        break;
                    }
                    case MotionEvent.ACTION_UP:{
                        Right1.setImageResource(R.drawable.right_button);
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
                        Right2.setImageResource(R.drawable.pushed_button);
                        R2 = true;
                        break;
                    }
                    case MotionEvent.ACTION_UP:{
                        Right2.setImageResource(R.drawable.right_button);
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
                        Right3.setImageResource(R.drawable.pushed_button);
                        R3 = true;
                        break;
                    }
                    case MotionEvent.ACTION_UP:{
                        Right3.setImageResource(R.drawable.right_button);
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
                        Right3_2.setImageResource(R.drawable.pushed_button);
                        R3_2 = true;
                        break;
                    }
                    case MotionEvent.ACTION_UP:{
                        Right3_2.setImageResource(R.drawable.right_button);
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
                        Right4.setImageResource(R.drawable.pushed_button);
                        R4 = true;
                        break;
                    }
                    case MotionEvent.ACTION_UP:{
                        Right4.setImageResource(R.drawable.right_button);
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
                        Right4_2.setImageResource(R.drawable.pushed_button);
                        R4_2 = true;
                        break;
                    }
                    case MotionEvent.ACTION_UP:{
                        Right4_2.setImageResource(R.drawable.right_button);
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
        Intent intent = getIntent();
        Log.v("MICROPHONE_CLASS", "microphone activity starting!!");
        isBlowing();
    }

    public void startButton(View view) {


    }
    public void pauseButton(View view) {


    }
    public void stopButton(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(FreePlay.this);
        builder.setTitle("Save Song Recording");

        View viewInflated = LayoutInflater.from(FreePlay.this).inflate(R.layout.save_recording_dialog, (ViewGroup) findViewById(R.id.content), false);

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
            determine_note();
            ar.read(buffer, 0, minSize);
            for (short s : buffer)
            {
                if (Math.abs(s) > 30000)   //DETECT VOLUME (IF I BLOW IN THE MIC)
                {
                    int blow_value = Math.abs(s);
                    // System.out.println("Blow Value="+blow_value);
                    ar.stop();
                    recorder = false;

                    if(!mp.isPlaying()) {
                        mp.release();
                        mp = MediaPlayer.create(this, mp3_file);
                        mp.start();
                    }

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

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
            }
        });
        recordingThread.start();
        return blowDetected;
    }

    public void determine_note(){
        if(L1 && L2 && L3  && L4  && !L4_2 &&
                R1 && R2 && R3 && R4 && !R3_2 && !R4_2){
            note_being_played.setText("C \n1");
            //mp = MediaPlayer.create(this, R.id.left);
            mp3_file = R.raw.c1;
        }
        else if(L1 && L2 && L3  && L4  && !L4_2 &&
                R1 && R2 && R3 && !R4 && !R3_2 && R4_2){
            note_being_played.setText("C# \n1");
            mp3_file = R.raw.c_1;
        }
        else if(L1 && L2 && L3  && L4  && !L4_2 &&
                R1 && R2 && R3 && !R4 && !R3_2 && !R4_2){
            note_being_played.setText("D \n1");
            mp3_file = R.raw.d1;
        }
        else if(L1 && L2 && L3  && L4  && !L4_2 &&
                R1 && R2 && !R3 && !R4 && R3_2 && !R4_2){
            note_being_played.setText("D# \n1");
            mp3_file = R.raw.d_1;
        }
        else if(L1 && L2 && L3  && L4  && !L4_2 &&
                R1 && R2 && !R3 && !R4 && !R3_2 && !R4_2){
            note_being_played.setText("E \n1");
            mp3_file = R.raw.e1;
        }
        else if(L1 && L2 && L3  && L4  && !L4_2 &&
                R1 && !R2 && R3 && R4 && !R3_2 && !R4_2){
            note_being_played.setText("F \n1");
            mp3_file = R.raw.f1;
        }
        else if(L1 && L2 && L3  && L4  && !L4_2 &&
                !R1 && R2 && R3 && !R4 && !R3_2 && !R4_2){
            note_being_played.setText("F# \n1");
            mp3_file = R.raw.f_1;
        }
        else if(L1 && L2 && L3  && L4  && !L4_2 &&
                !R1 && !R2 && !R3 && !R4 && !R3_2 && !R4_2){
            note_being_played.setText("G \n1");;
            mp3_file = R.raw.g1;
        }
        else if(L1 && L2 && !L3  && L4  && !L4_2 &&
                R1 && R2 && !R3 && !R4 && R3_2 && !R4_2){
            note_being_played.setText("G# \n1");
            mp3_file = R.raw.g_1;
        }
        else if(L1 && L2 && !L3  && L4  && !L4_2 &&
                !R1 && !R2 && !R3 && !R4 && !R3_2 && !R4_2){
            note_being_played.setText("A \n1");
            mp3_file = R.raw.a1;
        }
        else if(L1 && !L2 && L3  && L4  && !L4_2 &&
                !R1 && R2 && R3 && !R4 && !R3_2 && !R4_2){
            note_being_played.setText("A# \n1");
            mp3_file = R.raw.a_1;
        }
        else if(L1 && !L2 && !L3  && L4  && !L4_2 &&
                !R1 && !R2 && !R3 && !R4 && !R3_2 && !R4_2){
            note_being_played.setText("B \n1");
            mp3_file = R.raw.b1;
        }
        else if(!L1 && L2 && !L3  && L4  && !L4_2 &&
                !R1 && !R2 && !R3 && !R4 && !R3_2 && !R4_2){
            note_being_played.setText("C \n2");
            mp3_file = R.raw.c2;
        }
        else if(L1 && L2 && !L3  && !L4  && !L4_2 &&
                !R1 && !R2 && !R3 && !R4 && !R3_2 && !R4_2){
            note_being_played.setText("C# \n2");
            mp3_file = R.raw.c_2;
        }
        else if(!L1 && L2 && !L3  && !L4  && !L4_2 &&
                !R1 && !R2 && !R3 && !R4 && !R3_2 && !R4_2){
            note_being_played.setText("D \n2");
            mp3_file = R.raw.d2;
        }
        else if(!L1 && L2 && L3  && !L4  && !L4_2 &&
                !R1 && !R2 && !R3 && !R4 && !R3_2 && !R4_2){
            note_being_played.setText("D# \n2");
            mp3_file = R.raw.d_2;
        }
        //there are another 16 notes but they use the L4_2 fingering and we don't have that available
        // with our current button layout
        else if(L1 && !L2 && L3  && !L4  && L4_2 &&
                R1 && R2 && !R3 && !R4 && !R3_2 && !R4_2){
            note_being_played.setText("E \n2");
            mp3_file = R.raw.e2;
        }
        else if(L1 && L2 && L3  && !L4  && L4_2 &&
                R1 && !R2 && R3 && !R4 && !R3_2 && !R4_2){
            note_being_played.setText("F \n2");
            mp3_file = R.raw.f2;
        }
        else if(L1 && L2 && L3  && !L4  && L4_2 &&
                !R1 && R2 && !R3 && !R4 && !R3_2 && !R4_2){
            note_being_played.setText("F# \n2");
            mp3_file = R.raw.f_2;
        }
        else if(L1 && L2 && L3  && !L4  && L4_2 &&
                !R1 && !R2 && !R3 && !R4 && !R3_2 && !R4_2){
            note_being_played.setText("G \n2");
            mp3_file = R.raw.g2;
        }
        else if(L1 && L2 && !L3  && !L4  && L4_2 &&
                R1 && !R2 && !R3 && !R4 && !R3_2 && !R4_2){
            note_being_played.setText("G# \n2");
            mp3_file = R.raw.g_2;
        }
        else if(L1 && L2 && !L3  && !L4  && L4_2 &&
                !R1 && !R2 && !R3 && !R4 && !R3_2 && !R4_2){
            note_being_played.setText("A \n2");
            mp3_file = R.raw.a2;
        }
        else if(L1 && L2 && !L3  && !L4  && L4_2 &&
                !R1 && R2 && R3 && !R4 && !R3_2 && !R4_2){
            note_being_played.setText("A# \n2");
            mp3_file = R.raw.a_2;
        }
        else if(L1 && L2 && !L3  && !L4  && L4_2 &&
                R1 && R2 && !R3 && !R4 && !R3_2 && !R4_2){
            note_being_played.setText("B \n2");
            mp3_file = R.raw.b2;
        }
        else if(!L1 && !L2 && !L3  && !L4  && L4_2 &&
                !R1 && !R2 && !R3 && !R4 && !R3_2 && !R4_2){
            note_being_played.setText(" \n3");
            mp3_file = R.raw.d_2;
        }
        else if(L1 && !L2 && !L3  && !L4  && L4_2 &&
                R1 && R2 && !R3 && !R4 && !R3_2 && !R4_2){
            note_being_played.setText("C \n3");
            mp3_file = R.raw.c3;
        }
        else if(L1 && !L2 && L3  && !L4  && L4_2 &&
                R1 && !R2 && R3 && !R4 && !R3_2 && R4_2){
            note_being_played.setText("D# \n3");
            mp3_file = R.raw.d_3;
        }
        else if(L1 && L2 && !L3  && !L4  && L4_2 &&
                R1 && R2 && !R3 && !R4 && !R3_2 && !R4_2){
            note_being_played.setText("F# \n3");
            mp3_file = R.raw.f_3;
        }
        else if(L1 && !L2 && !L3  && !L4  && L4_2 &&
                R1 && !R2 && !R3 && !R4 && !R3_2 && !R4_2){
            note_being_played.setText("G \n3");
            mp3_file = R.raw.g3;
        }
        else{
            note_being_played.setText("-\n-");
        }
        //can't use the C#, E, or F notes in third octave since they use the knee hole.


    }
}