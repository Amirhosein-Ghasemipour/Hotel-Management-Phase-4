package model;

public class Food {
    private String name;
    private double price;
    private String description;

    public Food(String name, double price, String description) {
        setName(name);
        setPrice(price);
        setDescription(description);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString() {
        return " name: " + name + " | price: " + price + " | description: " + description;
    }
}
