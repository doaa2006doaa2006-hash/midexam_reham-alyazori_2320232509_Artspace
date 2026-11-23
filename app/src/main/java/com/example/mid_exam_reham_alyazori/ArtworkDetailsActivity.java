package com.example.mid_exam_reham_alyazori;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;
import android.net.Uri;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

public class ArtworkDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_artwork_details);

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main), (v, insets) -> {
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

        TextView detailsTitle = findViewById(R.id.detailsTitle);
        TextView detailsArtist = findViewById(R.id.detailsArtist);

        String title = getIntent().getStringExtra("title");
        String artist = getIntent().getStringExtra("artist");

        if (title != null) {
            detailsTitle.setText(title);
        }

        if (artist != null) {
            detailsArtist.setText(artist);
        }

        Button backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(v -> {
            finish();
        });

        Button visitWebsiteButton = findViewById(R.id.visitWebsiteButton);

        visitWebsiteButton.setOnClickListener(v -> {
            Intent intent = new Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.nasa.gov/")
            );

            startActivity(intent);
        });
    }
}
