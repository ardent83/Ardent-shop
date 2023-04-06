package model.for_item.child_item_digital;

import model.for_item.Category;

public class Ssd extends StorageEquipment {
    public Ssd(String name, double price, int availableNumber, double weight, double volume, double capacity, double readSpeed, double writSpeed) {
        super(name, price, availableNumber, weight, volume, capacity);
        this.readSpeed = readSpeed;
        this.writSpeed = writSpeed;
    }

    private final double readSpeed;
    private final double writSpeed;

    public double getReadSpeed() {
        return readSpeed;
    }

    public double getWritSpeed() {
        return writSpeed;
    }
}
