package com.pmirkelam;

import com.pmirkelam.record.RecordFile;
import com.pmirkelam.users.Guest;
import com.pmirkelam.users.Receptionist;
import com.pmirkelam.users.User;

import java.util.Scanner;

import static com.pmirkelam.Constants.*;

public class Main {

    private String fileName = FILE_NAME;

    private static RecordFile recordFile;
    private Receptionist receptionist;
    private Guest guest;

    public static void main(String[] args) {
        // write your code here
        System.out.println("Total Room Number: " + TOTAL_ROOM_NUMBER);
        recordFile = RecordFile.getInstance();
        Ask ask = new Ask();
        ask.startAsk();
    }

}
