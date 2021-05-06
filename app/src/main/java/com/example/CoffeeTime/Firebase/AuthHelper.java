package com.example.CoffeeTime.Firebase;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.CoffeeTime.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.function.Consumer;

import static android.content.ContentValues.TAG;

public class AuthHelper {
    private static FirebaseAuth mAuth = FirebaseAuth.getInstance();;
    private static User mCreated = new User();

    public AuthHelper(){
        mAuth = FirebaseAuth.getInstance();
    }

    public static boolean IsSignedInUser(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        return user != null;
    }

    public static String GetUserId(){
        if(IsSignedInUser()){
            return FirebaseAuth.getInstance().getCurrentUser().getUid();
        }

        return null;
    }

    public static void RegisterUser(String email, String password, Consumer<String> executable){
        Task<AuthResult> result = mAuth.createUserWithEmailAndPassword(email, password);
        result.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    user.sendEmailVerification();
                    Login(email, password, executable);
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                }
            }
        });
    }

    public static void Login(String email, String password, Consumer<String> executable){
        Task<AuthResult> result = mAuth.signInWithEmailAndPassword(email, password);
        result.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    mCreated.UniqueID = user.getUid();
                    executable.accept(user.getUid());
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                }
            }
        });
    }

    public static boolean IsVerifiedEmail(){
        return mAuth.getCurrentUser().isEmailVerified();
    }

    public static void ChangePassword(String newPassword){
        mAuth.getCurrentUser().updatePassword(newPassword);
    }

    public static void ForgotPassword(String email){
        mAuth.sendPasswordResetEmail(email);
    }

    public static void SignOut(){
        mAuth.signOut();
    }

    public static User getCreatedUser() throws Exception {
        if(mCreated.UniqueID == null){
            throw new Exception("Please login first");
        }

        return mCreated;
    }
}