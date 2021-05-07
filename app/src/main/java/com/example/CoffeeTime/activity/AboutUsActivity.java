package com.example.CoffeeTime.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.CoffeeTime.MainActivity;
import com.example.CoffeeTime.R;

public class AboutUsActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aboutus_layout);

        Button ButtonSend = (Button) findViewById(R.id.ButtonSend);
        ButtonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText inputUserName = (EditText) findViewById(R.id.editTextTextPersonName);
                EditText email = (EditText) findViewById(R.id.editTextTextEmailAddress);
                EditText msg = (EditText) findViewById(R.id.editTextTextMultiLine2);

                if(checkNotEmpty(inputUserName, email, msg)){
                        TextView msgLabel = (TextView) findViewById(R.id.msgLabel);
                        msgLabel.setText("Your message sent successfully");
                }
            }
        });

        Button ButtonEditUser = (Button) findViewById(R.id.ButtonEditUser);
        ButtonEditUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AboutUsActivity.this, EditProfileActivity.class));
            }
        });

        Button buttonHome = (Button) findViewById(R.id.buttonHome);
        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AboutUsActivity.this, MatchActivity.class));
            }
        });

        Button buttonSignOut = (Button) findViewById(R.id.buttonSignOut);
        buttonSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AboutUsActivity.this, MainActivity.class));
            }
        });



    }

    private boolean checkNotEmpty(EditText inputUserName, EditText email, EditText msg) {
        boolean res = true;

        if(inputUserName.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(),"enter username",Toast.LENGTH_SHORT).show();
            res = false;
        }else if(email.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(),"enter email address",Toast.LENGTH_SHORT).show();
            res = false;
        }else if (msg.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(),"enter message",Toast.LENGTH_SHORT).show();
            res = false;
        }

        return res;
    }
}
