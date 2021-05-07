package com.example.CoffeeTime.model;

import java.util.Objects;

public class Meeting {
    static final int MAX_PARTICIPANTS = 5;
    private String meetingId;
    private String passcode;
    private String name;
    private int members;
    private int maxMembers = MAX_PARTICIPANTS;

    public void setMembers(int members) {
        this.members = members;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPasscode(String passcode) {
        this.passcode = passcode;
    }

    public void setMeetingId(String meetingId) {
        this.meetingId = meetingId;
    }

    public String getName() {
        return name;
    }

    public int getMembers() {
        return members;
    }

    public String getPasscode() {
        return passcode;
    }

    public String getMeetingId() {
        return meetingId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meeting meeting = (Meeting) o;
        return Objects.equals(meetingId, meeting.meetingId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(meetingId);
    }
}
