package model.for_item.child_item_stationary;

import model.for_item.Category;
import model.for_item.Comment;
import model.for_item.CommentStatus;

public class Pencil extends Stationary {
    public Pencil(String name, double price, int availableNumber, String producingCountry, PencilType pencilType) {
        super(name, price, availableNumber, Category.STATIONERY, producingCountry);
        this.pencilType = pencilType;
    }

    private final PencilType pencilType;

    public PencilType getPencilType() {
        return pencilType;
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
                .append("\npencil type : ").append(pencilType)
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
