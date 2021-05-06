package com.example.CoffeeTime.Firebase;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.CoffeeTime.model.Meeting;
import com.example.CoffeeTime.model.Organization;
import com.example.CoffeeTime.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;
import java.util.function.Consumer;

public class FireStoreHelper {
    private DatabaseReference mDataBase;

    public FireStoreHelper(){
        mDataBase = FirebaseDatabase.getInstance().getReference();
    }

    // region User
    public void AddUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("null user");
        }

        mDataBase.child("users").child(user.UniqueID).setValue(user);
    }

    public void GetUser(String UserId, Consumer<User> executable){
        mDataBase.child("users").child(UserId).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    executable.accept((User) task.getResult().getValue());
                }
            }
        });

    }

    public void UpdateUser(User user){

    }

    public void DeleteUser(String UserId){
        mDataBase.child("users").child(UserId).removeValue();
    }
    // end region

    // region Organization
    public void AddOrganization(Organization organization){
        if (organization == null) {
            throw new IllegalArgumentException("null organization");
        }

        mDataBase.child("organizations").child(organization.UniqueID).setValue(organization);
    }

    public void GetOrganization(String OrganizationId, Consumer<Organization> executable){
        mDataBase.child("organizations").child(OrganizationId).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    executable.accept((Organization) task.getResult().getValue());
                }
            }
        });
    }

    public void GetAllOrganizations(Consumer<List<Organization>> executable){
        mDataBase.child("organizations").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    executable.accept((List<Organization>) task.getResult().getValue());
                }
            }
        });
    }

    public void UpdateOrganization(Organization organization){

    }

    public void DeleteOrganization(String OrganizationId){
        mDataBase.child("organizations").child(OrganizationId).removeValue();
    }
    // end region

    //region Meeting
    public void AddMeeting(Meeting meeting){
        if (meeting == null) {
            throw new IllegalArgumentException("null meeting");
        }

        mDataBase.child("meetings").child(meeting.ZoomMeetingID).setValue(meeting);
    }

    public void GetMeeting(String MeetingId, Consumer<Meeting> executable){
        mDataBase.child("meetings").child(MeetingId).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    executable.accept((Meeting) task.getResult().getValue());
                }
            }
        });
    }

    public void GetAllMeetings(Consumer<List<Meeting>> executable){
        mDataBase.child("meetings").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    executable.accept((List<Meeting>) task.getResult().getValue());
                }
            }
        });

    }

    public void UpdateMeeting(Meeting meeting){

    }

    public void DeleteMeeting(String MeetingId){
        mDataBase.child("meetings").child(MeetingId).removeValue();
    }

    //end region
}
