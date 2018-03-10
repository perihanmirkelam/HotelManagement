package com.pmirkelam.record;

import static com.pmirkelam.Constants.*;

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
        String availabilityString = ((availability == AVAILABLE) ? "1 (Available) "
                : (availability == RESERVED ? "2 (Reserved)  " :   "3 (Checked In)"));
        return "Room No: " + roomId + ", Availability: "
                + availabilityString + ", Guest ID: " + guestId;
    }
}
