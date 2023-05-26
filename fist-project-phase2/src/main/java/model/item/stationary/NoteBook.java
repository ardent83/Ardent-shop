package model.item.stationary;

import model.item.Category;
import model.item.Comment;
import model.item.CommentStatus;

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
        stringBuilder.append(super.toString())
                .append("\nnumber of page : ").append(numberOfPage)
                .append("\npaper type : ").append(paperType)
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
