package ece.wisc.recorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LearnToPlay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_to_play);
    }

    public void hotCrossBuns(View view) {
        Intent intent = new Intent(this, PlayAlong.class);
        startActivity(intent);
        intent.putExtra("song", "hotCrossBuns");
    }

    public void twinkle(View view) {
        Intent intent = new Intent(this, PlayAlong.class);
        startActivity(intent);
        intent.putExtra("song", "twinkleTwinkle");
    }

    public void maryLamb(View view) {
        Intent intent = new Intent(this, PlayAlong.class);
        startActivity(intent);
        intent.putExtra("song", "maryHadALittleLamb");
    }

}