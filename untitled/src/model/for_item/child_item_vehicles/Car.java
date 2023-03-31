package model.for_item.child_item_vehicles;

import model.for_item.Category;

public class Car extends Vehicles {
    public Car(String name, double price, int availableNumber, Category category, String companyName, double engineVolume, boolean automatic) {
        super(name, price, availableNumber, category, companyName);
        this.engineVolume = engineVolume;
        this.automatic = automatic;
    }

    private final double engineVolume;
    private final boolean automatic;

    public double getEngineVolume() {
        return engineVolume;
    }

    public boolean isAutomatic() {
        return automatic;
    }
}
