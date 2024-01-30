package model;

import model.user.Guest;

import java.util.ArrayList;

public class Hotel {
    private String name;
    private final String location;
    private double balance;
    private double rating;
    private ArrayList<Comment> comments;
    private ArrayList<Guest> guests;
    private ArrayList<Room> rooms;
    private ArrayList<Room> availableRooms;
    private ArrayList<Room> bookedRooms;
    private ArrayList<Food> allFoods;
    private final int hotelId;
    private static int lastHotelId;

    static {
        lastHotelId = 0;
    }

    {
        balance = 0;
        rating = 0;
        comments = new ArrayList<>();
        rooms = new ArrayList<>();
        availableRooms = new ArrayList<>();
        bookedRooms = new ArrayList<>();
        allFoods = new ArrayList<>();
        guests = new ArrayList<>();
    }

    public Hotel(String name, String location) {
        this.name = name;
        this.location = location;
        this.hotelId = getLastHotelId();
        lastHotelId++;
    }

    public Room getRoomByNumber(int number) {
        for (Room room : rooms) {
            if (room.getNumber() == number)
                return room;
        }
        return null;
    }

    public Food getFoodByName(String name) {
        for (Food food : allFoods) {
            if (food.getName().equals(name))
                return food;
        }
        return null;
    }

    public void addFood(Food food) {
        allFoods.add(food);
    }

    public void removeFood(Food food) {
        allFoods.remove(food);
    }

    public void spendFromBalance(double cost) {
        balance -= cost;
    }

    public void addRoom(Room room) {
        rooms.add(room);
        availableRooms.add(room);
        room.setHotel(this);
    }

    public void removeRoom(Room room) {
        rooms.remove(room);
        availableRooms.remove(room);
    }

    public void increaseBalance(double amount) {
        balance += amount;
    }

    public void increaseOneRoomPrice(Room room, double percent) {
        double initialPrice = room.getPrice();
        percent /= 100;
        room.setPrice(initialPrice + percent * initialPrice);
    }

    public void increaseAllRoomsPrice(double percent) {
        percent /= 100;
        double initialPrice;
        for (Room room : rooms) {
            initialPrice = room.getPrice();
            room.setPrice(initialPrice + percent * initialPrice);
        }
    }

    public void setDiscountOneRoom(Room room, double percent) {
        double initialPrice = room.getPrice();
        percent /= 100;
        room.setPrice(initialPrice - percent * initialPrice);
    }

    public void setDiscountAllRooms(double percent) {
        percent /= 100;
        double initialPrice;
        for (Room room : rooms) {
            initialPrice = room.getPrice();
            room.setPrice(initialPrice - percent * initialPrice);
        }
    }

    public void setDiscountOneFood(Food food, double percent) {
        double initialPrice = food.getPrice();
        percent /= 100;
        food.setPrice(initialPrice - percent * initialPrice);
    }

    public void setDiscountAllFoods(double percent) {
        percent /= 100;
        double initialPrice;
        for (Food food : allFoods) {
            initialPrice = food.getPrice();
            food.setPrice(initialPrice - percent * initialPrice);
        }
    }

    public void increaseOneFoodPrice(Food food, double percent) {
        double initialPrice = food.getPrice();
        percent /= 100;
        food.setPrice(initialPrice + percent * initialPrice);
    }

    public void increaseAllFoodsPrice(double percent) {
        percent /= 100;
        double initialPrice;
        for (Food food : allFoods) {
            initialPrice = food.getPrice();
            food.setPrice(initialPrice + percent * initialPrice);
        }
    }

    public void reserveRoom(Room room) {
        bookedRooms.add(room);
        availableRooms.remove(room);
    }

    public void leaveRoom(Room room) {
        bookedRooms.remove(room);
        availableRooms.add(room);
    }

    public void updateRating() {
        double sumRating = 0;
        for (Room room : rooms) {
            sumRating += room.getRating();
        }
        setRating(sumRating / rooms.size());
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public static int getLastHotelId() {
        return lastHotelId;
    }

    public static void setLastHotelId(int lastHotelId) {
        Hotel.lastHotelId = lastHotelId;
    }

    public int getHotelId() {
        return hotelId;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public String getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Room> getAvailableRooms() {
        return availableRooms;
    }

    public void setAvailableRooms(ArrayList<Room> availableRooms) {
        this.availableRooms = availableRooms;
    }

    public ArrayList<Room> getBookedRooms() {
        return bookedRooms;
    }

    public void setBookedRooms(ArrayList<Room> bookedRooms) {
        this.bookedRooms = bookedRooms;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public ArrayList<Food> getAllFoods() {
        return allFoods;
    }

    public void setAllFoods(ArrayList<Food> allFoods) {
        this.allFoods = allFoods;
    }

    public ArrayList<Guest> getGuests() {
        return guests;
    }

    public void setGuests(ArrayList<Guest> guests) {
        this.guests = guests;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }


    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public String toString() {
        return " name: " + name + " | location: " + location + " | number of available rooms: " + availableRooms.size()
                + " | rating: " + rating;
    }
}
