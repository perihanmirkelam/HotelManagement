package com.pmirkelam.users;

import com.pmirkelam.record.Record;
import com.pmirkelam.record.RecordFile;

import java.util.List;

import static com.pmirkelam.Constants.AVAILABLE;
import static com.pmirkelam.Constants.RESERVED;

public class User {

    private int userId;
    private RecordFile recordFile;
    private List<Record> records;

    public User(int userId){
        this.userId = userId;
        recordFile = RecordFile.getInstance();
        records = recordFile.getRecordList();
    }

    public List<Record> getList(){
        return records;
    }

    public void Book(int guestId){

        for(Record recordElement : getList()){

            if(recordElement.getAvailability() == AVAILABLE){
                recordElement.setAvailability(RESERVED);
                recordElement.setGuestId(guestId);
                recordFile.changeRecord(recordElement);
                System.out.println("Booked room: " + recordElement.toString());
                break;
            }
        }
    }

    public void CancelReservation(int guestId){

    }

    public void CheckIn(int guestId, int roomId){

    }

    public void CheckOut(int guestId) {
    }

}
