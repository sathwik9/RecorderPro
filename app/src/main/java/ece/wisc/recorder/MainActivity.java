package ece.wisc.recorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView L1, L2, L3, L4, R1, R2, R3, R4, R3_2, R4_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        L1 = 
    }

    public void freePlayButton(View view) {
        // go into free play mode
        Intent intent = new Intent(this, FreePlay.class);
        startActivity(intent);
    }
}