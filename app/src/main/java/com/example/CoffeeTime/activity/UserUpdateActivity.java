package com.example.CoffeeTime.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.CoffeeTime.R;

public class UserUpdateActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userupdated_layout);

        Button ButtonNext = (Button) findViewById(R.id.ButtonNext);
        ButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserUpdateActivity.this, MatchActivity.class));
            }
        });
    }
}
