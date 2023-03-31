package model.for_item.child_item_vehicles;

import model.for_item.Category;
import model.for_item.Item;

public abstract class Vehicles extends Item {
    public Vehicles(String name, double price, int availableNumber, Category category, String companyName) {
        super(name, price, availableNumber, category);
        this.companyName = companyName;
    }
    private final String companyName;

    public String getCompanyName() {
        return companyName;
    }
}
