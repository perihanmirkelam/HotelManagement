package com.pmirkelam;

import com.pmirkelam.record.Record;
import com.pmirkelam.record.RecordFile;
import com.pmirkelam.users.Guest;
import com.pmirkelam.users.Receptionist;
import com.pmirkelam.users.User;

import java.util.List;
import java.util.Scanner;

import static com.pmirkelam.Constants.*;

import javax.jws.soap.SOAPBinding;
import java.util.Scanner;

import static com.pmirkelam.Constants.RECORDER_TYPE_GUEST;
import static com.pmirkelam.Constants.RECORDER_TYPE_RECEPTIONIST;

public class Ask {

    private Receptionist receptionist;
    private Guest guest;

    public Ask() {

    }

    /**
     * Determine user type whether receptionist or guest.
     */
    public void startAsk(){

        Scanner reader = new Scanner(System.in);
        System.out.println("Enter 1 for receptionists, 2 for guests: ");
        int type = reader.nextInt();
        System.out.println("Enter Guest ID: ");
        int guestId = reader.nextInt();

        switch (type) {

            case RECORDER_TYPE_RECEPTIONIST:
                operationForReceptionist(guestId);
                break;

            case RECORDER_TYPE_GUEST:
                operationForGuest(guestId);
                break;

            default:
                System.out.println("Wrong enter, try again!");
                startAsk();
                break;
        }
        reader.close();
    }

    private void operationForReceptionist(int guestId) {

        Scanner reader = new Scanner(System.in);
        User receptionist = (User) new Receptionist(guestId);
        User clientGuest = (User) new Guest(guestId);
        System.out.println("Enter 1 for book a room, 2 for cancel a reservation, 3 for check-in a room, "
                + "4 for check-out a room, 0 for back: ");
        int operation = reader.nextInt();
        switch (operation) {

            case BOOK:
                receptionist.Book(guestId);
                break;

            case CANCEL_RESERVATION:
                receptionist.CancelReservation(guestId);
                break;

            case CHECKED_IN:
                System.out.println("If you do not have any reservation enter 1, have a reservation enter 2;");
                if (reader.nextInt() == 1) {
                    int roomId = findReservedRoom(guestId);
                    if (roomId != -1) {
                        receptionist.CheckIn(guestId, roomId);
                    } else {
                        reader.close();
                        System.out.println("You do not have any reservation. Checking in you an available room...");
                        receptionist.CheckIn(guestId, RecordFile.getInstance().getFirstAvailableRoom());
                    }
                }
                break;

            case CHECK_OUT:
                receptionist.CheckOut(guestId);
                break;

            case BACK:
                startAsk();
                break;

            default:
                System.out.println("Wrong enter, try again!");
                reader.close();
                operationForReceptionist(guestId);
                break;
        }
        reader.close();
    }

    private void operationForGuest(int guestId) {
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter 1 for book a room, 2 for cancel a reservation, 0 for back: ");
        int operation = reader.nextInt();
        switch (operation) {

            case BOOK:
                receptionist.Book(guestId);
                break;

            case CANCEL_RESERVATION:
                receptionist.CancelReservation(guestId);
                break;

            case BACK:
                startAsk();
                break;

            default:
                System.out.println("Wrong enter, try again!");
                reader.close();
                operationForGuest(guestId);
                break;
        }
        reader.close();

    }

    private void askNameAndPassword() {

    }
    private void loginForReceptionist(){

    }

    private int findReservedRoom(int guestId) {

        List<Record> records = RecordFile.getInstance().getRecordList();
        int index = -1;

        for (Record record : records) {
            if (record.getGuestId() == guestId && record.getAvailability() == RESERVED) {
                index = record.getRoomId();
                break;
            }
        }
        return index;
    }
}
