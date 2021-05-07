package com.example.CoffeeTime.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.CoffeeTime.MainActivity;
import com.example.CoffeeTime.R;

import us.zoom.sdk.JoinMeetingOptions;
import us.zoom.sdk.JoinMeetingParams;
import us.zoom.sdk.MeetingService;
import us.zoom.sdk.StartMeetingOptions;
import us.zoom.sdk.ZoomApiError;
import us.zoom.sdk.ZoomAuthenticationError;
import us.zoom.sdk.ZoomSDK;
import us.zoom.sdk.ZoomSDKAuthenticationListener;
import us.zoom.sdk.ZoomSDKInitParams;
import us.zoom.sdk.ZoomSDKInitializeListener;

public class MatchActivity extends AppCompatActivity {

    String orgName = "BetterWork";

    private ZoomSDKAuthenticationListener authListener = new ZoomSDKAuthenticationListener() {
        /**
         * This callback is invoked when a result from the SDK's request to the auth server is
         * received.
         */
        @Override
        public void onZoomSDKLoginResult(long result) {
            if (result == ZoomAuthenticationError.ZOOM_AUTH_ERROR_SUCCESS) {
                // Once we verify that the request was successful, we may start the meeting
                startMeeting(MatchActivity.this);
            }
        }

        @Override
        public void onZoomSDKLogoutResult(long l) {}@Override
        public void onZoomIdentityExpired() {}@Override
        public void onZoomAuthIdentityExpired() {}
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match_layout);

        initializeSdk(this);

        TextView TextOrganizationName = (TextView) findViewById(R.id.TextOrganizationName);
        TextOrganizationName.setText(orgName);

        Button ButtonEditUser = (Button) findViewById(R.id.buttonEditUser2);
        ButtonEditUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MatchActivity.this, EditProfileActivity.class));
            }
        });

        Button buttonAbout = (Button) findViewById(R.id.buttonAbout3);
        buttonAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MatchActivity.this, AboutUsActivity.class));
            }
        });

        Button buttonSignOut = (Button) findViewById(R.id.buttonSignOut3);
        buttonSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MatchActivity.this, MainActivity.class));
            }
        });


        Button ButtonWC = (Button) findViewById(R.id.ButtonWC);
        ButtonWC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: if there is an available room use join meeting
//                joinMeeting(MatchActivity.this, meetingNumber, password);
                joinMeeting(MatchActivity.this, "76934252351", "7hGpFt", "Guest");
                //TODO: else
//                 if (ZoomSDK.getInstance().isLoggedIn()) {
//                                startMeeting(MatchActivity.this);
//                            } else {
//                                createLoginDialog();
//                            }
            }
        });
    }

    public void initializeSdk(Context context) {
        ZoomSDK sdk = ZoomSDK.getInstance();
        // TODO: For the purpose of this demo app, we are storing the credentials in the client app itself. However, you should not use hard-coded values for your key/secret in your app in production.
        ZoomSDKInitParams params = new ZoomSDKInitParams();
        params.appKey = "5bFE6JAMpLLDGSd5Osifi9jB02qSAXXoLTjG"; // TODO: Retrieve your SDK key and enter it here
        params.appSecret = "26vXhNws3aVCoJUkj05yvrGc8rfgSE9jRAB5"; // TODO: Retrieve your SDK secret and enter it here
        params.domain = "zoom.us";
        params.enableLog = true;
        // TODO: Add functionality to this listener (e.g. logs for debugging)
        ZoomSDKInitializeListener listener = new ZoomSDKInitializeListener() {
            /**
             * @param errorCode {@link us.zoom.sdk.ZoomError#ZOOM_ERROR_SUCCESS} if the SDK has been initialized successfully.
             */
            @Override
            public void onZoomSDKInitializeResult(int errorCode, int internalErrorCode) { }

            @Override
            public void onZoomAuthIdentityExpired() { }
        };
        sdk.initialize(context, listener, params);
    }

    //1. Write the joinMeeting function.
    private void joinMeeting(Context context, String meetingNumber, String password, String userName) {
        MeetingService meetingService = ZoomSDK.getInstance().getMeetingService();
        JoinMeetingOptions options = new JoinMeetingOptions();
        JoinMeetingParams params = new JoinMeetingParams();
        params.displayName = userName; // TODO: Enter your name
        params.meetingNo = meetingNumber;
        params.password = password;
        meetingService.joinMeetingWithParams(context, params, options);
    }



    // 1. Write the login function
    private void login(String username, String password) {
        int result = ZoomSDK.getInstance().loginWithZoom(username, password);
        if (result == ZoomApiError.ZOOM_API_ERROR_SUCCESS) {

            //inMeetingService.changeName("New Name", userId);

            //InMeetingService service = ZoomSDK.getInstance().getInMeetingService();
            //MobileRTCSDKError result2 = service.changeName("New name", userID); // Pass in the name you are trying to use as a String and the user ID of the user whose name you are changing
            //if (result2.equals(MobileRTCSDKError.SDKERR_SUCCESS)) {
            // You have updated the name of the selected user
            //}

            // 2. After request is executed, listen for the authentication result prior to starting a meeting
            ZoomSDK.getInstance().addAuthenticationListener(authListener);
        }
    }
    // 3. Write the startMeeting function
    private void startMeeting(Context context) {
        ZoomSDK sdk = ZoomSDK.getInstance();
        if (sdk.isLoggedIn()) {
            MeetingService meetingService = sdk.getMeetingService();
            StartMeetingOptions options = new StartMeetingOptions();
            meetingService.startInstantMeeting(context, options);
        }
    }


//    // 1. Create a dialog where a participant can enter the meeting information to join a meeting.
//    private void createJoinMeetingDialog() {
//        new AlertDialog.Builder(this).setView(R.layout.join_meeting).setPositiveButton("Join", new DialogInterface.OnClickListener() {@Override
//        public void onClick(DialogInterface dialogInterface, int i) {
//            AlertDialog dialog = (AlertDialog) dialogInterface;
//            TextInputEditText numberInput = dialog.findViewById(R.id.meeting_no_input);
//            TextInputEditText passwordInput = dialog.findViewById(R.id.password_input);
//            if (numberInput != null && numberInput.getText() != null && passwordInput != null && passwordInput.getText() != null) {
//                String meetingNumber = numberInput.getText().toString();
//                String password = passwordInput.getText().toString();
//                if (meetingNumber.trim().length() > 0 && password.trim().length() > 0) {
//                    joinMeeting(MainActivity.this, meetingNumber, password);
//                }
//            }
//        }
//        }).show();
//    }

    // 2. Create a dialog where a host can enter Zoom email and password to login and start an instant meeting.
    private void createLoginDialog() {
        //////////////////////////////////
        login("gideonsh@mta.ac.il", "Gideonsh93");
        //JoinMeetingParams params = new JoinMeetingParams();
        //params.displayName = "Ilay_Display"; // TODO: Enter your name
        /////////////////////////////////
        //new AlertDialog.Builder(this).setView(R.layout.activity_login).setPositiveButton("Log in", new DialogInterface.OnClickListener() {@Override
        //public void onClick(DialogInterface dialogInterface, int i) {
        //    AlertDialog dialog = (AlertDialog) dialogInterface;
        //    TextInputEditText emailInput = dialog.findViewById(R.id.email_input);
        //    TextInputEditText passwordInput = dialog.findViewById(R.id.pw_input);
        //    if (emailInput != null && emailInput.getText() != null && passwordInput != null && passwordInput.getText() != null) {
        //        String email = emailInput.getText().toString();
        //        String password = passwordInput.getText().toString();
        //        if (email.trim().length() > 0 && password.trim().length() > 0) {
        //            login(email, password);
        //        }
        //    }
        //}
        //}).show();
    }


//    //1. Write initViews method to handle onClick events for the Join Meeting and Login & Start Meeting buttons.
//    private void initViews() {
//        findViewById(R.id.join_button).setOnClickListener(new View.OnClickListener() {@Override
//        public void onClick(View view) {
//            createJoinMeetingDialog();
//        }
//        });
//
//        findViewById(R.id.login_button).setOnClickListener(new View.OnClickListener() {@Override
//        public void onClick(View view) {
//            // 2.  If a user is logged in, start the instant meeting, else prompt the user to login.
//            if (ZoomSDK.getInstance().isLoggedIn()) {
//                startMeeting(MainActivity.this);
//            } else {
//                createLoginDialog();
//            }
//        }
//        });
//    }
}
//----------------------------------------


//import android.content.Context;
//import android.content.DialogInterface;
//import android.os.Bundle;
//import android.view.View;
//
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.example.CoffeeTime.R;
//import com.google.android.material.textfield.TextInputEditText;
//
//import us.zoom.sdk.JoinMeetingOptions;
//import us.zoom.sdk.JoinMeetingParams;
//import us.zoom.sdk.MeetingService;
//import us.zoom.sdk.StartMeetingOptions;
//import us.zoom.sdk.ZoomApiError;
//import us.zoom.sdk.ZoomAuthenticationError;
//import us.zoom.sdk.ZoomSDK;
//import us.zoom.sdk.ZoomSDKAuthenticationListener;
//import us.zoom.sdk.ZoomSDKInitParams;
//import us.zoom.sdk.ZoomSDKInitializeListener;
//
////import android.widget.Toast;
////import us.zoom.sdk.InMeetingService;
////import us.zoom.sdk.ZoomSDKMeetingActionController;
//
//public class MatchActivity extends AppCompatActivity {
//    private ZoomSDKAuthenticationListener authListener = new ZoomSDKAuthenticationListener() {
//        /**
//         * This callback is invoked when a result from the SDK's request to the auth server is
//         * received.
//         */
//        @Override
//        public void onZoomSDKLoginResult(long result) {
//            if (result == ZoomAuthenticationError.ZOOM_AUTH_ERROR_SUCCESS) {
//                // Once we verify that the request was successful, we may start the meeting
//                startMeeting(MatchActivity.this);
//            }
//        }
//
//        @Override
//        public void onZoomSDKLogoutResult(long l) {}@Override
//        public void onZoomIdentityExpired() {}@Override
//        public void onZoomAuthIdentityExpired() {}
//    };
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main_2);
//
//        initializeSdk(this);
//        initViews();
//    }
//
//
//    public void initializeSdk(Context context) {
//        ZoomSDK sdk = ZoomSDK.getInstance();
//        // TODO: For the purpose of this demo app, we are storing the credentials in the client app itself. However, you should not use hard-coded values for your key/secret in your app in production.
//        ZoomSDKInitParams params = new ZoomSDKInitParams();
//        params.appKey = "5bFE6JAMpLLDGSd5Osifi9jB02qSAXXoLTjG"; // TODO: Retrieve your SDK key and enter it here
//        params.appSecret = "26vXhNws3aVCoJUkj05yvrGc8rfgSE9jRAB5"; // TODO: Retrieve your SDK secret and enter it here
//        params.domain = "zoom.us";
//        params.enableLog = true;
//        // TODO: Add functionality to this listener (e.g. logs for debugging)
//        ZoomSDKInitializeListener listener = new ZoomSDKInitializeListener() {
//            /**
//             * @param errorCode {@link us.zoom.sdk.ZoomError#ZOOM_ERROR_SUCCESS} if the SDK has been initialized successfully.
//             */
//            @Override
//            public void onZoomSDKInitializeResult(int errorCode, int internalErrorCode) { }
//
//            @Override
//            public void onZoomAuthIdentityExpired() { }
//        };
//        sdk.initialize(context, listener, params);
//
//    }
//
//
//    //1. Write the joinMeeting function.
//    private void joinMeeting(Context context, String meetingNumber, String password) {
//        MeetingService meetingService = ZoomSDK.getInstance().getMeetingService();
//        JoinMeetingOptions options = new JoinMeetingOptions();
//        JoinMeetingParams params = new JoinMeetingParams();
//        params.displayName = "Gideon_Display"; // TODO: Enter your name
//        params.meetingNo = meetingNumber;
//        params.password = password;
//        meetingService.joinMeetingWithParams(context, params, options);
//    }
//
//
//
//    // 1. Write the login function
//    private void login(String username, String password) {
//        int result = ZoomSDK.getInstance().loginWithZoom(username, password);
//        if (result == ZoomApiError.ZOOM_API_ERROR_SUCCESS) {
//
//            //inMeetingService.changeName("New Name", userId);
//
//            //InMeetingService service = ZoomSDK.getInstance().getInMeetingService();
//            //MobileRTCSDKError result2 = service.changeName("New name", userID); // Pass in the name you are trying to use as a String and the user ID of the user whose name you are changing
//            //if (result2.equals(MobileRTCSDKError.SDKERR_SUCCESS)) {
//            // You have updated the name of the selected user
//            //}
//
//            // 2. After request is executed, listen for the authentication result prior to starting a meeting
//            ZoomSDK.getInstance().addAuthenticationListener(authListener);
//        }
//    }
//    // 3. Write the startMeeting function
//    private void startMeeting(Context context) {
//        ZoomSDK sdk = ZoomSDK.getInstance();
//        if (sdk.isLoggedIn()) {
//            MeetingService meetingService = sdk.getMeetingService();
//            StartMeetingOptions options = new StartMeetingOptions();
//            meetingService.startInstantMeeting(context, options);
//        }
//    }
//
//
//    // 1. Create a dialog where a participant can enter the meeting information to join a meeting.
//    private void createJoinMeetingDialog() {
//        new AlertDialog.Builder(this).setView(R.layout.join_meeting).setPositiveButton("Join", new DialogInterface.OnClickListener() {@Override
//        public void onClick(DialogInterface dialogInterface, int i) {
//            AlertDialog dialog = (AlertDialog) dialogInterface;
//            TextInputEditText numberInput = dialog.findViewById(R.id.meeting_no_input);
//            TextInputEditText passwordInput = dialog.findViewById(R.id.password_input);
//            if (numberInput != null && numberInput.getText() != null && passwordInput != null && passwordInput.getText() != null) {
//                String meetingNumber = numberInput.getText().toString();
//                String password = passwordInput.getText().toString();
//                if (meetingNumber.trim().length() > 0 && password.trim().length() > 0) {
//                    joinMeeting(MatchActivity.this, meetingNumber, password);
//                }
//            }
//        }
//        }).show();
//    }
//
//    // 2. Create a dialog where a host can enter Zoom email and password to login and start an instant meeting.
//    private void createLoginDialog() {
//        //////////////////////////////////
//        login("gideonsh@mta.ac.il", "Gideonsh93");
//        //JoinMeetingParams params = new JoinMeetingParams();
//        //params.displayName = "Ilay_Display"; // TODO: Enter your name
//        /////////////////////////////////
//        //new AlertDialog.Builder(this).setView(R.layout.activity_login).setPositiveButton("Log in", new DialogInterface.OnClickListener() {@Override
//        //public void onClick(DialogInterface dialogInterface, int i) {
//        //    AlertDialog dialog = (AlertDialog) dialogInterface;
//        //    TextInputEditText emailInput = dialog.findViewById(R.id.email_input);
//        //    TextInputEditText passwordInput = dialog.findViewById(R.id.pw_input);
//        //    if (emailInput != null && emailInput.getText() != null && passwordInput != null && passwordInput.getText() != null) {
//        //        String email = emailInput.getText().toString();
//        //        String password = passwordInput.getText().toString();
//        //        if (email.trim().length() > 0 && password.trim().length() > 0) {
//        //            login(email, password);
//        //        }
//        //    }
//        //}
//        //}).show();
//    }
//
//
//    //1. Write initViews method to handle onClick events for the Join Meeting and Login & Start Meeting buttons.
//    private void initViews() {
//        findViewById(R.id.join_button).setOnClickListener(new View.OnClickListener() {@Override
//        public void onClick(View view) {
//            createJoinMeetingDialog();
//        }
//        });
//
//        findViewById(R.id.login_button).setOnClickListener(new View.OnClickListener() {@Override
//        public void onClick(View view) {
//            // 2.  If a user is logged in, start the instant meeting, else prompt the user to login.
//            if (ZoomSDK.getInstance().isLoggedIn()) {
//                startMeeting(MatchActivity.this);
//            } else {
//                createLoginDialog();
//            }
//        }
//        });
//    }
////    /join meeting work/
//
//
////            /start meeting with non-user/
//
//
//
//
//}
//
//// line 194
