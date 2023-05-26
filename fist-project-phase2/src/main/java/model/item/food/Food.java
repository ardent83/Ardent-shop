package model.item.food;

import model.item.Category;
import model.item.Comment;
import model.item.CommentStatus;
import model.item.Item;

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
        stringBuilder.append(super.toString())
                .append("\nproduction date : ").append(productionDate)
                .append("\nexpiration date : ").append(expirationDate)
                .append("\n_______________________________________________");
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
