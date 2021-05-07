package com.example.CoffeeTime;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.CoffeeTime.Firebase.AuthHelper;
import com.example.CoffeeTime.activity.MatchActivity;
import com.example.CoffeeTime.activity.SignUpActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_layout);
//        startActivity(new Intent(MainActivity.this, MatchActivity.class)); //TODO:DELETE!!!!!


        Button ButtonSign = (Button) findViewById(R.id.ButtonSign);
        ButtonSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText email = (EditText) findViewById(R.id.InputEmail);
                EditText password = (EditText) findViewById(R.id.InputPassword);
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                if(email.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(),"enter email address",Toast.LENGTH_SHORT).show();
                }
                else if (password.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(),"enter password",Toast.LENGTH_SHORT).show();
                }
                else{
                    if (email.getText().toString().trim().matches(emailPattern)) {
                        AuthHelper.Login(email.getText().toString(), password.getText().toString(), MainActivity.this::executeActivity);
                    } else {
                        Toast.makeText(getApplicationContext(),"Invalid email address", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        Button ButtonSignup = (Button) findViewById(R.id.ButtonSignup);
        ButtonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SignUpActivity.class));
            }
        });
    }



    public void executeActivity(String id){
        if (id.equals(AuthHelper.GetUserId())){
            startActivity(new Intent(MainActivity.this, MatchActivity.class));
        }
    }
}