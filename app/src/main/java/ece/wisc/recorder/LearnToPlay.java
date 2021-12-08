package ece.wisc.recorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LearnToPlay extends AppCompatActivity {

    private String[] buns;
    private String[] twinkle;
    private String[] mary;
    private String[] shark;
    private String[] sleep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_to_play);

        buns = new String[]{"b", "a", "g", "b", "a", "g", "g", "g", "g", "g", "a", "a", "a", "a", "b", "a", "g"};
        twinkle = new String[]{"c", "c", "g", "g", "a", "a", "g", "f", "f", "e", "e", "d", "d", "c"};
        mary = new String[]{"b", "a", "g", "a", "b", "b", "b", "a", "a", "a", "b", "b", "b", "b", "a", "g", "a", "b", "b", "b", "a", "a", "b", "a","g"};
        shark = new String[]{"d", "e", "g", "g", "g","g", "g", "g", "g", "d", "e", "g", "g", "g","g", "g", "g", "g", "d", "e", "g", "g", "g","g", "g", "g", "g", "g", "g", "f"};
        sleep = new String[]{"g", "a", "b", "a", "g", "a", "g", "a", "b", "a", "b", "g", "g", "a", "b", "a", "g", "a", "g", "a", "b", "a", "b", "g"};
    }

    public void hotCrossBuns(View view) {
        Intent intent = new Intent(this, PlayAlong.class);
        startActivity(intent);
        intent.putExtra("song", buns);
    }

    public void twinkle(View view) {
        Intent intent = new Intent(this, PlayAlong.class);
        startActivity(intent);
        intent.putExtra("song", twinkle);
    }

    public void maryLamb(View view) {
        Intent intent = new Intent(this, PlayAlong.class);
        startActivity(intent);
        intent.putExtra("song", mary);
    }

    public void babyShark(View view) {
        Intent intent = new Intent(this, PlayAlong.class);
        startActivity(intent);
        intent.putExtra("song", shark);
    }

    public void gentlySleep(View view) {
        Intent intent = new Intent(this, PlayAlong.class);
        startActivity(intent);
        intent.putExtra("song", sleep);
    }

}