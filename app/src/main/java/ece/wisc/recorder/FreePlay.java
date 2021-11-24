package ece.wisc.recorder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.util.Log;

public class FreePlay extends AppCompatActivity {

    private final int MY_PERMISSIONS_RECORD_AUDIO = 1;
    private static final int RECORDER_SAMPLERATE = 8000;
    private static final int RECORDER_CHANNELS = AudioFormat.CHANNEL_IN_MONO;
    private static final int RECORDER_AUDIO_ENCODING = AudioFormat.ENCODING_PCM_16BIT;

    private AudioRecord recorder = null;
    private boolean isRecording = true;
    private Thread recordingThread = null;
    private boolean blowDetected = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_play);

        // get intent
        Intent intent = getIntent();
        Log.v("MICROPHONE_CLASS", "microphone activity starting!!");
        isBlowing();
    }

    public boolean isBlowing() {
        int bufferSize = AudioRecord.getMinBufferSize(RECORDER_SAMPLERATE, RECORDER_CHANNELS, RECORDER_AUDIO_ENCODING);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, MY_PERMISSIONS_RECORD_AUDIO);
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding

            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

        }
        short[] buffer = new short[bufferSize];

        recorder = new AudioRecord(MediaRecorder.AudioSource.MIC, RECORDER_SAMPLERATE, RECORDER_CHANNELS, RECORDER_AUDIO_ENCODING, bufferSize);
        recorder.startRecording();
        blowDetected = false;

        Log.v("MICROPHONE_INPUT", "***RECORDING STARTED***");

        recordingThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(isRecording)
                {

                    recorder.read(buffer, 0, bufferSize);
                    for (short s : buffer)
                    {
                        if (Math.abs(s) > 27000)   // detect volume when blowing into mic
                        {
                            float blow_value=Math.abs(s);
                            Log.v("MICROPHONE_INPUT", "Blow Value=" + blow_value);
                            recorder.stop();
                            // isRecording = false;

                            blowDetected = true;

                        } else {
                            Log.v("MICROPHONE_INPUT", "***not detecting blow***");
                        }

                    }
                }
            }
        });

        recordingThread.start();

        return blowDetected;

    }
}