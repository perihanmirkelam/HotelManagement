package com.pmirkelam.users;

import com.pmirkelam.BookingProcedure;
import com.pmirkelam.Constants;
import com.pmirkelam.record.RecordFile;

import static com.pmirkelam.Constants.BOOK;
import static com.pmirkelam.Constants.CANCEL_RESERVATION;

public class User implements BookingProcedure {

    public User() {
    }

    @Override
    public void book(int guestId) {
        RecordFile.getInstance().addRecord(guestId, BOOK);
    }

    @Override
    public void cancelReservation(int guestId) {
        RecordFile.getInstance().addRecord(guestId, CANCEL_RESERVATION);
    }

}
