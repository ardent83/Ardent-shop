package model.for_item.child_item_stationary;

import model.for_item.Category;
import model.for_item.Comment;
import model.for_item.CommentStatus;

public class Pen extends Stationary{
    public Pen(String name, double price, int availableNumber, String producingCountry, String color) {
        super(name, price, availableNumber, Category.STATIONERY, producingCountry);
        this.color = color;
    }

    private final String color;

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n***********************************************");
        stringBuilder.append("name : ").append(super.getName())
                .append("\nprice : ").append(super.getPrice())
                .append("\navailable number : ").append(super.getAvailableNumber())
                .append("\ncategory : ").append(super.getCategory())
                .append("\nproducing country : ").append(super.getProducingCountry())
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
