package controller;

import model.Food;
import model.Hotel;
import model.HotelManagement;
import model.Room;
import model.user.HotelOwner;
import view.enums.messages.HotelOwnerMenuMessages;


public class HotelOwnerMenuController {
    public static HotelOwnerMenuMessages addRoom(int number, double size, double price, String type) {
        if (number <= 0)
            return HotelOwnerMenuMessages.ADD_ROOM_NUMBER_INVALID;

        if (size <= 0)
            return HotelOwnerMenuMessages.ADD_ROOM_PRICE_INVALID;

        if (price <= 0)
            return HotelOwnerMenuMessages.ADD_ROOM_PRICE_INVALID;

        if (HotelManagement.getCurrentHotelOwner().getHotel().getRoomByNumber(number) != null)
            return HotelOwnerMenuMessages.ADD_ROOM_NUMBER_EXISTS;

        HotelManagement.getCurrentHotelOwner().getHotel().addRoom(new Room(number, size, price, type));
        return HotelOwnerMenuMessages.ADD_ROOM_SUCCESS;

    }

    public static HotelOwnerMenuMessages removeRoom(int number) {
        HotelOwner hotelOwner = HotelManagement.getCurrentHotelOwner();
        Hotel hotel = hotelOwner.getHotel();
        Room room = hotel.getRoomByNumber(number);
        if (number <= 0)
            return HotelOwnerMenuMessages.REMOVE_ROOM_NUMBER_INVALID;

        if (room == null)
            return HotelOwnerMenuMessages.REMOVE_ROOM_NUMBER_NOT_FOUND;

        if (!room.isAvailable())
            return HotelOwnerMenuMessages.REMOVE_ROOM_IS_BOOKED;

        hotel.removeRoom(room);
        return HotelOwnerMenuMessages.REMOVE_ROOM_SUCCESS;
    }

    public static HotelOwnerMenuMessages increaseHotelBalance(double amount) {
        Hotel hotel = HotelManagement.getCurrentHotelOwner().getHotel();
        if (amount <= 0)
            return HotelOwnerMenuMessages.INCREASE_HOTEL_BALANCE_INVALID;

        hotel.increaseBalance(amount);
        return HotelOwnerMenuMessages.INCREASE_HOTEL_BALANCE_SUCCESS;
    }

    public static HotelOwnerMenuMessages addFood(String name, double cost, double price, String description) {
        Hotel hotel = HotelManagement.getCurrentHotelOwner().getHotel();
        double profit = price - cost;
        if (cost <= 0)
            return HotelOwnerMenuMessages.ADD_FOOD_COST_INVALID;

        if (price <= 0)
            return HotelOwnerMenuMessages.ADD_FOOD_PRICE_INVALID;

        if (hotel.getFoodByName(name) != null)
            return HotelOwnerMenuMessages.ADD_FOOD_EXISTS;

        if (hotel.getBalance() < cost)
            return HotelOwnerMenuMessages.ADD_FOOD_INSUFFICIENT_BALANCE;

        if (profit < 0)
            return HotelOwnerMenuMessages.ADD_FOOD_PROFIT_NEGATIVE;

        hotel.addFood(new Food(name, price, description));
        hotel.spendFromBalance(cost);
        return HotelOwnerMenuMessages.ADD_FOOD_SUCCESS;
    }

    public static HotelOwnerMenuMessages removeFood(String name) {
        Hotel hotel = HotelManagement.getCurrentHotelOwner().getHotel();
        Food food = hotel.getFoodByName(name);
        if (food == null)
            return HotelOwnerMenuMessages.REMOVE_FOOD_NOT_FOUND;

        hotel.removeFood(food);
        return HotelOwnerMenuMessages.REMOVE_FOOD_SUCCESS;
    }

    public static HotelOwnerMenuMessages increaseOneRoomPrice(double percent, int roomNumber) {
        Hotel hotel = HotelManagement.getCurrentHotelOwner().getHotel();
        Room room = hotel.getRoomByNumber(roomNumber);
        if (percent < 0)
            return HotelOwnerMenuMessages.INCREASE_ONE_ROOM_PRICE_INVALID;

        if (room == null)
            return HotelOwnerMenuMessages.INCREASE_ONE_ROOM_PRICE_NOT_FOUND;

        hotel.increaseOneRoomPrice(room, percent);
        return HotelOwnerMenuMessages.INCREASE_ONE_ROOM_PRICE_SUCCESS;
    }

    public static HotelOwnerMenuMessages increaseAllRoomsPrice(double percent) {
        Hotel hotel = HotelManagement.getCurrentHotelOwner().getHotel();
        if (hotel.getRooms().isEmpty())
            return HotelOwnerMenuMessages.INCREASE_ALL_ROOMS_PRICE_EMPTY;

        if (percent < 0)
            return HotelOwnerMenuMessages.INCREASE_ALL_ROOMS_PRICE_INVALID;

        hotel.increaseAllRoomsPrice(percent);
        return HotelOwnerMenuMessages.INCREASE_ALL_ROOMS_PRICE_SUCCESS;
    }

    public static HotelOwnerMenuMessages setDiscountOneRoom(double percent, int roomNumber) {
        Hotel hotel = HotelManagement.getCurrentHotelOwner().getHotel();
        Room room = hotel.getRoomByNumber(roomNumber);
        if (percent < 0)
            return HotelOwnerMenuMessages.SET_DISCOUNT_ONE_ROOM_INVALID;

        if (percent >= 100)
            return HotelOwnerMenuMessages.SET_DISCOUNT_ONE_ROOM_PRICE_NEGATIVE;

        if (room == null)
            return HotelOwnerMenuMessages.SET_DISCOUNT_ONE_ROOM_NOT_FOUND;

        hotel.setDiscountOneRoom(room, percent);
        return HotelOwnerMenuMessages.SET_DISCOUNT_ONE_ROOM_SUCCESS;

    }

    public static HotelOwnerMenuMessages setDiscountAllRooms(double percent) {
        Hotel hotel = HotelManagement.getCurrentHotelOwner().getHotel();
        if (hotel.getRooms().isEmpty())
            return HotelOwnerMenuMessages.SET_DISCOUNT_ALL_ROOMS_EMPTY;

        if (percent < 0)
            return HotelOwnerMenuMessages.SET_DISCOUNT_ALL_ROOMS_INVALID;

        if (percent >= 100)
            return HotelOwnerMenuMessages.SET_DISCOUNT_ALL_ROOMS_PRICE_NEGATIVE;

        hotel.setDiscountAllRooms(percent);
        return HotelOwnerMenuMessages.SET_DISCOUNT_ALL_ROOMS_SUCCESS;
    }

    public static HotelOwnerMenuMessages setDiscountOneFood(double percent, String name) {
        Hotel hotel = HotelManagement.getCurrentHotelOwner().getHotel();
        Food food = hotel.getFoodByName(name);
        if (percent < 0)
            return HotelOwnerMenuMessages.SET_DISCOUNT_ONE_FOOD_INVALID;

        if (percent >= 100)
            return HotelOwnerMenuMessages.SET_DISCOUNT_ONE_FOOD_PRICE_NEGATIVE;

        if (food == null)
            return HotelOwnerMenuMessages.SET_DISCOUNT_ONE_FOOD_NOT_FOUND;

        hotel.setDiscountOneFood(food, percent);
        return HotelOwnerMenuMessages.SET_DISCOUNT_ONE_FOOD_SUCCESS;
    }

    public static HotelOwnerMenuMessages setDiscountAllFoods(double percent) {
        Hotel hotel = HotelManagement.getCurrentHotelOwner().getHotel();
        if (hotel.getAllFoods().isEmpty())
            return HotelOwnerMenuMessages.SET_DISCOUNT_ALL_FOODS_EMPTY;

        if (percent < 0)
            return HotelOwnerMenuMessages.SET_DISCOUNT_ALL_FOODS_INVALID;

        if (percent >= 100)
            return HotelOwnerMenuMessages.SET_DISCOUNT_ALL_FOODS_PRICE_NEGATIVE;

        hotel.setDiscountAllFoods(percent);
        return HotelOwnerMenuMessages.SET_DISCOUNT_ALL_FOODS_SUCCESS;
    }

    public static HotelOwnerMenuMessages increaseOneFoodPrice(double percent, String name) {
        Hotel hotel = HotelManagement.getCurrentHotelOwner().getHotel();
        Food food = hotel.getFoodByName(name);
        if (percent < 0)
            return HotelOwnerMenuMessages.INCREASE_ONE_FOOD_PRICE_INVALID;

        if (food == null)
            return HotelOwnerMenuMessages.INCREASE_ONE_FOOD_PRICE_NOT_FOUND;

        hotel.increaseOneFoodPrice(food, percent);
        return HotelOwnerMenuMessages.INCREASE_ONE_FOOD_PRICE_SUCCESS;
    }

    public static HotelOwnerMenuMessages increaseAllFoodsPrice(double percent) {
        Hotel hotel = HotelManagement.getCurrentHotelOwner().getHotel();
        if (hotel.getAllFoods().isEmpty())
            return HotelOwnerMenuMessages.INCREASE_ALL_FOODS_PRICE_EMPTY;

        if (percent < 0)
            return HotelOwnerMenuMessages.INCREASE_ALL_FOODS_PRICE_INVALID;

        hotel.increaseAllFoodsPrice(percent);
        return HotelOwnerMenuMessages.INCREASE_ALL_FOODS_PRICE_SUCCESS;
    }
}
