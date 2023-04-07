package model.for_item.child_item_stationary;

import model.for_item.Category;
import model.for_item.Comment;

public class NoteBook extends Stationary {
    public NoteBook(String name, double price, int availableNumber, String producingCountry, int numberOfPage, String paperType) {
        super(name, price, availableNumber, Category.STATIONERY, producingCountry);
        this.numberOfPage = numberOfPage;
        this.paperType = paperType;
    }

    private final int numberOfPage;
    private final String paperType;

    public int getNumberOfPage() {
        return numberOfPage;
    }

    public String getPaperType() {
        return paperType;
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
                .append("\nnumber of page : ").append(numberOfPage)
                .append("\npaper type : ").append(paperType)
                .append("\n______________________________________________________");
        for (Comment comment : super.getCommentArrayList()) {
            stringBuilder.append(comment.toString());
            stringBuilder.append("\n______________________________________________________");
        }
        stringBuilder.append("\n***********************************************");
        return stringBuilder.toString();
    }
}
