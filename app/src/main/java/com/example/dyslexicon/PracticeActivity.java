package com.example.dyslexicon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PracticeActivity extends AppCompatActivity implements View.OnClickListener{
    Button exitButton;
    Button sightWordsButton;
    Button decodableWordsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);

        exitButton = (Button) findViewById(R.id.exit_button);
        sightWordsButton = (Button) findViewById(R.id.sight_words_button);
        decodableWordsButton = (Button) findViewById(R.id.decodable_words_button);

        exitButton.setOnClickListener(this);
        sightWordsButton.setOnClickListener(this);
        decodableWordsButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.exit_button:
                openMainActivity();
                break;
            case R.id.sight_words_button:
                openSightWordsActivity();
                break;
            case R.id.decodable_words_button:
                openDecodableWordsButton();
                break;
        }
    }

    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openSightWordsActivity() {
        Intent intent = new Intent(this, SightWordsActivity.class);
        startActivity(intent);
    }

    public void openDecodableWordsButton() {
        Intent intent = new Intent(this, DecodableWordsActivity.class);
        startActivity(intent);
    }
}