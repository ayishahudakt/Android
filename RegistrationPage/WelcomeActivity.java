package com.example.registrationpage;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    TextView tvWelcome;
    LinearLayout layoutRegister, layoutWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutRegister = findViewById(R.id.layoutRegister);
        layoutWelcome = findViewById(R.id.layoutWelcome);
        tvWelcome = findViewById(R.id.tvWelcome);

        // Hide registration section and show welcome
        layoutRegister.setVisibility(View.GONE);
        layoutWelcome.setVisibility(View.VISIBLE);

        // Get name from Intent
        String nameIntent = getIntent().getStringExtra("name");

        // Or fallback to SharedPreferences
        SharedPreferences prefs = getSharedPreferences("UserData", MODE_PRIVATE);
        String namePrefs = prefs.getString("name", "User");

        // Display welcome message
        tvWelcome.setText("Welcome, " + (nameIntent != null ? nameIntent : namePrefs) + "!");
    }
}
