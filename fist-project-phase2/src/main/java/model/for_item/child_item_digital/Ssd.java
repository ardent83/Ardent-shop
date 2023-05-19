package model.for_item.child_item_digital;

import model.for_item.Category;
import model.for_item.Comment;
import model.for_item.CommentStatus;

public class Ssd extends StorageEquipment {
    public Ssd(String name, double price, int availableNumber, double weight, double volume, double capacity, double readSpeed, double writSpeed) {
        super(name, price, availableNumber, weight, volume, capacity);
        this.readSpeed = readSpeed;
        this.writSpeed = writSpeed;
    }

    private final double readSpeed;
    private final double writSpeed;

    public double getReadSpeed() {
        return readSpeed;
    }

    public double getWritSpeed() {
        return writSpeed;
    }
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n***********************************************");
        stringBuilder.append("name : ").append(super.getName())
                .append("\nprice : ").append(super.getPrice())
                .append("\navailable number : ").append(super.getAvailableNumber())
                .append("\ncategory : ").append(super.getCategory())
                .append("\nwight : ").append(super.getWeight())
                .append("\nvolume : ").append(super.getVolume())
                .append("\ncapacity : ").append(super.getCapacity())
                .append("\nread speed : ").append(readSpeed)
                .append("\nwrit speed : ").append(writSpeed)
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
