package model.for_item.child_item_stationary;

import model.for_item.Category;

public class NoteBook extends Stationary {
    public NoteBook(String name, double price, int availableNumber, Category category, String producingCountry, int numberOfPage, String paperType) {
        super(name, price, availableNumber, category, producingCountry);
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
}
