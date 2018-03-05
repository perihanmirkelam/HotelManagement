package com.pmirkelam.users;

import com.pmirkelam.record.Record;
import com.pmirkelam.record.RecordFile;

import java.util.List;

import static com.pmirkelam.Constants.AVAILABLE;
import static com.pmirkelam.Constants.RESERVED;

public class Receptionist extends User {

    private int id;
    private RecordFile recordFile;
    private List<Record> records;

    public Receptionist(int id) {
        super(id);
        this.id = id;
        recordFile = RecordFile.getInstance();
        records = getList();
    }

    public int getId() {
        return id;
    }

    @Override
    public List<Record> getList() {
        return super.getList();
    }

    @Override
    public void Book(int guestId) {
        super.Book(guestId);
        for(Record recordElement : getList()){
            if(recordElement.getAvailability() == AVAILABLE){
                recordElement.setAvailability(RESERVED);
                recordElement.setGuestId(guestId);
                System.out.println("Booked room: " + recordElement.toString());
                break;
            }
            recordFile.addRecord(recordElement);
        }
    }

    @Override
    public void CancelReservation(int guestId) {
        super.CancelReservation(guestId);
    }

    @Override
    public void CheckIn(int guestId, int roomId) {
        super.CheckIn(guestId, roomId);
    }

    @Override
    public void CheckOut(int guestId) {
        super.CheckOut(guestId);
    }

}