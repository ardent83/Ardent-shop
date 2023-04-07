package model.for_item;

import java.util.ArrayList;

public abstract class Item {
    public Item(String name, double price, int availableNumber, Category category) {
        this.idItem = Integer.toHexString(countForId+48049);
        this.name = name;
        this.price = price;
        this.availableNumber = availableNumber;
        this.averageScore = 0;
        this.category = category;
        this.commentArrayList = new ArrayList<>();
        this.scoreArrayList = new ArrayList<>();
        countForId++;
    }
    private static int countForId;
    private final String idItem;
    private String name;
    private double price;
    private int availableNumber;
    private double averageScore;
    private final Category category;
    private ArrayList<Comment> commentArrayList;
    private ArrayList<Score> scoreArrayList;

    public String getIdItem() {
        return idItem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAvailableNumber() {
        return availableNumber;
    }

    public void setAvailableNumber(int availableNumber) {
        this.availableNumber = availableNumber;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }

    public Category getCategory() {
        return category;
    }
    public void addComment(Comment comment){
        this.commentArrayList.add(comment);
    }

    public ArrayList<Comment> getCommentArrayList() {
        return commentArrayList;
    }

    public ArrayList<Score> getScoreArrayList() {
        return scoreArrayList;
    }
    public void addScore(Score score){
        this.scoreArrayList.add(score);
    }

    static {
        countForId = 0;
    }
}
