package com.pmirkelam.users;

import com.pmirkelam.record.Record;
import com.pmirkelam.record.RecordFile;

import java.util.List;


public class Guest extends User {

    private int guestId;
    private String guestName;
    private int id;
    private RecordFile recordFile;
    private List<Record> records;

    public Guest(int id) {
        super(id);
        this.guestId = id;
        recordFile = RecordFile.getInstance();
        records = recordFile.getRecordList();
    }

    @Override
    public List<Record> getList() {
        return super.getList();
    }

    @Override
    public void Book(int guestId) {
        super.Book(guestId);
    }

    @Override
    public void CancelReservation(int guestId) {
        super.CancelReservation(guestId);
    }

    public int getUserId() {
        return guestId;
    }
}
