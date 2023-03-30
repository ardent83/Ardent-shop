package model.for_item.child_item_digital;

import model.for_item.Category;
import model.for_item.Item;

public abstract class DigitalItem extends Item {
    public DigitalItem(String name, double price, int availableNumber, Category category, double weight, double volume) {
        super(name, price, availableNumber, category);
        this.weight = weight;
        this.volume = volume;
    }

    private final double weight;
    private final double volume;

    public double getWeight() {
        return weight;
    }

    public double getVolume() {
        return volume;
    }
}
