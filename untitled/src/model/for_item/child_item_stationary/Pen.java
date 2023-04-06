package model.for_item.child_item_stationary;

import model.for_item.Category;

public class Pen extends Stationary{
    public Pen(String name, double price, int availableNumber, String producingCountry, String color) {
        super(name, price, availableNumber, Category.STATIONERY, producingCountry);
        this.color = color;
    }

    private final String color;

    public String getColor() {
        return color;
    }
}
