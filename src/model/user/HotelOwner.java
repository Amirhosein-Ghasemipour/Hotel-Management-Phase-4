package model.user;

import model.Hotel;
import model.HotelManagement;


public class HotelOwner extends User {
    private final int hotelOwnerId;
    private Hotel hotel;
    private static int lastHotelOwnerId;

    static {
        lastHotelOwnerId = 0;
    }

    {
        hotel = null;
    }

    public HotelOwner(String username, String password, String hotelName, String hotelLocation) {
        super(username, password);
        Hotel hotel = new Hotel(hotelName, hotelLocation);
        this.addHotel(hotel);
        setHotel(hotel);
        this.hotelOwnerId = getLastHotelOwnerId();
        lastHotelOwnerId++;
    }

    public static int getLastHotelOwnerId() {
        return lastHotelOwnerId;
    }

    public static void setLastHotelOwnerId(int lastHotelOwnerId) {
        HotelOwner.lastHotelOwnerId = lastHotelOwnerId;
    }

    public int getHotelOwnerId() {
        return hotelOwnerId;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    private void addHotel(Hotel hotel) {
        HotelManagement.addHotel(hotel);
    }
}
