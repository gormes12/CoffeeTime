package com.example.CoffeeTime.Firebase;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.CoffeeTime.model.Meeting;
import com.example.CoffeeTime.model.Organization;
import com.example.CoffeeTime.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

import static android.content.ContentValues.TAG;

public class FireStoreHelper {
    private static DatabaseReference mDataBase = FirebaseDatabase.getInstance("https://coffeetime-1e43c-default-rtdb.firebaseio.com/").getReference();

    public static void addListener(){
        mDataBase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value = snapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });;
    }

    // region User
    public static void AddUser(User user) {
        addListener();
        if (user == null) {
            throw new IllegalArgumentException("null user");
        }
        if(user.getOrganizations() != null){
           // AddOrganization(user.organization);
        }

        mDataBase.child("users").child(user.getId()).setValue(user).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
               Log.println(Log.ERROR, "tag", e.getMessage());
            }
        });
    }

    public static void GetUser(String UserId, Consumer<User> executable){
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

    public static void UpdateUser(User user){
        String newName = user.getName();
        String newOrganizations = "5";
        HashMap hashMap= new HashMap();
        hashMap.put("NickName", newName);
        hashMap.put("organizations", newOrganizations);

        mDataBase.child("users").child(user.getId()).updateChildren(hashMap);
    }

    public static void DeleteUser(String UserId){
        mDataBase.child("users").child(UserId).removeValue();
    }
    // end region

    // region Organization
    public static void AddOrganization(Organization organization){
        if (organization == null) {
            throw new IllegalArgumentException("null organization");
        }

         mDataBase.child("organizations").push().setValue(organization).addOnFailureListener(new OnFailureListener() {
             @Override
             public void onFailure(@NonNull Exception e) {
                 Log.println(Log.ERROR, "tag", e.getMessage());
             }
         });
    }

    public static void GetOrganization(String OrganizationId, Consumer<Organization> executable){
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

    public static void GetAllOrganizations(Consumer<List<Organization>> executable){
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

    public static void UpdateOrganization(Organization organization){
        String newName = organization.getName();
        int newNumOfMembers = organization.getMembers();
        HashMap hashMap= new HashMap();
        hashMap.put("Name", newName);
        hashMap.put("NumOfMembers", newNumOfMembers);

        mDataBase.child("organizations").child(organization.getId()).updateChildren(hashMap);
    }

    public static void DeleteOrganization(String OrganizationId){
        mDataBase.child("organizations").child(OrganizationId).removeValue();
    }
    // end region

    //region Meeting
    public static void AddMeeting(Meeting meeting){
        if (meeting == null) {
            throw new IllegalArgumentException("null meeting");
        }

        mDataBase.child("meetings").child(meeting.getMeetingId()).setValue(meeting);
    }

    public static void GetMeeting(String MeetingId, Consumer<Meeting> executable){
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

    public static void GetAllMeetings(Consumer<List<Meeting>> executable){
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

    public static void UpdateMeeting(Meeting meeting){
        String newName = meeting.getName();
        int newNumOfMembers = meeting.getMembers();
        HashMap hashMap= new HashMap();
        hashMap.put("MeetingName", newName);
        hashMap.put("CurrentNumOfParticipants", newNumOfMembers);

        mDataBase.child("meetings").child(meeting.getMeetingId()).updateChildren(hashMap);
    }

    public static void DeleteMeeting(String MeetingId){
        mDataBase.child("meetings").child(MeetingId).removeValue();
    }

    //end region
}
