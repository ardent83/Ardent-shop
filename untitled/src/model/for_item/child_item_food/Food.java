package model.for_item.child_item_food;

import model.for_item.Category;
import model.for_item.Comment;
import model.for_item.CommentStatus;
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
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n***********************************************");
        stringBuilder.append("name : ").append(super.getName())
                .append("\nprice : ").append(super.getPrice())
                .append("\navailable number : ").append(super.getAvailableNumber())
                .append("\ncategory : ").append(super.getCategory())
                .append("\nproduction date : ").append(productionDate)
                .append("\nexpiration date : ").append(expirationDate)
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
