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
    private int id;
    private String password;
    private int type;

    public Ask() {
    }

    /**
     * Determine user type whether receptionist or guest.
     */
    public void whoAreYou() {

        Scanner reader = new Scanner(System.in);
        System.out.println("Enter 1 for receptionists, 2 for guests: ");
        type = reader.nextInt();

        if (type == RECORDER_TYPE_RECEPTIONIST || type == RECORDER_TYPE_GUEST) {

            System.out.println("Enter ID: ");
            id = reader.nextInt();
            System.out.println("Enter password: ");
            password = reader.next();

            if (LoginAuth.getInstance().checkLogin(type, String.valueOf(id), password)) {

                if (type == RECORDER_TYPE_GUEST) {
                    operationForGuest(id);

                } else {
                    enterGuestId();
                }
            }

        } else {
            System.out.println("Invalid enter, try again!");
            whoAreYou();
        }
        reader.close();

    }

    private void enterGuestId() {

        Scanner reader = new Scanner(System.in);
        System.out.println("Receptionist Login. \nEnter Guest ID or enter 0 for logout back: ");
        int guestId = reader.nextInt();

        if (guestId > 0) {
            operationForReceptionist(guestId);
        } else if (guestId == 0) {
            whoAreYou();
        } else {
            System.out.println("Please try again to enter valid Guest ID. ");
            enterGuestId();
        }
        reader.close();
    }

    private void operationForReceptionist(int guestId) {

        Scanner reader = new Scanner(System.in);
        receptionist = new Receptionist();
        int roomNo;
        System.out.println("Receptionist Login. Guest ID: " + guestId + " \nEnter " + BOOK + " for book a room, " + CANCEL_RESERVATION + " for cancel a reservation," +
                +CHECK_IN + " for check-in a room, " + CHECK_OUT + " for check-out a room, " + BACK + " for back: ");
        int operation = reader.nextInt();
        switch (operation) {

            case BOOK:
                if (RecordFile.getInstance().getFirstAvailableRoomNo() != -1) {
                    receptionist.book(guestId);
                    enterGuestId();
                } else {
                    System.out.println("Sorry. No any available room now. ");
                    operationForReceptionist(guestId);
                }
                break;

            case CANCEL_RESERVATION:
                roomNo = RecordFile.getInstance().getRoomNoOfGuestId(guestId);

                if (roomNo != -1) {
                    receptionist.cancelReservation(guestId);
                    enterGuestId();
                } else {
                    System.out.println("You have not any reservation.");
                    operationForReceptionist(guestId);
                }
                break;

            case CHECK_IN:
                roomNo = RecordFile.getInstance().getRoomNoOfGuestId(guestId);
                if (roomNo != -1 && RecordFile.getInstance().getRecordList().get(roomNo - 1).getAvailability() == RESERVED) {
                    System.out.println("You have already reserved room " + roomNo + ". You have checked in. Enjoy.");
                    receptionist.checkIn(guestId);
                    enterGuestId();

                } else if (roomNo == -1 && (RecordFile.getInstance().getFirstAvailableRoomNo()) != -1) {
                    System.out.println("You do not have any reservation. Checking you in an available room. "
                            + RecordFile.getInstance().getFirstAvailableRoomNo());
                    receptionist.checkIn(guestId);
                    enterGuestId();

                } else {
                    System.out.println("Sorry. No any available room now.");
                    enterGuestId();
                }
                break;

            case CHECK_OUT:

                roomNo = RecordFile.getInstance().getRoomNoOfGuestId(guestId);
                if (roomNo != -1 && RecordFile.getInstance().getRecordList().get(roomNo - 1).getAvailability() == CHECKED_IN) {
                    receptionist.checkOut(guestId);
                    enterGuestId();
                    System.out.println("Check out successful.");

                } else {
                    System.out.println("Invalid enter! There is no any record checked in before of Guest ID: "
                            + guestId + "! Please enter a valid request.");
                    operationForReceptionist(guestId);
                }

                System.out.println("You have not any record on system of guest Id " + guestId
                        + "! Please enter a valid request.");
                operationForReceptionist(guestId);
                break;

            case BACK:
                enterGuestId();
                break;

            default:
                System.out.println("Invalid enter, try again!");
                operationForReceptionist(guestId);
                break;
        }
        reader.close();
    }

    private void operationForGuest(int guestId) {
        guest = new User();
        Scanner reader = new Scanner(System.in);
        System.out.println("Guest Login. Guest ID: " + guestId + " \nEnter 1 for book a room, 2 for cancel a reservation, 0 for back: ");
        int operation = reader.nextInt();
        int roomNo;
        switch (operation) {

            case BOOK:
                if (RecordFile.getInstance().getFirstAvailableRoomNo() != -1) {
                    guest.book(guestId);
                    whoAreYou();
                } else {
                    System.out.println("Sorry. No any available room now. ");
                    operationForGuest(guestId);
                }
                break;

            case CANCEL_RESERVATION:
                roomNo = RecordFile.getInstance().getRoomNoOfGuestId(guestId);
                if (roomNo != -1 && RecordFile.getInstance().getRecordList().get(roomNo - 1).getAvailability() == RESERVED) {
                    guest.cancelReservation(guestId);
                    whoAreYou();
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
