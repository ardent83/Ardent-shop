package model.for_item.child_item_digital;

import model.for_item.Category;

public abstract class StorageEquipment extends DigitalItem{
    public StorageEquipment(String name, double price, int availableNumber, Category category, double weight, double volume, double capacity) {
        super(name, price, availableNumber, category, weight, volume);
        this.capacity = capacity;
    }

    private final double capacity;
    public double getCapacity() {
        return capacity;
    }
}
