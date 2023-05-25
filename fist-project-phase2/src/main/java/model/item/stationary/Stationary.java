package model.item.stationary;

import model.item.Category;
import model.item.Item;

public abstract class Stationary extends Item {
    public Stationary(String name, double price, int availableNumber, Category category, String producingCountry) {
        super(name, price, availableNumber, category);
        this.producingCountry = producingCountry;
    }

    private final String producingCountry;

    public String getProducingCountry() {
        return producingCountry;
    }
}
