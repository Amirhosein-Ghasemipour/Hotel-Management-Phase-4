package controller;

import model.*;
import model.user.Guest;
import view.enums.messages.GuestMenuMessages;

public class GuestMenuController {
    public static GuestMenuMessages reserveRoom(String hotelName, int roomNumber) {
        Guest guest = HotelManagement.getCurrentGuest();
        Hotel hotel = HotelManagement.getHotelByName(hotelName);
        if (hotel == null)
            return GuestMenuMessages.RESERVE_ROOM_HOTEL_NOT_FOUND;

        Room room = hotel.getRoomByNumber(roomNumber);
        if (room == null)
            return GuestMenuMessages.RESERVE_ROOM_NOT_FOUND;

        if (!room.isAvailable())
            return GuestMenuMessages.RESERVE_ROOM_NOT_AVAILABLE;

        if (guest.getReservedRoom() != null)
            return GuestMenuMessages.RESERVE_ROOM_SECOND_ROOM;

        if (guest.getBalance() < room.getPrice())
            return GuestMenuMessages.RESERVE_ROOM_INSUFFICIENT_BALANCE;

        guest.setReservedRoom(room);
        guest.setBalance(guest.getBalance() - room.getPrice());
        room.setAvailability(false);
        hotel.reserveRoom(room);
        return GuestMenuMessages.RESERVE_ROOM_SUCCESS;
    }

    public static GuestMenuMessages leaveRoom() {
        Guest guest = HotelManagement.getCurrentGuest();
        Room room = guest.getReservedRoom();
        if (room == null)
            return GuestMenuMessages.LEAVE_ROOM_NOT_FOUND;

        guest.setReservedRoom(null);
        room.getHotel().leaveRoom(room);
        return GuestMenuMessages.LEAVE_ROOM_SUCCESS;
    }

    public static GuestMenuMessages buyFood(String foodName, int number) {
        Guest guest = HotelManagement.getCurrentGuest();
        Room room = guest.getReservedRoom();
        if (number <= 0)
            return GuestMenuMessages.BUY_FOOD_NEGATIVE;

        if (room == null)
            return GuestMenuMessages.BUY_FOOD_ROOM_NOT_RESERVED;

        Hotel hotel = room.getHotel();
        Food food = hotel.getFoodByName(foodName);
        if (food == null)
            return GuestMenuMessages.BUY_FOOD_NOT_FOUND;

        if (guest.getBalance() < food.getPrice() * number)
            return GuestMenuMessages.BUY_FOOD_INSUFFICIENT_BALANCE;

        guest.addFood(food, number);
        guest.setBalance(guest.getBalance() - food.getPrice() * number);
        return GuestMenuMessages.BUY_FOOD_SUCCESS;
    }

    public static GuestMenuMessages increaseBalance(double amount) {
        Guest guest = HotelManagement.getCurrentGuest();
        if (amount <= 0)
            return GuestMenuMessages.INCREASE_BALANCE_NEGATIVE;

        guest.setBalance(guest.getBalance() + amount);
        return GuestMenuMessages.INCREASE_BALANCE_SUCCESS;
    }

    public static GuestMenuMessages rateMyRoom(double rating) {
        Guest guest = HotelManagement.getCurrentGuest();
        Room room = guest.getReservedRoom();
        if (room == null)
            return GuestMenuMessages.RATE_MY_ROOM_NOT_EXISTS;

        if (rating < 0 || rating > 5)
            return GuestMenuMessages.RATE_MY_ROOM_INVALID;

        room.updateRating(rating);
        room.getHotel().updateRating();
        return GuestMenuMessages.RATE_MY_ROOM_SUCCESS;
    }

    public static GuestMenuMessages leaveComment(String content) {
        Guest guest = HotelManagement.getCurrentGuest();
        Room room = guest.getReservedRoom();
        if (room == null)
            return GuestMenuMessages.LEAVE_COMMENT_ROOM_NOT_EXISTS;

        Hotel hotel = room.getHotel();
        hotel.addComment(new Comment(content, guest));
        return GuestMenuMessages.LEAVE_COMMENT_SUCCESS;
    }

    public static GuestMenuMessages showHotelComments(String hotelName) {
        Hotel hotel = HotelManagement.getHotelByName(hotelName);
        if (hotel == null)
            return GuestMenuMessages.SHOW_COMMENTS_HOTEL_NOT_FOUND;

        if (hotel.getComments().isEmpty())
            return GuestMenuMessages.SHOW_COMMENTS_NO_COMMENT;

        return GuestMenuMessages.SHOW_COMMENTS_SUCCESS;

    }

}
