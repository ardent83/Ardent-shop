package model.item.digital;

import model.item.Category;
import model.item.Discount;
import model.item.Item;

public abstract class DigitalItem extends Item  implements Discount {
    public DigitalItem(String name, double price, int availableNumber, Category category, double weight, double volume) {
        super(name, price, availableNumber, category);
        this.weight = weight;
        this.volume = volume;
        this.originalPrice = price;
    }
    private double originalPrice;
    @Override
    public void addDiscount(double discountPercent) {
        this.discountPercent = discountPercent;
        super.price = (this.getPrice() * (100 - discountPercent) / 100);
    }

    @Override
    public void removeDiscount() {
        super.price = originalPrice;
    }
    @Override
    public void setPrice(double price) {
        super.setPrice(price);
        this.originalPrice = price;
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

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(super.toString())
                .append("\nwight : ").append(this.weight)
                .append("\nvolume : ").append(this.volume);
        return stringBuilder.toString();
    }
}
