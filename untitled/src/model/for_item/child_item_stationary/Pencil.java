package model.for_item.child_item_stationary;

import model.for_item.Category;

public class Pencil extends Stationary {
    public Pencil(String name, double price, int availableNumber, String producingCountry, PencilType pencilType) {
        super(name, price, availableNumber, Category.STATIONERY, producingCountry);
        this.pencilType = pencilType;
    }

    private final PencilType pencilType;

    public PencilType getPencilType() {
        return pencilType;
    }
}
