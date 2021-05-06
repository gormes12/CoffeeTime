package com.example.coffeetime.model;

import java.util.Objects;

public class Meeting {
    static final int MAX_PARTICIPANTS = 5;

    public String ZoomMeetingID;
    public String Passcode;
    public String MeetingName;
    public int CurrentNumOfParticipants;
    public int MaxNumOfParticipants = MAX_PARTICIPANTS;

    public void setCurrentNumOfParticipants(int currentNumOfParticipants) {
        CurrentNumOfParticipants = currentNumOfParticipants;
    }

    public void setMeetingName(String meetingName) {
        MeetingName = meetingName;
    }

    public void setPasscode(String passcode) {
        Passcode = passcode;
    }

    public void setZoomMeetingID(String zoomMeetingID) {
        ZoomMeetingID = zoomMeetingID;
    }

    public String getMeetingName() {
        return MeetingName;
    }

    public int getCurrentNumOfParticipants() {
        return CurrentNumOfParticipants;
    }

    public String getPasscode() {
        return Passcode;
    }

    public String getZoomMeetingID() {
        return ZoomMeetingID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meeting meeting = (Meeting) o;
        return Objects.equals(ZoomMeetingID, meeting.ZoomMeetingID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ZoomMeetingID);
    }
}