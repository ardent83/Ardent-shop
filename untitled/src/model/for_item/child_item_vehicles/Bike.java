package model.for_item.child_item_vehicles;

import model.for_item.Category;

public class Bike extends Vehicles {
    public Bike(String name, double price, int availableNumber, String companyName, BikeType bikeType) {
        super(name, price, availableNumber, Category.VEHICLES, companyName);
        this.bikeType = bikeType;
    }

    BikeType bikeType;

    public BikeType getBikeType() {
        return bikeType;
    }
}
