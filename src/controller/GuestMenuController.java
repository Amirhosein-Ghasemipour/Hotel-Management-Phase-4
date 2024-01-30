package controller;

import model.Hotel;
import model.HotelManagement;
import model.Room;
import model.user.Guest;
import view.enums.messages.GuestMenuMessages;

public class GuestMenuController {
    public static GuestMenuMessages reserveRoom(String hotelName, int roomNumber){
        Guest guest = HotelManagement.getCurrentGuest();
        Hotel hotel = HotelManagement.getHotelByName(hotelName);
        if(hotel == null)
            return GuestMenuMessages.RESERVE_ROOM_HOTEL_NOT_FOUND;

        Room room = hotel.getRoomByNumber(roomNumber);
        if(room == null)
            return GuestMenuMessages.RESERVE_ROOM_NOT_FOUND;

        if(!room.isAvailable())
            return GuestMenuMessages.RESERVE_ROOM_NOT_AVAILABLE;

        if(guest.getReservedRoom() != null)
            return GuestMenuMessages.RESERVE_ROOM_SECOND_ROOM;

        if(guest.getBalance() < room.getPrice())
            return GuestMenuMessages.RESERVE_ROOM_INSUFFICIENT_BALANCE;

        guest.setReservedRoom(room);
        room.setAvailability(false);
        hotel.reserveRoom(room);
        return GuestMenuMessages.RESERVE_ROOM_SUCCESS;
    }

    public static GuestMenuMessages leaveRoom(){
        Guest guest = HotelManagement.getCurrentGuest();
        Room room = guest.getReservedRoom();
        if(room == null)
            return GuestMenuMessages.LEAVE_ROOM_NOT_FOUND;

        guest.setReservedRoom(null);
        room.getHotel().leaveRoom(room);
        return GuestMenuMessages.LEAVE_ROOM_SUCCESS;
    }
}
