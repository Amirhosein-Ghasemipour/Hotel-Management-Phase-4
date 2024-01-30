package model;

import model.user.Guest;
import model.user.HotelOwner;
import model.user.User;

import java.util.ArrayList;

public class HotelManagement {
    private static ArrayList<User> allUsers;
    private static ArrayList<Hotel> allHotels;
    private static ArrayList<HotelOwner> allHotelOwners;
    private static ArrayList<Guest> allGuests;
    private static User currentUser;
    private static HotelOwner currentHotelOwner;
    private static Guest currentGuest;

    static{
        allUsers = new ArrayList<>();
        allHotels = new ArrayList<>();
        allHotelOwners = new ArrayList<>();
        allGuests = new ArrayList<>();
        currentUser = null;
        currentHotelOwner = null;
        currentGuest = null;
    }

    public static User getUserByUsername(String username){
        if(allUsers.size() == 0)
            return null;
        for (User user : allUsers) {
            if(user.getUsername().equals(username))
                return user;
        }
        return null;
    }

    public static Guest getGuestByUsername(String username){
        if(allGuests.size() == 0)
            return null;
        for(Guest guest : allGuests){
            if(guest.getUsername().equals(username))
                return guest;
        }
        return null;
    }

    public static HotelOwner getHotelOwnerByUsername(String username){
        if(allHotelOwners.size() == 0)
            return null;
        for(HotelOwner hotelOwner : allHotelOwners){
            if(hotelOwner.getUsername().equals(username))
                return hotelOwner;
        }
        return null;
    }

    public static Hotel getHotelByName(String name){
        if(allHotels.size() == 0)
            return null;
        for(Hotel hotel : allHotels){
            if(hotel.getName().equals(name))
                return hotel;
        }
        return null;
    }

    public static void addUser(User user){
        allUsers.add(user);
    }
    public static void addGuest(Guest guest){
        allGuests.add(guest);
    }

    public static void addHotelOwner(HotelOwner hotelOwner){
        allHotelOwners.add(hotelOwner);
    }

    public static void addHotel(Hotel hotel){
        allHotels.add(hotel);
    }

    public static void removeUser(User user){
        allUsers.remove(user);
    }

    public static void removeGuest(Guest guest){
        allGuests.remove(guest);
    }

    public static void removeHotelOwner(HotelOwner hotelOwner){
        allHotelOwners.remove(hotelOwner);
        removeHotel(hotelOwner.getHotel());
    }

    public static void removeHotel(Hotel hotel){
        allHotels.remove(hotel);
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        HotelManagement.currentUser = currentUser;
    }

    public static ArrayList<Hotel> getAllHotels() {
        return allHotels;
    }

    public static void setAllHotels(ArrayList<Hotel> allHotels) {
        HotelManagement.allHotels = allHotels;
    }

    public static ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public static void setAllUsers(ArrayList<User> allUsers) {
        HotelManagement.allUsers = allUsers;
    }

    public static ArrayList<Guest> getAllGuests() {
        return allGuests;
    }

    public static void setAllGuests(ArrayList<Guest> allGuests) {
        HotelManagement.allGuests = allGuests;
    }

    public static ArrayList<HotelOwner> getAllHotelOwners() {
        return allHotelOwners;
    }

    public static void setAllHotelOwners(ArrayList<HotelOwner> allHotelOwners) {
        HotelManagement.allHotelOwners = allHotelOwners;
    }

    public static Guest getCurrentGuest() {
        return currentGuest;
    }

    public static void setCurrentGuest(Guest currentGuest) {
        HotelManagement.currentGuest = currentGuest;
    }

    public static HotelOwner getCurrentHotelOwner() {
        return currentHotelOwner;
    }

    public static void setCurrentHotelOwner(HotelOwner currentHotelOwner) {
        HotelManagement.currentHotelOwner = currentHotelOwner;
    }
}
