package com.pmirkelam.users;

import com.pmirkelam.record.Record;
import com.pmirkelam.record.RecordFile;

import java.util.List;


public class Guest extends User {

    private int id;

    public Guest(int id) {
        this.id = id;
    }

    @Override
    public void Book(int guestId) {
        System.out.println("Book");
        super.Book(guestId);
    }

    @Override
    public void CancelReservation(int guestId) {
        System.out.println("CancelReservation");
        super.CancelReservation(guestId);
    }

}
