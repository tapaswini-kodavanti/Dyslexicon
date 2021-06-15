package com.example.dyslexicon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button playButton;
    private Button practiceButton;
    private Button aboutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playButton = (Button) findViewById(R.id.play_button);
        practiceButton = (Button) findViewById(R.id.practice_button);
        aboutButton = (Button) findViewById(R.id.about_button);

        playButton.setOnClickListener(this);
        practiceButton.setOnClickListener(this);
        aboutButton.setOnClickListener(this);


    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play_button:
                openPlayActivity();
                break;
            case R.id.practice_button:
                openPracticeActivity();
                break;
            case R.id.about_button:
                openAboutActivity();
        }
    }

    public void openPlayActivity() {
        Intent intent = new Intent(this, PlayActivity.class);
        startActivity(intent);
    }

    public void openPracticeActivity() {
        Intent intent = new Intent(this, PracticeActivity.class);
        startActivity(intent);
    }

    public void openAboutActivity() {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }


}