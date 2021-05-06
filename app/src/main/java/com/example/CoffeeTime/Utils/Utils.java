package com.example.CoffeeTime.Utils;

import android.widget.EditText;
import android.widget.Toast;

public class Utils {

    public static boolean checkNotEmpty(android.content.Context context, EditText inputUserName, EditText email, EditText password1, EditText password2) {
        boolean res = true;

        if(inputUserName.getText().toString().isEmpty()) {
            Toast.makeText(context,"enter username",Toast.LENGTH_SHORT).show();
            res = false;
        }else if(email.getText().toString().isEmpty()) {
            Toast.makeText(context,"enter email address",Toast.LENGTH_SHORT).show();
            res = false;
        }else if (password1.getText().toString().isEmpty()) {
            Toast.makeText(context,"enter password",Toast.LENGTH_SHORT).show();
            res = false;
        }else if (password2.getText().toString().isEmpty()) {
            Toast.makeText(context,"re-enter password",Toast.LENGTH_SHORT).show();
            res = false;
        }

        return res;
    }
}
