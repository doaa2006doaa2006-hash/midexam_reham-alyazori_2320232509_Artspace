package com.example.mid_exam_reham_alyazori;

import android.os.Bundle;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private int currentIndex = 0;

    private final int[] artworkImages = {
            R.drawable.artwork1,
            R.drawable.artwork2,
            R.drawable.artwork3,
            R.drawable.artwork4,
            R.drawable.artwork5,
            R.drawable.artwork6
    };
    private final String[] artworkTitles = {
            "Deep Field Stellar Deaths",
            "Cosmic Nebula",
            "Galaxy of Stars",
            "The Blue Universe",
            "Stellar Formation",
            "Hubble Ngc Flat Final"
    };

    private final String[] artworkArtists = {
            "Smacs Carin (2021)",
            "NASA (2022)",
            "NASA (2020)",
            "ESA (2023)",
            "NASA (2024)",
            "Sonay Billan (2019)"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt("currentIndex", 0);
        }

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main),
                (v, insets) -> {
                    Insets systemBars = insets.getInsets(
                            WindowInsetsCompat.Type.systemBars()
                    );

                    v.setPadding(
                            systemBars.left,
                            systemBars.top,
                            systemBars.right,
                            systemBars.bottom
                    );

                    return insets;
                }
        );


        ImageView artworkImage = findViewById(R.id.artworkImage);

        TextView artworkTitle = findViewById(R.id.artworkTitle);
        TextView artworkArtist = findViewById(R.id.artworkArtist);
        TextView artworkCounter = findViewById(R.id.artworkCounter);

        Button nextButton = findViewById(R.id.nextButton);
        Button previousButton = findViewById(R.id.previousButton);

        ColorStateList buttonColors = new ColorStateList(
                new int[][]{
                        new int[]{-android.R.attr.state_enabled},
                        new int[]{android.R.attr.state_enabled}
                },
                new int[]{
                        Color.parseColor("#E5DDD6"),
                        Color.parseColor("#914C1B")
                }
        );

        previousButton.setBackgroundTintList(buttonColors);
        nextButton.setBackgroundTintList(buttonColors);

        previousButton.setTextColor(ColorStateList.valueOf(Color.WHITE));
        nextButton.setTextColor(ColorStateList.valueOf(Color.WHITE));


        artworkImage.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ArtworkDetailsActivity.class);

            intent.putExtra("title", artworkTitles[currentIndex]);
            intent.putExtra("artist", artworkArtists[currentIndex]);

            startActivity(intent);
        });


        artworkImage.setImageResource(artworkImages[currentIndex]);
        artworkTitle.setText(artworkTitles[currentIndex]);
        artworkArtist.setText(artworkArtists[currentIndex]);
        artworkCounter.setText((currentIndex + 1) + " / 6");

        previousButton.setEnabled(currentIndex > 0);
        nextButton.setEnabled(currentIndex < artworkImages.length - 1);


        nextButton.setOnClickListener(v -> {
            if (currentIndex < artworkImages.length - 1) {
                currentIndex++;

                artworkImage.setImageResource(artworkImages[currentIndex]);
                artworkTitle.setText(artworkTitles[currentIndex]);
                artworkArtist.setText(artworkArtists[currentIndex]);
                artworkCounter.setText((currentIndex + 1) + " / 6");

                previousButton.setEnabled(true);
                nextButton.setEnabled(currentIndex < artworkImages.length - 1);
            }
        });

        previousButton.setOnClickListener(v -> {
            if (currentIndex > 0) {
                currentIndex--;

                artworkImage.setImageResource(artworkImages[currentIndex]);
                artworkTitle.setText(artworkTitles[currentIndex]);
                artworkArtist.setText(artworkArtists[currentIndex]);
                artworkCounter.setText((currentIndex + 1) + " / 6");

                previousButton.setEnabled(currentIndex > 0);
                nextButton.setEnabled(true);
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("currentIndex", currentIndex);
    }

}
