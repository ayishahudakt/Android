package com.example.toggleimage;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView1, imageView2;
    private boolean isFirstImageVisible = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Handle insets for system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Link UI elements
        imageView1 = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);
        Button toggleButton = findViewById(R.id.toggleButton);

        // Button click to toggle images
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFirstImageVisible) {
                    imageView1.setVisibility(View.GONE);  // Hide first image
                    imageView2.setVisibility(View.VISIBLE); // Show second image
                } else {
                    imageView1.setVisibility(View.VISIBLE); // Show first image
                    imageView2.setVisibility(View.GONE);    // Hide second image
                }
                isFirstImageVisible = !isFirstImageVisible; // Toggle state
            }
        });
    }
}
