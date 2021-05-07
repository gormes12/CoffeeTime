package com.example.CoffeeTime.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.CoffeeTime.Firebase.FireStoreHelper;
import com.example.CoffeeTime.R;
import com.example.CoffeeTime.model.Organization;
import com.example.CoffeeTime.model.User;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class OrganizationSelectActivity extends AppCompatActivity {

    String selectedOrg = null;
    User currUser;
    List<Organization> myOrganizationList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.organizationselect_layout);

        executeActivity();
//        Intent i = getIntent();
//        currUser = (User)i.getSerializableExtra("userClass");
//        FireStoreHelper.GetAllOrganizations(OrganizationSelectActivity.this::executeActivity);


    }

    public void executeActivity(){
        Spinner spinner = findViewById(R.id.InputOrganization);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("BetterWork");
        arrayList.add("MTAHack");
        arrayList.add("INNOVID");
        arrayList.add("Wix");
        arrayList.add("duda");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,                         android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedOrg = parent.getItemAtPosition(position).toString();
//                Toast.makeText(parent.getContext(), tutorialsName, Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });

        Button ButtonSetOrganization = (Button) findViewById(R.id.ButtonSetOrganization);
        ButtonSetOrganization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrganizationSelectActivity.this, UserUpdateActivity.class));
            }
        });
    }

    public void executeActivity(List<Organization> organizationList){
        Spinner spinner = findViewById(R.id.InputOrganization);
        ArrayList<String> arrayList = new ArrayList<>();
        myOrganizationList = new LinkedList<>();
        Organization myOrg = new Organization();
        myOrg.setId("1");
        myOrg.setMembers(1);
        myOrg.setName("BetterWork");

        myOrganizationList.add(myOrg);
        for (Organization org: myOrganizationList) {
            arrayList.add(org.getName());
        }
//        arrayList.add("ANDROID");
//        arrayList.add("C Language");
//        arrayList.add("CPP Language");
//        arrayList.add("Go Language");
//        arrayList.add("AVN SYSTEMS");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,                         android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedOrg = parent.getItemAtPosition(position).toString();
//                Toast.makeText(parent.getContext(), tutorialsName, Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });

        Button ButtonSetOrganization = (Button) findViewById(R.id.ButtonSetOrganization);
        ButtonSetOrganization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Organization org: myOrganizationList){
                    if(org.getName().equals(selectedOrg)){
                        currUser.setOrganizations(org.getId());
                        FireStoreHelper.AddUser(currUser);
                    }
                }
            }
        });
    }
}
