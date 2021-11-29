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
import android.view.View;

public class FreePlay extends AppCompatActivity {

    private final int MY_PERMISSIONS_RECORD_AUDIO = 1;
    private static final int RECORDER_SAMPLE_RATE = 8000;
    private static final int RECORDER_CHANNELS = AudioFormat.CHANNEL_IN_MONO;
    private static final int RECORDER_AUDIO_ENCODING = AudioFormat.ENCODING_PCM_16BIT;

    private AudioRecord recorder = null;
    private boolean isRecording = true;
    private Thread recordingThread = null;
    private boolean blowDetected = false;

    private AudioRecord ar = null;
    private int minSize;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_play);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
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
        // get intent
        // Intent intent = getIntent();
        Log.v("MICROPHONE_CLASS", "microphone activity starting!!");
        isBlowing();
    }

    public void startButton(View view) {


    }
    public void pauseButton(View view) {


    }
    public void stopButton(View view) {


    }

    public boolean isBlowingValue() {
        boolean recorder = true;

        short[] buffer = new short[minSize];

        ar.startRecording();
        while(recorder)
        {

            ar.read(buffer, 0, minSize);
            for (short s : buffer)
            {
                if (Math.abs(s) > 27000)   //DETECT VOLUME (IF I BLOW IN THE MIC)
                {
                    int blow_value = Math.abs(s);
                    System.out.println("Blow Value="+blow_value);
                    ar.stop();
                    recorder=false;

                    return true;

                }
            }
        }
        return false;

    }

    public void isBlowing() {
        Log.v("MICROPHONE_INPUT", "***RECORDING STARTED***");

        recordingThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    //blowDetected = false;
                    Log.v("mic", "is blowing is" + isBlowingValue());
                }
            }
        });
        recordingThread.start();
    }
}

