package com.example.registrationpage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText etName, etEmail, etPassword;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(v -> {
            String name = etName.getText().toString();
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                // Save details in SharedPreferences
                SharedPreferences prefs = getSharedPreferences("UserData", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("name", name);
                editor.putString("email", email);
                editor.putString("password", password);
                editor.apply();

                // Pass name via Intent to WelcomeActivity
                Intent intent = new Intent(RegisterActivity.this, com.example.registrationpage.WelcomeActivity.class);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });
    }
}
