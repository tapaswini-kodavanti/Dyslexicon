package com.example.dyslexicon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class SightWordsActivity extends AppCompatActivity implements View.OnClickListener{
    private static String[] images = {"ball.jpeg", "bear.jpeg", "bird.png", "cat.jpeg", "city.jpeg", "dog.jpeg", "fish.jpeg", "mouse.jpeg"};
    private StorageReference mStorageReference;
    private static int currentIndex = 0;

    TextView wordLabel;

    Button exitButton;
    Button nextButton;
    Button prevButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sight_words);

        wordLabel = (TextView) findViewById(R.id.sight_words_word_label);

        exitButton = (Button) findViewById(R.id.exit_button);
        nextButton = (Button) findViewById(R.id.sight_words_next_image);
        prevButton = (Button) findViewById(R.id.sight_words_prev_image);

        exitButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        prevButton.setOnClickListener(this);


        this.displayImage(0);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.exit_button:
                openPracticeActivity();
                break;
            case R.id.sight_words_next_image:
                displayNextImage();
                break;
            case R.id.sight_words_prev_image:
                displayPrevImage();
                break;
        }
    }

    public void openPracticeActivity() {
        Intent intent = new Intent(this, PracticeActivity.class);
        startActivity(intent);
    }

    private void displayNextImage() {
        displayImage(1);
    }

    private void displayPrevImage() {
        displayImage(-1);
    }

    private void displayImage(int change) {
        int index = getIndex(change);

        String imageName = images[index];
        String image = imageName.substring(0, imageName.indexOf("."));
        String imageSuffix = imageName.substring(imageName.indexOf(".") + 1);

        mStorageReference = FirebaseStorage.getInstance().getReference().child("sightWords/" + image + "/" + imageName);

        try {
            final File localFile = File.createTempFile(image, imageSuffix);
            mStorageReference.getFile(localFile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(SightWordsActivity.this, "Pictured Retrieved", Toast.LENGTH_SHORT);
                            Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                            ((ImageView)findViewById(R.id.sightWordsImageView)).setImageBitmap(bitmap);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(SightWordsActivity.this, "Error Occurred", Toast.LENGTH_SHORT);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        wordLabel.setText(image.toUpperCase());
    }

    private int getIndex(int change) {
        currentIndex += change;
        if (currentIndex >= 0) {
            currentIndex %= images.length;
        } else {
            currentIndex += images.length;
        }
        return currentIndex;
    }
}