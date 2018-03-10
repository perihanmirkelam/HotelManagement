package com.pmirkelam.users;

import com.pmirkelam.Constants;
import com.pmirkelam.record.RecordFile;

import static com.pmirkelam.Constants.*;

public class Receptionist extends User {

    public Receptionist() {
        super();
    }

    @Override
    public void book(int guestId) {
        super.book(guestId);
        //TODO: do only book for self
    }

    @Override
    public void cancelReservation(int guestId) {
        super.cancelReservation(guestId);
        //TODO: do only book for self
    }

    public void checkIn(int guestId) {
        System.out.println("checkIn");
        //TODO: onceden book kontrol var mı ekle
        RecordFile.getInstance().addRecord(guestId, CHECK_IN);
    }

    public void checkOut(int guestId) {
        System.out.println("checkOut");
        RecordFile.getInstance().addRecord(guestId, CHECK_OUT);
    }

}
