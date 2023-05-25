package model.item.vehicles;

import model.item.Category;
import model.item.Item;

public abstract class Vehicles extends Item {
    public Vehicles(String name, double price, int availableNumber, String companyName) {
        super(name, price, availableNumber, Category.VEHICLES);
        this.companyName = companyName;
    }
    private final String companyName;

    public String getCompanyName() {
        return companyName;
    }
}
