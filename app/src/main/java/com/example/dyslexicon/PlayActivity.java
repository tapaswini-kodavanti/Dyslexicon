package com.example.dyslexicon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class PlayActivity extends AppCompatActivity implements View.OnClickListener{
    private Button exitButton;
    private TextView wordTv;
    private EditText wordEnteredTv;
    private Button validate, newGame;
    private String wordToFind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        exitButton = (Button) findViewById(R.id.exit_button);
        exitButton.setOnClickListener(this);

        validate = (Button) findViewById(R.id.validate);
        validate.setOnClickListener(this);

        newGame = (Button) findViewById(R.id.newGame);
        newGame.setOnClickListener(this);

        wordTv = (TextView) findViewById(R.id.wordTv);

        wordEnteredTv = (EditText) findViewById(R.id.wordEnteredEt);

        newGame();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.exit_button:
                openMainActivity();
                break;
            case R.id.validate:
                validate();
                break;
            case R.id.newGame:
                newGame();
                break;
        }
    }

    private void validate() {
        String w = wordEnteredTv.getText().toString();

        if (wordToFind.equals(w)) {
            Toast.makeText(this, "Congratulations! You found the correct word, " + wordToFind + "!", Toast.LENGTH_SHORT).show();
            newGame();
        } else {
            Toast.makeText(this, "Retry!", Toast.LENGTH_SHORT).show();
        }
    }

    private void newGame() {
        wordToFind = Anagram.randomWord();
        String wordShuffled = Anagram.shuffleWord(wordToFind);
        wordTv.setText(wordShuffled);
        wordEnteredTv.setText("");
    }

    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}