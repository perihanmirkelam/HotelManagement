package com.pmirkelam;

public interface RecordListener {

    void onBookAdd(int guestId);
    void onCancelReservation(int guestId);
    void onCheckIn(int guestId);
    void onCheckOut(int guestId);

}
