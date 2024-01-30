package model;

import java.util.ArrayList;

public class Room {
    private int number;
    private ArrayList<Double> allRatings;
    private double rating;
    private double size;
    private double price;
    private String type;
    private Hotel hotel;
    private boolean availability;

    {
        availability = true;
        allRatings = new ArrayList<>();
        rating = 0;
    }

    public Room(int number, double size, double price, String type) {
        this.number = number;
        this.size = size;
        this.price = price;
        this.type = type;
    }

    public boolean isAvailable() {
        return availability;
    }

    public void updateRating(double rating) {
        allRatings.add(rating);
        double avgRating;
        double sumRating = 0;
        for (double rate : allRatings) {
            sumRating += rate;
        }
        avgRating = sumRating / allRatings.size();
        setRating(avgRating);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public ArrayList<Double> getAllRatings() {
        return allRatings;
    }

    public void setAllRatings(ArrayList<Double> allRatings) {
        this.allRatings = allRatings;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String toString() {
        String status;
        if (isAvailable())
            status = "available";
        else status = "not available";
        return " number: " + number + " | size: " + size + " | price: " + price + " | type: "
                + type + " | availability: " + status + " | rating: " + rating;
    }
}
