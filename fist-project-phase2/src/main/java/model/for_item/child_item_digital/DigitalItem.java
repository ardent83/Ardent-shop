package model.for_item.child_item_digital;

import model.for_item.Category;
import model.for_item.Discount;
import model.for_item.Item;

public abstract class DigitalItem extends Item  implements Discount {
    public DigitalItem(String name, double price, int availableNumber, Category category, double weight, double volume) {
        super(name, price, availableNumber, category);
        this.weight = weight;
        this.volume = volume;
    }

    @Override
    public void addDiscount(double discountPercent) {
        this.discountPercent = discountPercent;
        this.setPrice(this.getPrice() * discountPercent / 100);
    }

    @Override
    public void removeDiscount() {
        this.setPrice(this.getPrice() * 100 / this.discountPercent);
        discountPercent = 0;
    }

    private final double weight;
    private final double volume;
    private double discountPercent;

    public double getWeight() {
        return weight;
    }

    public double getVolume() {
        return volume;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }
}
