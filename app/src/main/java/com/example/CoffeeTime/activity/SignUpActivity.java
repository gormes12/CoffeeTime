package com.example.CoffeeTime.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.CoffeeTime.Firebase.AuthHelper;
import com.example.CoffeeTime.R;
import com.example.CoffeeTime.model.User;

public class SignUpActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_layout);

        Button ButtonContinue = (Button) findViewById(R.id.ButtonContinue);
        ButtonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText inputUserName = (EditText) findViewById(R.id.InputUserName);
                EditText email = (EditText) findViewById(R.id.InputEmail);
                EditText password1 = (EditText) findViewById(R.id.InputPassword1);
                EditText password2 = (EditText) findViewById(R.id.InputPassword2);


                if(checkNotEmpty(inputUserName, email, password1, password2)){
                    if(checkPasswordAndEmail(password1, password2, email)){
                        AuthHelper.RegisterUser(email.getText().toString(), password1.getText().toString(), SignUpActivity.this::executeActivity);
                    }
                }
            }
        });

    }

    private boolean checkPasswordAndEmail(EditText password1, EditText password2, EditText email) {
        boolean res = true;
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if(!(password1.getText().toString().equals(password2.getText().toString()))){
            Toast.makeText(getApplicationContext(),"password not equals",Toast.LENGTH_SHORT).show();
            res = false;
        }else if(!(email.getText().toString().trim().matches(emailPattern))){
            Toast.makeText(getApplicationContext(),"Invalid email address", Toast.LENGTH_SHORT).show();
            res = false;
        }

        return res;
    }

    private boolean checkNotEmpty(EditText inputUserName, EditText email, EditText password1, EditText password2) {
        boolean res = true;

        if(inputUserName.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(),"enter username",Toast.LENGTH_SHORT).show();
            res = false;
        }else if(email.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(),"enter email address",Toast.LENGTH_SHORT).show();
            res = false;
        }else if (password1.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(),"enter password",Toast.LENGTH_SHORT).show();
            res = false;
        }else if (password2.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(),"re-enter password",Toast.LENGTH_SHORT).show();
            res = false;
        }

        return res;
    }

    public void executeActivity(String id){
        if (id.equals(AuthHelper.GetUserId())){
            EditText inputUserName = (EditText) findViewById(R.id.InputUserName);
            try {
                User user = AuthHelper.getCreatedUser();
                user.NickName = inputUserName.getText().toString();
                Intent intent = new Intent(SignUpActivity.this, OrganizationSelectActivity.class);
                intent.putExtra("userClass", user);
                startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
