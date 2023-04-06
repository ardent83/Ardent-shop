package model.for_item.child_item_food;

import model.for_item.Category;
import model.for_item.Item;

public class Food extends Item {
    public Food(String name, double price, int availableNumber, String productionDate, String expirationDate) {
        super(name, price, availableNumber, Category.FOOD);
        this.productionDate = productionDate;
        this.expirationDate = expirationDate;
    }

    private final String productionDate;
    private final String expirationDate;

    public String getProductionDate() {
        return productionDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }
}
