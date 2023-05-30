package model.item.stationary;

import model.item.Category;
import model.item.Comment;
import model.item.CommentStatus;
import model.item.Discount;

public class Pen extends Stationary implements Discount {
    public Pen(String name, double price, int availableNumber, String producingCountry, String color) {
        super(name, price, availableNumber, Category.STATIONERY, producingCountry);
        this.color = color;
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
    private final String color;
    private double discountPercent;

    public String getColor() {
        return color;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n***********************************************")
                .append(super.toString())
                .append("\ncolor : ").append(color)
                .append("\n______________________________________________________");
        for (Comment comment : super.getCommentArrayList()) {
            if (comment.getCommentStatus().equals(CommentStatus.ACCEPTED)){
                stringBuilder.append(comment);
                stringBuilder.append("\n______________________________________________________");
            }
        }
        stringBuilder.append("\n***********************************************");
        return stringBuilder.toString();
    }
}
