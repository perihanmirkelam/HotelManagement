package com.pmirkelam;

public class Constants {

    public static final String RECEPTIONIST = "Receptionist";
    public static final String GUEST = "Guest";

    public static final int RECORDER_TYPE_RECEPTIONIST = 1;
    public static final int RECORDER_TYPE_GUEST = 2;

    public static final int TOTAL_ROOM_NUMBER = 9;
    public static final int TOTAL_GUEST_NUMBER = 3;
    public static final int TOTAL_RECEPTIONIST_NUMBER = 2;



    public static final String ROOM_FILE_NAME = System.getProperty("user.home") + "/records.csv";
    public static final String LOGIN_GUEST_FILE_NAME = System.getProperty("user.home") + "/guestlogin.csv";
    public static final String LOGIN_RECEPTIONIST_FILE_NAME = System.getProperty("user.home") + "/receptionistlogin.csv";


    public static final int AVAILABLE = 1;
    public static final int RESERVED = 2;
    public static final int CHECKED_IN = 3;
    public static final int NO_ANY_GUEST = -1;


    public static final int BOOK = 1;
    public static final int CANCEL_RESERVATION = 2;
    public static final int CHECK_IN = 3;
    public static final int CHECK_OUT = 4;
    public static final int BACK = 0;


}

