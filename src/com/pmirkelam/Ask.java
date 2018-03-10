package com.pmirkelam;

import com.pmirkelam.record.RecordFile;
import com.pmirkelam.users.Receptionist;
import com.pmirkelam.users.User;

import java.util.Scanner;

import static com.pmirkelam.Constants.*;

import static com.pmirkelam.Constants.RECORDER_TYPE_GUEST;
import static com.pmirkelam.Constants.RECORDER_TYPE_RECEPTIONIST;

public class Ask {

    private Receptionist receptionist;
    private User guest;

    public Ask() {
    }

    /**
     * Determine user type whether receptionist or guest.
     */
    public void whoAreYou() {

        Scanner reader = new Scanner(System.in);
        System.out.println("Enter 1 for receptionists, 2 for guests: ");
        int type = reader.nextInt();

        if ((type == RECORDER_TYPE_RECEPTIONIST || type == RECORDER_TYPE_GUEST) && isValidLogin(type)) {
            enterGuestId(type);

        } else {
            System.out.println("Invalid enter, try again!");
            reader.close();
            whoAreYou();
        }

}

    private void enterGuestId(int userType) {

        Scanner reader = new Scanner(System.in);
        System.out.println("Enter Guest ID: ");
        int guestId = reader.nextInt();


        switch (userType) {

            case RECORDER_TYPE_RECEPTIONIST:
                operationForReceptionist(guestId);
                break;

            case RECORDER_TYPE_GUEST:
                operationForGuest(guestId);
                break;
        }
        reader.close();
    }

    private void operationForReceptionist(int guestId) {

        Scanner reader = new Scanner(System.in);
        receptionist = new Receptionist();
        int roomId;
        System.out.println("Enter " + BOOK + " for book a room, " + CANCEL_RESERVATION + " for cancel a reservation," +
                +CHECK_IN + " for check-in a room, " + CHECK_OUT + " for check-out a room, " + BACK + " for back: ");
        int operation = reader.nextInt();
        switch (operation) {

            case BOOK:
                if (RecordFile.getInstance().getFirstAvailableRoom() != -1) {
                    receptionist.book(guestId);
                    enterGuestId(RECORDER_TYPE_RECEPTIONIST);
                } else {
                    System.out.println("Sorry. No any available room now. ");
                    reader.close();
                    operationForReceptionist(guestId);
                }
                break;

            case CANCEL_RESERVATION:
                if (RecordFile.getInstance().getRoomOfGuestId(guestId) != -1) {
                    receptionist.cancelReservation(guestId);
                    enterGuestId(RECORDER_TYPE_RECEPTIONIST);
                } else {
                    System.out.println("You have not any reservation.");
                    reader.close();
                    operationForReceptionist(guestId);
                }
                break;

            case CHECKED_IN:
                roomId = RecordFile.getInstance().getRoomOfGuestId(guestId);

                if (roomId != -1 && RecordFile.getInstance().getRecordList().get(roomId).getAvailability() == RESERVED) {
                    System.out.println("You have already reserved room " + roomId + ". You have checked in. Enjoy.");
                    receptionist.checkIn(guestId);
                    enterGuestId(RECORDER_TYPE_RECEPTIONIST);
                } else if (roomId == -1 && (RecordFile.getInstance().getFirstAvailableRoom()) != -1) {
                    System.out.println("You do not have any reservation. Checking you in an available room. "
                            + RecordFile.getInstance().getFirstAvailableRoom());
                    receptionist.checkIn(guestId);
                    enterGuestId(RECORDER_TYPE_RECEPTIONIST);
                } else {
                    System.out.println("Sorry. No any available room now.");
                }
                break;

            case CHECK_OUT:
                roomId = RecordFile.getInstance().getRoomOfGuestId(guestId);

                if (roomId != -1 && RecordFile.getInstance().getRecordList().get(roomId).getAvailability() == CHECK_IN) {
                    receptionist.checkOut(guestId);
                    enterGuestId(RECORDER_TYPE_RECEPTIONIST);
                    System.out.println("Check out successful.");


                } else {
                    System.out.println("Invalid enter! There is no any record checked in before of Guest ID: "
                            + guestId + "! Please enter a valid request.");
                    reader.close();
                    operationForReceptionist(guestId);
                }

                System.out.println("You have not any record on system of guest Id " + guestId
                        + "! Please enter a valid request.");
                reader.close();
                operationForReceptionist(guestId);
                break;

            case BACK:
                whoAreYou();
                break;

            default:
                System.out.println("Invalid enter, try again!");
                reader.close();
                operationForReceptionist(guestId);
                break;
        }
        reader.close();
    }

    private void operationForGuest(int guestId) {
        guest = new User();
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter 1 for book a room, 2 for cancel a reservation, 0 for back: ");
        int operation = reader.nextInt();
        switch (operation) {

            case BOOK:
                if (RecordFile.getInstance().getFirstAvailableRoom() != -1) {
                    guest.book(guestId);
                    enterGuestId(RECORDER_TYPE_GUEST);
                } else {
                    System.out.println("Sorry. No any available room now. ");
                    operationForGuest(guestId);
                }
                break;

            case CANCEL_RESERVATION:
                if (RecordFile.getInstance().getRoomOfGuestId(guestId) != -1) {
                    guest.cancelReservation(guestId);
                    enterGuestId(RECORDER_TYPE_GUEST);
                } else {
                    System.out.println("You have not any reservation.");
                    operationForGuest(guestId);
                }
                break;

            case BACK:
                whoAreYou();
                break;

            default:
                System.out.println("Invalid enter, try again!");
                reader.close();
                operationForGuest(guestId);
                break;
        }
        reader.close();
    }

    private boolean isValidLogin(int type) {

        //TODO: user auth
        return true;
    }


}
