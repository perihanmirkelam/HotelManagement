package com.pmirkelam.users;

import com.pmirkelam.RecordListener;
import com.pmirkelam.record.Record;

import java.util.List;

public class User {

    private int userId;
    private List<Record> records;
    private RecordListener recordListener = null;

    public User() {
        System.out.println("User created");
    }

    public void Book(int guestId) {
        System.out.println("Book");

        if(recordListener != null){
            recordListener.onBookAdd(guestId);
        }

    }

    public void CancelReservation(int guestId) {
        if(recordListener != null){
            recordListener.onCancelReservation(guestId);
        }
    }

    public void CheckIn(int guestId) {
        if(recordListener != null){
            recordListener.onCheckIn(guestId);
        }
    }

    public void CheckOut(int guestId) {
        if(recordListener != null){
            recordListener.onCheckOut(guestId);
        }
    }

    public void setRecordListener(RecordListener recordListener){
        this.recordListener = recordListener;
    }

}
