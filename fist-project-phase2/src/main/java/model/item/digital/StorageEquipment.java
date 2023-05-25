package model.item.digital;

import model.item.Category;

public abstract class StorageEquipment extends DigitalItem{
    public StorageEquipment(String name, double price, int availableNumber, double weight, double volume, double capacity) {
        super(name, price, availableNumber, Category.DIGITAL, weight, volume);
        this.capacity = capacity;
    }

    private final double capacity;
    public double getCapacity() {
        return capacity;
    }
}
