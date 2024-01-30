package model.user;

import model.Room;

import java.util.ArrayList;

public class Guest extends User {
    private final int guestId;
    private double balance;
    private Room reservedRoom;
    private static int lastGuestId;

    static{
        lastGuestId = 0;
    }

    {
        balance = 0;
       reservedRoom = null;
    }

    public Guest(String username, String password){
        super(username, password);
        this.guestId = getLastGuestId();
        lastGuestId++;
    }


    public static int getLastGuestId() {
        return lastGuestId;
    }

    public static void setLastGuestId(int lastGuestId) {
        Guest.lastGuestId = lastGuestId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getGuestId() {
        return guestId;
    }

    public Room getReservedRoom() {
        return reservedRoom;
    }

    public void setReservedRoom(Room reservedRoom) {
        this.reservedRoom = reservedRoom;
    }
}
