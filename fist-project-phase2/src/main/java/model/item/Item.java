package model.item;

import model.item.digital.Pc;
import model.item.digital.Ssd;
import model.item.digital.Usb;
import model.item.food.Food;
import model.item.stationary.NoteBook;
import model.item.stationary.Pen;
import model.item.stationary.Pencil;
import model.item.vehicles.Bike;
import model.item.vehicles.Car;

import java.util.ArrayList;

public abstract class Item implements Comparable {
    public Item(String name, double price, int availableNumber, Category category) {
        this.idItem = Integer.toHexString(countForId + 48049);
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

    @Override
    public int compareTo(Object o) {
        Item item = (Item) o;
        if (this instanceof Pc && !(item instanceof Pc)) {
            return -1;
        } else if (!(this instanceof Pc) && item instanceof Pc) {
            return 1;
        } else if (this instanceof Ssd && !(item instanceof Ssd)) {
            return -1;
        } else if (!(this instanceof Ssd) && item instanceof Ssd) {
            return 1;
        } else if (this instanceof Usb && !(item instanceof Usb)) {
            return -1;
        } else if (!(this instanceof Usb) && item instanceof Usb) {
            return 1;
        } else if (this instanceof Food && !(item instanceof Food)) {
            return -1;
        } else if (!(this instanceof Food) && item instanceof Food) {
            return 1;
        } else if (this instanceof NoteBook && !(item instanceof NoteBook)) {
            return -1;
        } else if (!(this instanceof NoteBook) && item instanceof NoteBook) {
            return 1;
        } else if (this instanceof Pen && !(item instanceof Pen)) {
            return -1;
        } else if (!(this instanceof Pen) && item instanceof Pen) {
            return 1;
        } else if (this instanceof Pencil && !(item instanceof Pencil)) {
            return -1;
        } else if (!(this instanceof Pencil) && item instanceof Pencil) {
            return 1;
        } else if (this instanceof Bike && !(item instanceof Bike)) {
            return -1;
        } else if (!(this instanceof Bike) && item instanceof Bike) {
            return 1;
        } else if (this instanceof Car && !(item instanceof Car)) {
            return -1;
        } else if (!(this instanceof Car) && item instanceof Car) {
            return 1;
        } else if (this.name.toUpperCase().compareTo(item.name.toUpperCase()) != 0) {
            return (this.name.toUpperCase().compareTo(item.name.toUpperCase()));
        } else if (this instanceof Pc) {
            if (((Pc) this).getRamCapacity() > ((Pc) item).getRamCapacity()) {
                return -1;
            }
            return 1;
        } else if (this instanceof Ssd) {
            if (((Ssd) this).getCapacity() > ((Ssd) item).getCapacity()) {
                return -1;
            }
            return 1;
        } else if (this instanceof Usb) {
            if (((Usb) this).getCapacity() > ((Usb) item).getCapacity()) {
                return -1;
            }
            return 1;
        } else if (this instanceof NoteBook) {
            if (((NoteBook) this).getNumberOfPage() > ((NoteBook) item).getNumberOfPage()){
                return -1;
            }
            return 1;
        } else if (this instanceof Car) {
            if (((Car) this).getEngineVolume() > ((Car) item).getEngineVolume()){
                return -1;
            }
            return 1;
        } else if (this.averageScore > item.averageScore) {
            return -1;
        } else if (this.averageScore < item.averageScore) {
            return 1;
        } else if (this.price > item.price) {
            return -1;
        } else if (this.price < item.price) {
            return 1;
        } else if (this.availableNumber > item.availableNumber) {
            return -1;
        } else if (this.availableNumber < item.availableNumber) {
            return 1;
        }
        return 0;
    }

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

    public void addComment(Comment comment) {
        this.commentArrayList.add(comment);
    }

    public ArrayList<Comment> getCommentArrayList() {
        return commentArrayList;
    }

    public ArrayList<Score> getScoreArrayList() {
        return scoreArrayList;
    }

    public void addScore(Score score) {
        this.scoreArrayList.add(score);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nname : ").append(this.name)
                .append("\nScore : ").append(this.averageScore)
                .append("\nprice : ").append(this.price)
                .append("\navailable number : ").append(this.availableNumber)
                .append("\ncategory : ").append(this.category);
        return stringBuilder.toString();
    }

    static {
        countForId = 0;
    }
}
