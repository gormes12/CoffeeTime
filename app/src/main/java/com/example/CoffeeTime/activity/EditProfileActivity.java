package com.example.CoffeeTime.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.CoffeeTime.MainActivity;
import com.example.CoffeeTime.R;
import com.example.CoffeeTime.model.User;

public class EditProfileActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editprofile_layout);

//        String userID = AuthHelper.GetUserId();
//        FireStoreHelper.GetUser(userID, EditProfileActivity.this::pageExecute);

        pageExecute();
    }

    private void pageExecute(){
        Button ButtonUserName = (Button) findViewById(R.id.ButtonUserName);
        ButtonUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText inputUserName = (EditText) findViewById(R.id.InputNewUserName);
                if (inputUserName.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "enter username", Toast.LENGTH_SHORT).show();
                } else {
//                    user.setName(inputUserName.getText().toString());
//                    FireStoreHelper.UpdateUser(user);
                    startActivity(new Intent(EditProfileActivity.this, MatchActivity.class));

                }
            }
        });

        Button ButtonPassword = (Button) findViewById(R.id.ButtonPassword);
        ButtonPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText password1 = (EditText) findViewById(R.id.InputNewPassword1);
                EditText password2 = (EditText) findViewById(R.id.InputNewPassword2);

                if (password1.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(),"enter password",Toast.LENGTH_SHORT).show();
                }else if (password2.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(),"re-enter password",Toast.LENGTH_SHORT).show();
                }else if(!(password1.getText().toString().equals(password2.getText().toString()))){
                    Toast.makeText(getApplicationContext(),"password not equals",Toast.LENGTH_SHORT).show();
                }else{
//                    AuthHelper.ChangePassword(password1.getText().toString());
                    startActivity(new Intent(EditProfileActivity.this, MatchActivity.class));
                }
            }
        });

        Button ButtonGoToOrganization = (Button) findViewById(R.id.ButtonGoToOrganization);
        ButtonGoToOrganization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditProfileActivity.this, OrganizationSelectActivity.class));
            }
        });

        Button ButtonAbout = (Button) findViewById(R.id.buttonAbout2);
        ButtonAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditProfileActivity.this, AboutUsActivity.class));
            }
        });

        Button buttonHome = (Button) findViewById(R.id.buttonHome2);
        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditProfileActivity.this, MatchActivity.class));
            }
        });

        Button buttonSignOut = (Button) findViewById(R.id.buttonSignOut2);
        buttonSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditProfileActivity.this, MainActivity.class));
            }
        });
    }

    private void pageExecute(User user){
        Button ButtonUserName = (Button) findViewById(R.id.ButtonUserName);
        ButtonUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText inputUserName = (EditText) findViewById(R.id.InputNewUserName);
                if (inputUserName.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "enter username", Toast.LENGTH_SHORT).show();
                } else {
//                    user.setName(inputUserName.getText().toString());
//                    FireStoreHelper.UpdateUser(user);
                    startActivity(new Intent(EditProfileActivity.this, MatchActivity.class));

                }
            }
        });

        Button ButtonPassword = (Button) findViewById(R.id.ButtonPassword);
        ButtonPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText password1 = (EditText) findViewById(R.id.InputNewPassword1);
                EditText password2 = (EditText) findViewById(R.id.InputNewPassword2);

                if (password1.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(),"enter password",Toast.LENGTH_SHORT).show();
                }else if (password2.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(),"re-enter password",Toast.LENGTH_SHORT).show();
                }else if(!(password1.getText().toString().equals(password2.getText().toString()))){
                    Toast.makeText(getApplicationContext(),"password not equals",Toast.LENGTH_SHORT).show();
                }else{
//                    AuthHelper.ChangePassword(password1.getText().toString());
                    startActivity(new Intent(EditProfileActivity.this, MatchActivity.class));
                }
            }
        });

        Button ButtonGoToOrganization = (Button) findViewById(R.id.ButtonGoToOrganization);
        ButtonGoToOrganization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditProfileActivity.this, OrganizationSelectActivity.class));
            }
        });

    }
}
