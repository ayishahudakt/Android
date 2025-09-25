package com.example.optionmenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Inflate the menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu); // your menu xml
        return true;
    }

    // Handle menu clicks
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.switch1) {
            // Open HomeActivity
            startActivity(new Intent(this, HomeActivity.class));
            return true;

        } else if (id == R.id.switch2) {
            // Open ProfileActivity
            startActivity(new Intent(this, com.example.optionmenu.ProfileActivity.class));
            return true;

        } else if (id == R.id.switch3) {
            // Open SettingsActivity
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
