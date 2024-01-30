package model;

public class Room {
    private int number;
    private double size;
    private double price;
    private String type;
    private Hotel hotel;
    private boolean availability;
    {
        availability = true;
    }

    public Room(int number, double size, double price, String type){
        this.number = number;
        this.size = size;
        this.price = price;
        this.type = type;
    }
    public boolean isAvailable(){
        return availability;
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
}
