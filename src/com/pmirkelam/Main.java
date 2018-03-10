package com.pmirkelam;

import com.pmirkelam.record.RecordFile;

import static com.pmirkelam.Constants.*;

public class Main {

    public static void main(String[] args) {
        // write your code here
        System.out.println("Total Room Number: " + TOTAL_ROOM_NUMBER);
        RecordFile.getInstance();
        Ask ask = new Ask();
        ask.whoAreYou();
    }

}
