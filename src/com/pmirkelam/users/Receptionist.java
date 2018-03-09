package com.pmirkelam.users;

public class Receptionist extends User {

    private int id;

    public Receptionist(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public void Book(int guestId) {
        System.out.println("Book");

        super.Book(guestId);
    }

    @Override
    public void CancelReservation(int guestId) {

        System.out.println("CancelReservation");
        super.CancelReservation(guestId);
    }

    @Override
    public void CheckIn(int guestId) {
        super.CheckIn(guestId);
    }

    @Override
    public void CheckOut(int guestId) {
        super.CheckOut(guestId);
    }

}
