package com.pmirkelam.record;

import static com.pmirkelam.Constants.GUEST;
import static com.pmirkelam.Constants.RECEPTIONIST;
import static com.pmirkelam.Constants.RECORDER_TYPE_RECEPTIONIST;

public class Record {

    private int roomId = -1;
    private int availability = -1;
    private int guestId = -1;

    public Record(int roomId, int availability, int guestId) {
        this.roomId = roomId;
        this.availability = availability;
        this.guestId = guestId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    @Override
    public String toString() {
        return "Room No: " + roomId + ", Availability: " + availability + ", Guest ID: " + guestId;
    }
}
