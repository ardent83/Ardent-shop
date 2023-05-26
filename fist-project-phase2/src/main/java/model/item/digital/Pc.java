package model.item.digital;

import model.item.Category;
import model.item.Comment;
import model.item.CommentStatus;

public class Pc extends DigitalItem {
    public Pc(String name, double price, int availableNumber, double weight, double volume, String modelCpu, double ramCapacity) {
        super(name, price, availableNumber, Category.DIGITAL, weight, volume);
        this.modelCpu = modelCpu;
        this.ramCapacity = ramCapacity;
    }

    private final String modelCpu;
    private final double ramCapacity;

    public String getModelCpu() {
        return modelCpu;
    }

    public double getRamCapacity() {
        return ramCapacity;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n***********************************************");
        stringBuilder.append(super.toString())
                .append("\nmodel CPU : ").append(modelCpu)
                .append("\nRAM capacity : ").append(ramCapacity)
                .append("\n______________________________________________________");
        for (Comment comment : super.getCommentArrayList()) {
            if (comment.getCommentStatus().equals(CommentStatus.ACCEPTED)) {
                stringBuilder.append(comment);
                stringBuilder.append("\n______________________________________________________");
            }
        }
                stringBuilder.append("\n***********************************************");
        return stringBuilder.toString();
    }
}
